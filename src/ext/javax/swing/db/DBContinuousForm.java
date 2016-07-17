/*
 * DBContinuousForm.java
 *
 * Created on March 5, 2008, 12:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import jentry.sql.swing.DBNavigationPane;
import ext.java.lang.ArraySupport;
import ext.java.sql.Query;
import ext.java.sql.event.RecordSetChangeEvent;
import ext.java.sql.event.RecordSetChangeListener;
import ext.java.util.VetoException;
import ext.javax.swing.WindowClosingException;
import ext.javax.swing.db.event.ContinuousFormEvent;
import ext.javax.swing.db.event.ContinuousFormListener;
import ext.javax.swing.db.event.FormFieldUpdateListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

// TODO 3 - what happens if a Control is added to a child form?

/**
 * DBContinuousForm is a container that allows the end-ser to view multiple
 * database recors simlutaniously, laid out one after the other. There are two
 * ways that the user can set up the subforms. The first way is to subclass
 * DBContinuousForm.Subform and add all of the controls and set up any
 * dependencies in the constructor. Then pass the Class of the subclass to
 * setSubformClass. The alternative is to register a ContinuousFormListener
 * which will be informed each time a subform is added or removed (including
 * when the underlying RecordSet is set). Then whenever a subform is added the
 * listener can add DBFieldControls or any other controls.
 *
 * Initialize a DBContinuousForm by first providing a Connection object and then
 * setting the recordSource. Aside for setting up a single Subform, all of the
 * rest of the work is done automatically.
 *
 * See DBRecordSetContainer for additional information.
 *
 * @author Menachem Schachter
 */

public class DBContinuousForm extends JPanel implements DBRecordSetContainer, RecordSetChangeListener, ContainerListener, AncestorListener {

  // <editor-fold defaultstate="collapsed" desc=" Constructors ">  
  
  /**
   * Creates a new instance of DBContinuousForm
   * Subforms will be layed out in a single vertical column
   */
  public DBContinuousForm() {this(0, 1);}

