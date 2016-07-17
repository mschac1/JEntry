/*
 * DBForm.java
 *
 * Created on January 13, 2008, 5:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import jentry.sql.swing.DBNavigationPane;
import ext.java.sql.Query;
import ext.java.sql.event.RecordSetChangeEvent;
import ext.java.sql.event.RecordSetChangeListener;
import ext.java.util.VetoException;
import ext.javax.swing.WindowClosingException;
import ext.javax.swing.db.event.DBFieldUpdateEvent;
import ext.javax.swing.db.event.DBFieldUpdateListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


// TODO 1.4 - Reinstate, ComboBox, and Label based on the new system
// TODO 3 - More controls: DBTable,  DBChildForm, DBReport, DBListBox, DBOptionPane,  DBFormWizard
// TODO 3 - make the GUI Exception Handling more user modifiable
// TODO 4 - what happens to navigation when uve selcted the navigation record box? - EDIT It seems to work 4
/* TODO 1.2 - The fieldName field should be replaced with a ControlSource field...
 The ControlSource field should be able to parse complex expressions (see C:\Menachem\Java\Math\src\calc\parse)
 If the ControlSource has a single database field as a source, the protected
 field fieldName can be set and the control should be editable, assuming the underlying recordSet
 is editable; otherwise the Control should listen for all relevent DBFields but not have a 2-way link and not be editable
*/
//TODO 2.5 - The DBForm-DBControls relationship should be more dynamic - the Controls should be able to alter the ControlSource at runtime and update the dependencies autoatcialy

/**
 *  NOTE: If the containing window's DefaultCloseOperation property is set to
 *  EXIT_ON_CLOSE, the current record will not be saved on exiting because
 *  the resources are destroyed before the database transaction can be completed.
 *  instead use DISPOSE_ON_CLOSE
 */
public class DBForm extends JPanel implements DBRecordSetContainer, RecordSetChangeListener, AncestorListener, DBFieldUpdateListener {
  
  // Initialize the navigation and content panes and lay them out
  public DBForm() {
    super();

    addAncestorListener(this);
    
    setNavigationPane(new DBNavigationPane());
    setContentPane(new AbstractDBContainer() {
      protected DBNavigationPane getNavigationPane() {
        return getSource().getNavigationPane();
      }
      protected DBForm getSource() {
        return DBForm.this;
      }
      protected Query getQuery() {
        return getSource().getQuery();
      }
    });
    
    setLayout(new BorderLayout());
    add(getNavigationPane(), BorderLayout.SOUTH);
    add(getContentPane(), BorderLayout.CENTER);
    
  }

  public void setRecordSource(String recordSource) throws SQLException {
    this.recordSource = recordSource;
    Query q = new Query(getConnection(), getRecordSource());
    q.addRecordSetChangeListener(this);
    q.addRecordSetChangeListener(getNavigationPane());
    setQuery(q);
    q.initialize();
  }

