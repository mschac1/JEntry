/*
 * AbstractDBContainer.java
 *
 * Created on March 16, 2008, 7:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import jentry.sql.swing.DBNavigationPane;
import ext.java.sql.Query;
import ext.java.sql.event.RecordSetChangeEvent;
import ext.javax.swing.db.event.DBFieldUpdateEvent;
import ext.javax.swing.db.event.DBFieldUpdateListener;
import ext.javax.swing.db.event.FormFieldUpdateEvent;
import ext.javax.swing.db.event.FormFieldUpdateListener;
import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JPanel;


/**
 * AbstractDBContainer is designed to contain IFieldControls. It handles all of
 * the neccesary operations when those controls are added or removed to the
 * container, as well as listens for FieldUpdateEvents. Additionally, it
 * keeps track of which controls have default values so that those values can
 * be updated when a record is inserted. Finally, it provides a windowClosing
 * property which can be set to true when the window is being closed so that
 * Exceptions can be handled differently (they could possibly prevent the
 * window from closing
 * Subclasses must implement getNavigationPane(), getSource(), and getQuery()
 * Sublasses should override showDatabaseError to provide their own handling of
 * Database Exceptions
 * The 2 provided subclasses are DBForm and DBContinuousForm.Subform
 * 
 * @author Menachem & Shira
 */
abstract public class AbstractDBContainer extends JPanel implements FormFieldUpdateListener, ContainerListener {
  
  /**
   * Creates a new instance of AbstractDBContainer
   */
  public AbstractDBContainer() {
    addContainerListener(this);
  }