  /**
   * Creates a new instance of DBContinuousForm
   * Subforms will be layed out using a GridLayout with the number of rows/columns
   * specified - see the documentation of GridLayout for the specifics
  */
  public DBContinuousForm(int rows, int columns) {
    super(new BorderLayout());
    
    setContentPane(new JPanel(new GridLayout(rows, columns)));
    layoutPanes(rows, columns);
    
    getContentPane().addContainerListener(this);
    addAncestorListener(this);
    
    setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Public Interface Methods ">  
  public void setSubformTemplate(Class subformClass) {
    this.subformClass = subformClass;
    
    // Force a clone so that any exceptions that might occur are thrown to the
    // setter and we can be assure thet will not occur later
    try {
      cloneTemplate();
    } catch (Exception ex) {throw new IllegalArgumentException(
        "Invalid Template Class: " + getSubformClass());}
  }
  
  public void setRecordSource(String recordSource) throws SQLException {
    this.recordSource = recordSource;
    Query q = new Query(getConnection(), getRecordSource());
    q.addRecordSetChangeListener(this);
    setQuery(q);
    q.initialize();
  }

  public void setNavigationPane(DBNavigationPane navigationPane) {
    this.navigationPane = navigationPane;
    if (navigationPane != null) {
      navigationPane.setSource(this);
      addRecordSetChangeListener(navigationPane);
    }
  }

  public void setInsertsAllowed(boolean insertsAllowed) {
    if(this.insertsAllowed == insertsAllowed) return;

    // Update the GUI
    if (getInsertRow() != null) {
      if (insertsAllowed)
        getContentPane().add(getInsertRow());
      else
        getContentPane().remove(getInsertRow());
    }
    if (getNavigationPane() != null)
      getNavigationPane().updateButtons();
    this.insertsAllowed = insertsAllowed;
  }  
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Helper Methods ">  
  protected void layoutPanes(int rows, int columns) {
    scrollPane.setBorder(null);
    add(scrollPane, BorderLayout.CENTER);

    JPanel panel = new JPanel(new BorderLayout());
    scrollPane.setViewportView(panel);

    JPanel panel2 = new JPanel(new BorderLayout());
    setNavigationPane(new DBNavigationPane());

    // Add the NavigationPane
    panel.add(panel2, BorderLayout.CENTER);
    panel.add(getNavigationPane(), BorderLayout.SOUTH);
    
    // We need to allow for extra vertical and horizontal space,
    // depending on the particular layout
    if (rows == 0 && columns == 1)
      panel2.add(getContentPane(), BorderLayout.NORTH);
    else if (rows == 1 && columns == 0)
      panel2.add(getContentPane(), BorderLayout.WEST);
    else {
      JPanel panel3 = new JPanel(new BorderLayout());
      panel2.add(panel3, BorderLayout.NORTH);
      panel3.add(getContentPane(), BorderLayout.WEST);
    }
  }

  protected Subform cloneTemplate() throws InstantiationException, IllegalAccessException {
    if (getSubformClass() != null)
      return (Subform) getSubformClass().newInstance();
    
    // Use Default
    Subform subform = new Subform();
    subform.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return subform;
  }

  public Subform getCurrentSubform() {
    return getSubform(getRecordNum() - 1);
  }
  
  public Subform getSubform(int index) {
    try {
      return (Subform) getContentPane().getComponent(index);
    } catch (Exception e) {return null;}
  }
  
  protected void showDatabaseError(Exception ex) {
    if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(this,
      "There has been a database error: (" + ex + "). Would you like to continue anyway?",
      "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE)) {
      ((Window) getTopLevelAncestor()).dispose();
    }
  }
  
  /**
   * This method is called by a subform when the user clicks on it or on any of
   * its subcomponents. We find which number form it is and set the recordNumber
   */
  protected void subformSelected(Subform subform) {
    for (int i = 0; i < getContentPane().getComponentCount(); i++)
      if (subform == getContentPane().getComponent(i) && subform != getCurrentSubform())
        setRecordNum(i + 1);
  }
  
  //TODO 3 - Continue commenting from here
  protected boolean isDirty() {
    return getQuery().isDirty() || (getCurrentSubform() != null && getCurrentSubform().dirtyControlList.size() != 0); 
  } 
  
	@Override
  protected void finalize() {
    getQuery().close();
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" RecordSetChange Interface ">  
  public void recordSelected(RecordSetChangeEvent evt) {
    fireRecordSelected(evt);    
    Subform subform = getCurrentSubform();
    if (subform == null) {
      if (getRecordNum() > 1)
        previousRecord();
      return;
    }
    Component comp = getSelectedComponent();
    Component toFocus = null;

    if (comp != null) {
      if (subform.isAncestorOf(comp))
        toFocus = comp;
      else if (comp.getParent() instanceof Subform) {
        int index = ArraySupport.indexOf(comp.getParent().getComponents(), comp);
        if (index != -1 && subform.getComponentCount() > index && comp.getClass().equals(subform.getComponent(index).getClass()))
          toFocus = subform.getComponent(index);
      }
    }
    if (toFocus == null && subform.getComponentCount() > 0)
      toFocus = subform.getComponent(0);
    if (toFocus != null)
      toFocus.requestFocusInWindow();
    
    subform.recordSelected(evt);
  }
  public void recordSetChanged(RecordSetChangeEvent evt) {
    fireRecordSetChanged(evt);
    // Remove all previous componenets
    getContentPane().removeAll();
    
    Subform subform = null;
    int count = getQuery().getRecordCount();
    if (getInsertsAllowed()) count++;
    
    for (int i = 1; i <= count; i++) {
      // We are garuenteed that no Exceptions will be throw b/c we tested earlier
      try {
        subform = cloneTemplate();
      } catch (Exception ex) {}
      
      getContentPane().add(subform);
      subform.setContinuousForm(this);
      try {
        // This causes a RecordSelectedEvent to be generated which causes the subform pto be populated
        getQuery().setRecordNum(i);
      } catch (SQLException ex) {showDatabaseError(ex);}
    }
  }
  public void recordWillBeInserted(RecordSetChangeEvent evt) throws VetoException {
    fireRecordWillBeInserted(evt);
    getCurrentSubform().recordWillBeInserted(evt);
  }
  public void recordInserted(RecordSetChangeEvent evt) {
    fireRecordInserted(evt);
    Subform subform = null;
    // We are gaurenteed that no Exceptions will be throw b/c we tested earlier
    try {
      subform = cloneTemplate();
    } catch (Exception ex) {}
    setInsertRow(subform);
    if (getInsertsAllowed()) {
      getContentPane().add(subform);
      
    }
    subform.setContinuousForm(this);
    try {
      // This causes a RecordSelectedEvent to be generated which causes the subform pto be populated
      getQuery().setRecordNum(getRecordCount() + 1);
      // Return to the previousRecord
      getQuery().setRecordNum(getRecordCount());
    } catch (SQLException ex) {showDatabaseError(ex);}
    revalidate();
  }
  public void recordDirtied(RecordSetChangeEvent evt) {
    fireRecordDirtied(evt);
  }
  public void recordWillBeUpdated(RecordSetChangeEvent evt) throws VetoException {
    fireRecordWillBeUpdated(evt);
  }
  public void recordUpdated(RecordSetChangeEvent evt) {
    fireRecordUpdated(evt);
  }
  public void recordWillBeDeleted(RecordSetChangeEvent evt) throws VetoException {
    fireRecordWillBeDeleted(evt);
    int delete = JOptionPane.showConfirmDialog(this, "Do you want to delete the selected record?", "Delete?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    if (delete == JOptionPane.NO_OPTION || delete == JOptionPane.CLOSED_OPTION)
      throw new VetoException();
  }
  public void recordDeleted(RecordSetChangeEvent evt) {
    fireRecordDeleted(evt);
    getContentPane().remove(getCurrentSubform());
    revalidate();
  }
   // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" ConatinerListener Interface ">  
  public void componentAdded(ContainerEvent e) {
    if (e.getChild() instanceof Subform) {
      fireSubformAdded(new ContinuousFormEvent(this, (Subform) e.getChild()));
    }
  }
  public void componentRemoved(ContainerEvent e) {
    if (e.getChild() instanceof Subform) {
      fireSubformRemoved(new ContinuousFormEvent(this, (Subform) e.getChild()));
    }
  }
    // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" DBRecordSetContainer Interface ">
  public int getRecordNum() {return getQuery().getRecordNum();}
  public boolean setRecordNum(int recordNum) {
    if (recordNum == getRecordNum()) return true;
    try {
      if (getCurrentSubform() != null)
        getCurrentSubform().updateDirtyFields();
        return getQuery().setRecordNum(recordNum);
    } catch (SQLException ex) { 
      showDatabaseError(ex);
      return false;
    }
  }
  public int getRecordCount() {return getQuery().getRecordCount();}
  public Class getFieldType(String fieldName) {return getQuery().getFieldType(fieldName);}
  public void setFilter(String filter) {
    try {
      getQuery().setFilter(filter);
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public String getFilter() {
      return getQuery().getFilter();
  }
  public void setSort(String sort) {
    try { 
      getQuery().setSort(sort);
    } catch (SQLException ex) {showDatabaseError(ex);}
  }
  public String getSort() {
      return getQuery().getSort();
  }

  public void setNavigationPaneVisible(boolean navigationPaneVisible) {
    // The container of the navigation pane
    Container cont = (Container) ((Container)getComponent(0)).getComponent(0);
    
    if (this.navigationPaneVisible != navigationPaneVisible) {
      if (navigationPaneVisible) {
        cont.add(getNavigationPane(), BorderLayout.SOUTH);     
      }
      else {
        cont.remove(getNavigationPane());
      }
    }
    this.navigationPaneVisible = navigationPaneVisible;
  }
  
  public void saveRecord() {
    if (getCurrentSubform() != null)
      getCurrentSubform().updateDirtyFields();
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
    getCurrentSubform().updateDirtyFields();
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
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Anscestor Listener Interface ">  
  public void ancestorAdded(AncestorEvent event) {
    if (getTopLevelAncestor() instanceof Window) {
      ((Window) getTopLevelAncestor()).addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          try {
            getCurrentSubform().setWindowClosing(true);
            saveRecord();
            getCurrentSubform().setWindowClosing(false);
          }
          catch (WindowClosingException ex) {
            if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(DBContinuousForm.this,
              "The current record cannot be saved at this time. Would you like to exit anyway?",
              "Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE)) {
              getCurrentSubform().setWindowClosing(false);
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


  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Event Methods ">  
  /**
   * Utility field holding list of ContinuousFormListeners.
   */
  private transient java.util.ArrayList continuousFormListenerList;

  /**
   * Registers ContinuousFormListener to receive events.
   * @param listener The listener to register.
   */
  public synchronized void addContinuousFormListener(ContinuousFormListener listener) {
    if (continuousFormListenerList == null ) {
      continuousFormListenerList = new java.util.ArrayList ();
    }
    continuousFormListenerList.add (listener);
  }

  /**
   * Removes ContinuousFormListener from the list of listeners.
   * @param listener The listener to remove.
   */
  public synchronized void removeContinuousFormListener(ContinuousFormListener listener) {
    if (continuousFormListenerList != null ) {
      continuousFormListenerList.remove (listener);
    }
  }

  /**
   * Notifies all registered listeners about the event.
   * 
   * @param event The event to be fired
   */
  private void fireSubformAdded(ContinuousFormEvent event) {
    java.util.ArrayList list;
    synchronized (this) {
      if (continuousFormListenerList == null) return;
      list = (java.util.ArrayList)continuousFormListenerList.clone ();
    }
    for (int i = 0; i < list.size (); i++) {
      ((ContinuousFormListener)list.get (i)).subformAdded (event);
    }
  }

  /**
   * Notifies all registered listeners about the event.
   * 
   * @param event The event to be fired
   */
  private void fireSubformRemoved(ContinuousFormEvent event) {
    java.util.ArrayList list;
    synchronized (this) {
      if (continuousFormListenerList == null) return;
      list = (java.util.ArrayList)continuousFormListenerList.clone ();
    }
    for (int i = 0; i < list.size (); i++) {
      ((ContinuousFormListener)list.get (i)).subformRemoved (event);
    }
  }

  public void addRecordSetChangeListener(RecordSetChangeListener listener) {
    recordSetChangeListenerList.add (listener);
  }
  public void removeRecordSetChangeListener(RecordSetChangeListener listener) {
    recordSetChangeListenerList.remove (listener);
  }

  public void fireRecordSelected(RecordSetChangeEvent evt) {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordSelected(evt);
    }
  }
  public void fireRecordSetChanged(RecordSetChangeEvent evt) {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordSetChanged(evt);
    }
  }
  public void fireRecordWillBeInserted(RecordSetChangeEvent evt) throws VetoException {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordWillBeInserted(evt);
    }
  }
   public void fireRecordInserted(RecordSetChangeEvent evt) {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordInserted(evt);
    }
  }

   private void fireRecordDirtied(RecordSetChangeEvent evt) {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordDirtied(evt);
    }
  }

  private void fireRecordWillBeUpdated(RecordSetChangeEvent evt) throws VetoException {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordWillBeUpdated(evt);
    }
  }

  private void fireRecordUpdated(RecordSetChangeEvent evt) {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordUpdated(evt);
    }
  }
  private void fireRecordWillBeDeleted(RecordSetChangeEvent evt) throws VetoException {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordWillBeDeleted(evt);
    }
  }

  private void fireRecordDeleted(RecordSetChangeEvent evt) {
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordDeleted(evt);
    }
  }
  // </editor-fold>  
  
  // <editor-fold defaultstate="collapsed" desc=" Trivial Accessors/Mutators ">  

  public String getRecordSource() {return recordSource;}
  
  public Connection getConnection() {return connection;}
  public void setConnection(Connection connection) {this.connection = connection;}

  public Query getQuery() {return query;}
  protected void setQuery(Query query) {this.query = query;}

  protected Component getSelectedComponent() {return selectedComponent;}
  protected void setSelectedComponent(Component selectedComponent) {this.selectedComponent = selectedComponent;}

  protected JPanel getContentPane() {return contentPane;}
  protected void setContentPane(JPanel contentPane) {this.contentPane = contentPane;}

  public boolean getDeletesAllowed() {return deletesAllowed;}
  public void setDeletesAllowed(boolean deletesAllowed) {this.deletesAllowed = deletesAllowed;}

  public Subform getInsertRow() {return insertRow;}
  public void setInsertRow(Subform insertRow) {this.insertRow = insertRow;}

  public boolean getInsertsAllowed() {return insertsAllowed;}
  
  public Class getSubformClass() {return subformClass;}

  public DBNavigationPane getNavigationPane() {return navigationPane;}

  public void setHorizontalScrollBarPolicy(int policy) {
    if (scrollPane != null) scrollPane.setHorizontalScrollBarPolicy(policy);
  }

  public void setVerticalScrollBarPolicy(int policy) {
    if (scrollPane != null) scrollPane.setVerticalScrollBarPolicy(policy);
  }

  public String[][] getParentChildLink() {return parentChildLink;}
  public void setParentChildLink(String[][] parentChildLink) {this.parentChildLink = parentChildLink;}
  
  public boolean isNavigationPaneVisible() {return navigationPaneVisible;}
  
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Variable Declararion ">  
  private Connection connection = null;
  
  // The query from which the forms ResultSet is generated 
  private String recordSource = null;
  
  private Query query = null;
  
  private Class subformClass = null;
  
  private DBNavigationPane navigationPane = null;
  
  private Component selectedComponent = null;
  
  private JScrollPane scrollPane = new JScrollPane();
  
  private JPanel contentPane = null;
  
  private boolean insertsAllowed = true;
  
  private boolean deletesAllowed = true;
  
  private Subform insertRow = null;
  
  private transient ArrayList<RecordSetChangeListener> recordSetChangeListenerList = new ArrayList<RecordSetChangeListener>();

  private String[][] parentChildLink = null;
  
  private boolean navigationPaneVisible = true;
  
  // </editor-fold>  

  public static class Subform extends AbstractDBContainer implements FormFieldUpdateListener, ContainerListener, MouseListener, FocusListener {

    /**
     * Creates a new instance of Subform
     */
    public Subform() {
      super();
      addMouseListener(this);
    }

    protected Query getQuery() {
      if (getContinuousForm() == null)
        return null;
      return getContinuousForm().getQuery();
    }

    // Implement ContainerListener Interface
    public void componentAdded(ContainerEvent evt) {
      registerSubcomponent(evt.getChild());
      if (evt.getContainer() == this)
        super.componentAdded(evt);
    }
    protected void registerSubcomponent(Component comp) {
      comp.addMouseListener(this);
      comp.addFocusListener(this);

      if (comp instanceof Container) {
        Container cont = (Container) comp;
        // Register all subcontrols
        int count = cont.getComponentCount();
        for (int i = 0; i < count; i++) {
          registerSubcomponent(cont.getComponent(i));
        }
        // Make sure we are notified when future subcontrols are added
        cont.addContainerListener(this);
      }
    }
    public void componentRemoved(ContainerEvent evt) {
      deregisterSubcomponent(evt.getChild());
      if (evt.getContainer() == this)
        super.componentRemoved(evt);
    }
    protected void deregisterSubcomponent(Component comp) {
      comp.removeMouseListener(this);
      comp.removeFocusListener(this);

      if (comp instanceof Container) {
        Container cont = (Container) comp;
        // Remove all subcontrols
        int count = cont.getComponentCount();
        for (int i = 0; i < count; i++) {
          deregisterSubcomponent(cont.getComponent(i));
        }
        cont.removeContainerListener(this);
      }
    }

    // Implement MouseListener
    public void mousePressed(MouseEvent e) {
      getContinuousForm().setSelectedComponent((Component) e.getSource());
      getContinuousForm().subformSelected(this);
    }
      public void mouseClicked(MouseEvent e) {}
      public void mouseReleased(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}

  // Implement FocusListener    
    public void focusGained(FocusEvent e) {
      getContinuousForm().setSelectedComponent(e.getComponent());
      getContinuousForm().subformSelected(this);
    }
    public void focusLost(FocusEvent e) {}

    public void showDatabaseError(Exception ex) {
      getContinuousForm().showDatabaseError(ex);
    }

    DBContinuousForm getContinuousForm() {return continuousForm;}
    void setContinuousForm(DBContinuousForm continuousForm) {this.continuousForm = continuousForm;}

    public DBRecordSetContainer getSource() {return getContinuousForm();}

    public DBNavigationPane getNavigationPane() {
      if (getContinuousForm() == null)
        return null;
      else
        return getContinuousForm().getNavigationPane();
    }

    DBContinuousForm continuousForm = null;
  }  
}