  // Implement the RecordSetChangeListener interface
  public void recordSetChanged(RecordSetChangeEvent evt) {}
  public void recordSelected(RecordSetChangeEvent evt) {
    getContentPane().recordSelected(evt);
  }
  public void recordInserted (RecordSetChangeEvent evt) {}
  public void recordDirtied(RecordSetChangeEvent evt) {}
  public void recordWillBeUpdated(RecordSetChangeEvent evt) throws VetoException {}
  public void recordUpdated(RecordSetChangeEvent evt) {}
  public void recordWillBeInserted (RecordSetChangeEvent evt) {
    getContentPane().recordWillBeInserted(evt);
  }
  public void recordWillBeDeleted(RecordSetChangeEvent evt) throws VetoException {
    int delete = JOptionPane.showConfirmDialog(this, "Do you want to delete the selected record?", "Delete?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    if (delete == JOptionPane.NO_OPTION || delete == JOptionPane.CLOSED_OPTION)
      throw new VetoException();
  }
  public void recordDeleted(RecordSetChangeEvent evt) {}

  // Passthrough methods for the underlying query object
  public boolean setRecordNum(int recordNum) {
    try {
      getContentPane().updateDirtyFields();
      return getQuery().setRecordNum(recordNum);
    } catch (SQLException ex) { 
      showDatabaseError(ex);
      return false;
    }
  }
  public int getRecordNum() {return getQuery().getRecordNum();}
  public int getRecordCount() {return getQuery().getRecordCount();}
  public boolean isDirty() {return getQuery().isDirty() || isFormDirty();}
  public void updateField(String fieldName, Object value) throws SQLException {
    getQuery().updateField(fieldName, value);
  }
  public Object getField(String fieldName) throws SQLException {
    return getQuery().getField(fieldName);
  }
  public Object getField(String fieldName, Object ifNull) throws SQLException {
    return getQuery().getField(fieldName, ifNull);
  }
  public Class getFieldType(String fieldName) {
    return getQuery().getFieldType(fieldName);
  }

  public void setFilter(String filter) {
    this.filter = filter;
    try {
      getQuery().setFilter(filter);
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public void setSort(String sort) {
    try { 
      getQuery().setSort(sort);
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public void saveRecord() {
    getContentPane().updateDirtyFields();
    try {
      getQuery().saveRecord();
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public void deleteRecord() {
    try {
      getQuery().deleteRecord();
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public void requery() {
    getContentPane().updateDirtyFields();
    try {
      getQuery().requery();
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public void firstRecord() {setRecordNum(1);}
  public void previousRecord(){setRecordNum(getRecordNum() - 1);}
  public void nextRecord() {setRecordNum(getRecordNum() + 1);}
  public void lastRecord() {
    if (getRecordNum() == getRecordCount() + 1 && isDirty())
      setRecordNum(getRecordCount() + 1);
    else
      setRecordNum(getRecordCount());
  }
  public void newRecord() {
    if (getRecordNum() == getRecordCount() + 1 && isDirty())
      setRecordNum(getRecordCount() + 2);
    else
      setRecordNum(getRecordCount() + 1);
  }

  @Override
  public void addImpl(Component comp, Object constraints, int index) {
    if (comp == getContentPane() || comp == getNavigationPane())
      super.addImpl(comp, constraints, index);
    else
      getContentPane().add(comp, constraints, index);
  }

  public boolean isFormDirty() {return getContentPane().dirtyControlList.size() != 0;}

  public void setNavigationPane(DBNavigationPane navigationPane) {
    this.navigationPane = navigationPane;
    navigationPane.setSource(this);
  }
  
  public void setNavigationPaneVisible(boolean navigationPaneVisible) {
    if (this.navigationPaneVisible != navigationPaneVisible) {
      if (navigationPaneVisible) {
        add(getNavigationPane(), BorderLayout.SOUTH);     
      }
      else {
        remove(getNavigationPane());
      }
    }
    this.navigationPaneVisible = navigationPaneVisible;
  }
  
  public DBRecordSetContainer getSource() {return this;}
  
  // <editor-fold defaultstate="collapsed" desc=" Trivial Accessors/Mutators ">  

  public String getRecordSource() {return recordSource;}
  
  public DBNavigationPane getNavigationPane() {return navigationPane;}
  
  public Connection getConnection() {return connection;}
  public void setConnection(Connection connection) {this.connection = connection;}

  public Query getQuery() {return query;}
  protected void setQuery(Query query) {this.query = query;}
   
  public boolean getInsertsAllowed() {return insertsAllowed;}
  public void setInsertsAllowed(boolean insertsAllowed) {this.insertsAllowed = insertsAllowed;}  
  
  public boolean getDeletesAllowed() {return deletesAllowed;}
  public void setDeletesAllowed(boolean deletesAllowed) {this.deletesAllowed = deletesAllowed;}

  public AbstractDBContainer getContentPane() {return contentPane;}
  public void setContentPane(AbstractDBContainer contentPane) {this.contentPane = contentPane;}
  
  public boolean isNavigationPaneVisible() {return navigationPaneVisible;}
  
  public String getFilter() {return filter;}
  
  public String getSort() {return getQuery().getSort();}
  
// </editor-fold>
 

  public void ancestorAdded(AncestorEvent event) {
    if (getTopLevelAncestor() instanceof Window) {
      ((Window) getTopLevelAncestor()).addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          getContentPane().setWindowClosing(true);
          try {
            getContentPane().setWindowClosing(true);
            saveRecord();
            getContentPane().setWindowClosing(false);
          }
          catch (WindowClosingException ex) {
            if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(DBForm.this,
              "The current record cannot be saved at this time. Would you like to exit anyway?",
              "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE)) {
              getContentPane().setWindowClosing(false);
              throw ex; // Stop the window from closing
            }
          }
          // Don't let any problems prevent the window from closing
          catch (Exception ex) {} 
        }
      });
    }
  }
  public void ancestorRemoved(AncestorEvent event) {}
  public void ancestorMoved(AncestorEvent event) {}
  
  public void showDatabaseError(Exception ex) {
    if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(this,
      "There has been a database error: (" + ex.getMessage() + "). Would you like to continue anyway?",
      "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE)) {
      ((Window) getTopLevelAncestor()).dispose();
    }
  }
  
  protected void finalize() {
    getQuery().close();
  }
 
  // <editor-fold defaultstate="collapsed" desc=" Variable Declaration ">  
  
  protected Connection connection;
  
  // The query from which the forms ResultSet is generated 
  protected String recordSource;
  
  protected Query query;
  
  // The container that actually holds the controls (b/c we hold the contentPane
  // and the navPane
  private AbstractDBContainer contentPane;
  
  protected DBNavigationPane navigationPane;

  private boolean insertsAllowed = true;
  
  private boolean deletesAllowed = true;

  private boolean navigationPaneVisible = true;
  
  private String filter;
  
// </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Subform Functionality ">  
  public void dbFieldUpdated(DBFieldUpdateEvent evt) {
    setLinkValue(evt);
    // TODO 3 - Continue Subform coding from here
  }
  private void setLinkValue(DBFieldUpdateEvent evt) {
    String fieldName = evt.getFieldName();
    for (int i = 0; i < linkCount(); i++) {
      
    }
  }
  
  private int linkCount() {
    if (parentChildLink == null)
      return 0;
    else
      return parentChildLink.length;
  }
  public String[][] getParentChildLink() {return parentChildLink;}
  public void setParentChildLink(String[][] parentChildLink) {
    this.parentChildLink = parentChildLink;
    linkFieldInitialized = new boolean[linkCount()];
    linkFieldValues = new Object[linkCount()];
  }

  private String[][] parentChildLink = null;
  private boolean[] linkFieldInitialized = null;
  private Object[] linkFieldValues = null;
  
// </editor-fold>
  
}