  abstract protected DBNavigationPane getNavigationPane();
  abstract protected DBRecordSetContainer getSource();
  abstract protected Query getQuery();
  
  
  /** DBFieldControls call this method when they want to update a value */
  public void formFieldUpdated(FormFieldUpdateEvent evt) {
    DBFieldControl dbc = evt.getSource();
    try {
      getQuery().updateField(dbc.getFieldName(), dbc.getFieldValue());
			
			// This code block causes any field updates to be broadcasted to all
			// Controls that are listeing for the relevant updates. This allows
			// the user to change the value of a field using one control and
			// have all other related controls be automatically updated.
			// The original control is temporarily remove from the llistener list
			// b/c it doesn't need to be notified of this update - it originated it
			removeDBFieldUpdateListener(dbc, dbc.getFieldName());
			fireDBFieldUpdated(dbc.getFieldName(), dbc.getFieldValue());
			addDBFieldUpdateListener(dbc, dbc.getFieldName());
			
      // Since a change was made to the value - it is explicitly updated,
			// we do not need it in the defaultController list any more
      if (defaultControlList.contains(dbc))
        defaultControlList.remove(dbc);
      
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  
  /**
   * DBFieldControls call this method when they a value is dirtied but not yet
   * ready to be updated
   */
  public void formFieldDirtied(FormFieldUpdateEvent evt) {
    DBFieldControl dbc = evt.getSource();
    if (!dirtyControlList.contains(dbc))
      dirtyControlList.add(dbc);
    getNavigationPane().recordDirtied(null);

    // Avoid duplicating updates
    if (defaultControlList.contains(dbc))
      defaultControlList.remove(dbc);
  }
  

  /** This method should be called by the subclass before a Save of record
   *  number change. It updates the Query object with all of the field values
   *  that have been dirtied
   */
  public void updateDirtyFields() {
    for (DBFieldControl dbc : dirtyControlList) {
      try {
        getQuery().updateField(dbc.getFieldName(), dbc.getFieldValue());
      } catch (SQLException ex) {showDatabaseError(ex);}
    }
    dirtyControlList.clear();
  }
  
  /** This method provides simple error handling using printStackTrace.
   *  It can be overriden to provide more sophisticated error handling
   */
  public void showDatabaseError(Exception ex) {
    ex.printStackTrace();
  }

  /**
   *  This method is public as an implementation side effect. It should not
   *  be called directly by users or overriden by subcasses
   */
  //TODO 3 - Maybe this could be done using an annonymous inner class
  public void componentAdded(ContainerEvent evt) {
    if (evt.getChild() instanceof DBFieldControl) {
      DBFieldControl dbc = (DBFieldControl) evt.getChild();
      dbc.setRecordSourceControl(getSource());

      // If the DBFieldControl has a significant fieldName, set up the dependencies 
      if (dbc.getAllFieldNames().length == 0)
        return;

			addDBFieldUpdateListener(dbc, dbc.getAllFieldNames());
			
      dbc.addFormFieldUpdateListener(this);
      
      // If the query has already been initialized, then initialze the DBFieldControl
      if (getQuery() != null) {
        try {
          dbc.dbFieldUpdated(new DBFieldUpdateEvent(getSource(), dbc.getFieldName(), getQuery().getField(dbc.getFieldName())));
        } catch (SQLException ex) {showDatabaseError(ex);}
      }
    }
    else if (evt.getChild() instanceof DBRecordSetContainer && evt.getChild() instanceof DBFieldUpdateListener) {
      DBRecordSetContainer dbs = (DBRecordSetContainer) evt.getChild();
      DBFieldUpdateListener dbup = (DBFieldUpdateListener) evt.getChild();
      dbs.setConnection(getSource().getConnection());
      for (int i = 0; i < dbs.getParentChildLink().length; i++) {
        String parentField = dbs.getParentChildLink()[i][0];
        addDBFieldUpdateListener(dbup, parentField);
        if (getQuery() != null) {
          try {
            dbup.dbFieldUpdated(new DBFieldUpdateEvent(getSource(), parentField, getQuery().getField(parentField)));
          } catch (SQLException ex) {showDatabaseError(ex);}
        }
      }
    }
  }

  /**
   *  This method is public as an implementation side effect. It should not
   *  be called directly by users or overriden by subcasses
   */
  public void componentRemoved(ContainerEvent evt) {
    if (evt.getChild() instanceof DBFieldControl) {
      DBFieldControl dbc = (DBFieldControl) evt.getChild();
      dbc.setRecordSourceControl(getSource());
      removeDBFieldUpdateListener(dbc, dbc.getAllFieldNames());
      dbc.removeFormFieldUpdateListener(this);
    }    
    else if (evt.getChild() instanceof DBRecordSetContainer && evt.getChild() instanceof DBFieldUpdateListener) {
      DBRecordSetContainer dbs = (DBRecordSetContainer) evt.getChild();
      DBFieldUpdateListener dbup = (DBFieldUpdateListener) evt.getChild();
      for (int i = 0; i < dbs.getParentChildLink().length; i++) {
        String parentField = dbs.getParentChildLink()[i][0];
        removeDBFieldUpdateListener(dbup, parentField);
      }
    }
  }

  
  public void recordSelected(RecordSetChangeEvent evt) {
    // Notify all of the dbFieldListeners that there's a new record
    Enumeration<String> e = getQuery().getFieldNames();
    String fieldName = null;
    while (e.hasMoreElements()) {
      fieldName = e.nextElement();
      ArrayList<DBFieldUpdateListener> list = listenerHash.get(fieldName);
      if (list != null && list.size() > 0) {
        try {
          fireDBFieldUpdated(fieldName, getQuery().getField(fieldName));
        } catch (Exception ex) {showDatabaseError(ex);}
      }
    }
    // If at the insert row, make a list of all of the controls that have default
    // values. We will use this to update those fields if the record is dirtied
    if (getQuery().getRecordNum() == getQuery().getRecordCount() + 1) {
      defaultControlList.clear();
      Component[] comps = getComponents();
      for (int i = 0; i < comps.length; i++) {
        if (comps[i] instanceof DBFieldControl && ((DBFieldControl) comps[i]).getDefaultValue() != null)
          defaultControlList.add((DBFieldControl) comps[i]);
      }
    }
   }

  /** Update the values of all IFieldControls with default values, even if they
   * haven't been otherwise dirtied by the user
   */
  public void recordWillBeInserted (RecordSetChangeEvent evt) {
    for (DBFieldControl dbc : defaultControlList) {
      try {
        getQuery().updateField(dbc.getFieldName(), dbc.getFieldValue());
      } catch (SQLException ex) {showDatabaseError(ex);}
    }
  }
  
  /** Adds a DBFieldUpdateListener to listen to updates of all of the fields named
   *  in fieldNames
   */
  public void addDBFieldUpdateListener(DBFieldUpdateListener listener, String... fieldNames) {
    for (int i = 0; i < fieldNames.length; i++) {
      ArrayList<DBFieldUpdateListener> list = listenerHash.get(fieldNames[i]);
      if (list == null) {
        list = new ArrayList<DBFieldUpdateListener>();
        listenerHash.put(fieldNames[i], list);
      }
      list.add(listener);
    }
  }

  /** Removes a DBFieldUpdateListener from listening to updates of all of the fields named
   *  in fieldNames
   */
  public void removeDBFieldUpdateListener(DBFieldUpdateListener listener, String... fieldNames) {
    for (int i = 0; i < fieldNames.length; i++) {
      ArrayList<DBFieldUpdateListener> list = listenerHash.get(fieldNames[i]);
      if (list == null) {
        list = new ArrayList<DBFieldUpdateListener>();
        listenerHash.put(fieldNames[i], list);
      }
      list.remove(listener);
    }
  }
  
  protected void fireDBFieldUpdated(String fieldName, Object value) {
    DBFieldUpdateEvent evt = new DBFieldUpdateEvent(getSource(), fieldName, value);
    ArrayList<DBFieldUpdateListener> list = listenerHash.get(fieldName);
    if (list == null) {
      list = new ArrayList<DBFieldUpdateListener>();
        listenerHash.put(fieldName, list);
    }
    for (DBFieldUpdateListener l : list) {
      l.dbFieldUpdated(evt);
    }
  }

  
  public boolean isWindowClosing() {return windowClosing;}
  
  public void setWindowClosing(boolean windowClosing) {this.windowClosing = windowClosing;}
  
  protected transient ArrayList<DBFieldControl> dirtyControlList = new ArrayList<DBFieldControl>();

  /** List of controls with default values that need to be considered even if
   * they're not dirtied
   */
  protected transient ArrayList<DBFieldControl> defaultControlList = new ArrayList<DBFieldControl>();

  protected transient Hashtable<String, ArrayList<DBFieldUpdateListener>> listenerHash = new Hashtable<String, ArrayList<DBFieldUpdateListener>>();
  
  /** Set to true if someone is attempting to close the window.
   *  If true, we need to respond differently to field formatting problems
   */
  private boolean windowClosing = false;
}
