/*
 * DBComboBox.java
 *
 * Created on March 24, 2008, 5:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import ext.java.lang.NumberSupport;
import ext.java.sql.Query;
import ext.java.sql.TypeConversionSupport;
import ext.java.util.ListSupport;
import ext.javax.swing.WindowClosingException;
import ext.javax.swing.db.event.DBFieldUpdateEvent;
import ext.javax.swing.db.event.FormFieldUpdateEvent;
import ext.javax.swing.db.event.FormFieldUpdateListener;
import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * DBComboBox has 2 seperate database related functionalities that can be used
 * independantly or together
 * First, DBComboBox is an DBFieldControl; therfore when added to an AbstractDBContainer
 * it will automatically reflect the value in the database column with the name fieldValue.
 * And since DBComboBox is also a JComboBox, the user can easily set a list of
 * possible values, either using the constructor parameter items or using the method
 * addItem
 * Second, when passed a Connection and a recordSource (a String to use as a
 * database query), the DBComboBox can populate its item list with the qury result.
 * The values displayed in the ComboBox are determined by displayColumn, while the
 * underlying value of the DBComboBox, which can be retrieved using getFieldValue
 * is determined by valueColumn.
 * For example, consider the following database table named NAMES with two columns,
 * the first named "key" and the second named "name". The table currently has 3
 * entries (1, 'Bob'), (3, 'Jim'), (4, 'Jane'). Once the properties connection,
 * displayColumn, and valueColumn have been set, set the recordSource to "NAMES".
 * By default displayColumn and valueColumn are both 1. which means the ComboBox
 * would display 1,3,4. If both displayColumn and valueColumn are set to 2,
 * then the ComboBox would display 'Bob', 'Jim', 'Jane'. If valueColumn is set
 * to 1 and the displayColumn to 2 then the display would be 'Bob', 'Jim', 'Jane';
 * however, ifthe end-user selects 'Bob' then getFieldValue would return 1 and if
 * 'Jane' is selected, it would return 4
 * If the ComboBox is editable, the end-user will be able to enter display values
 * which have no associated value. For example in the final example above, if the
 * end-user enters 'Martin' there would be no associated value for getFieldValue
 * to return. The default response is to display an error message; however by setting
 * the providing a custom UnlistedSelectionHandler, the user can provide their own
 * functionality, such as added a new entry into the databse. See
 * UnlistedSelectionHandler
 * Finally, DBComboBox can combine both functionalities by reflecting a database
 * field while at the same time providing choices from a different (or the same)
 * database table.
 * A problem that can arise with this, is if the current value of the field does
 * not have an associated display value, what should be displayed. By default,
 * an error message is dispplayed; however, however by  providing a custom
 * UnlistedSelectionHandler, the user can provide their own functionality. See
 * UnlistedSelectionHandler 
 *
 * @author Menachem Schachter
 */

public class DBComboBox extends JComboBox implements DBFieldControl, FocusListener, DocumentListener {
  
  // <editor-fold defaultstate="collapsed" desc=" Constructors ">  

  /**
   * Creates a new instance of DBComboBox
   */
  public DBComboBox() {this(null, new Object[0]);}
  public DBComboBox(Object... items) {this(null, items);}
  public DBComboBox(String fieldName) {this(fieldName, new Object[0]);}
  public DBComboBox(String fieldName, Object... items) {
    super(items);
    setFieldName(fieldName);
    setRenderer(new DBComboBoxRenderer());
    setEditor(new DBComboBoxEditor());
    
    // Recieve notification when a list item is selected
    addActionListener(actionListener);
    
    addFocusListener(this);
    // Recieve focus notifications even if the ComboBox is editable
    getEditor().getEditorComponent().addFocusListener(this);
    
    // Listen for changes to the text when the ComboBox is editable
    addDocumentListener(this);
  }
  // </editor-fold>    
 
  // <editor-fold defaultstate="collapsed" desc=" Key Methods ">  
  
  public void dbFieldUpdated(DBFieldUpdateEvent evt) {
    DBRecordSetContainer db = evt.getSource();
    
    // We may need to know the field type. so store it
    if (getFieldType() == null)
      setFieldType(db.getFieldType(getFieldName()));
    
    Object val = null;
    
    // If not at the insert row
    if (db.getRecordNum() != db.getRecordCount() + 1) {
      val = evt.getValue();
      // If there is a recordSource, then we need to display the item in 
      // the display column that corresponds to the database value in the value column
      if (getRecordSource() != null) {
        int index = ListSupport.liberalIndexOf(valueList, val);
        if (index != -1)
          val = getItemAt(index);
      }
      
      /*If there is no corresponding display value, let the unlistedSelectionHandler
        take a crack at the problem. If there isn't one or if it returns UNHANDELED,
        we'll have to do our own error handling. If it returns HANDLED we do nothing.
        Otherwise, assumethe problem has been addressed and continue with the new value.
        See handleUnlistedDBValue */
      if (getRecordSource() != null && getDisplayColumn() != getValueColumn() &&
          !containsItem(val)) {
        val = handleUnlistedDBValue(val);
        if (val == UnlistedSelectionHandler.HANDLED)
          return;
      }
      setInitialSelectedItem(val);
    }
    
    // If we're at the insert row, use the default value. Use get getFieldValue
    // to force error checking
    else {
      setInitialSelectedItem(getDefaultValue());
      getFieldValue();
    }
  }
  
  /** Do not set the record source until both displayColumn and valueColumn have been set */
  public void setRecordSource(String recordSource) throws SQLException {
    this.recordSource = recordSource;
    requery();
  }
  
  public void requery() throws SQLException {
    String query = Query.formalizeQuery(recordSource);
    
    if (query == null) throw new RuntimeException("Record Source cannot be null");
    if (getConnection() == null) throw new RuntimeException("Connection cannot be null");
    
    ResultSet rs  = getConnection().prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery();
    valueList = new ArrayList();
    
    //Do not respond to resetting the list
    removeActionListener(actionListener);
    removeDocumentListener(this);
    
    // Store the current selection so we can reset it after the list is repopulated
    Object selected = getSelectedItem();
    removeAllItems();
  
    // Add the display values to the comboBox and thir corresponding values
    // to the valueList for later retrieval
    while (rs.next()) {
      Object o = TypeConversionSupport.getFieldValue(rs, getValueColumn());
      valueList.add(o);
      if (getDisplayColumn() == getValueColumn())
        addItem(o);
      else
        addItem(TypeConversionSupport.getFieldValue(rs, getDisplayColumn()));
    }

    setSelectedItem(selected);
    
    addActionListener(actionListener);
    addDocumentListener(this);
    
    // Set the field type (or value type if there is no field) and the display
    // type to be used in String to Object conversions
    int type = rs.getMetaData().getColumnType(getValueColumn());
    setFieldType(TypeConversionSupport.getJavaClass(type));
    type = rs.getMetaData().getColumnType(getDisplayColumn());
    setDisplayType(TypeConversionSupport.getJavaClass(type));
    
    try {
      rs.close();
    } catch (Exception ex) {}
  }
  
  /** Returns the value of the currently selected item, or if the ComboBox is
   * editable, an Object representation of the String. The Object will be of
   * Class type. If valueColumn != displayColumn the value returned will be
   * the value that corresponds to the display value selected or entered
   */
  public Object getFieldValue() {
    Object val = null;

    try {
      String text;
      // If the ComboBox is editable then we want the current text of the editor
      // field, otherwise we want the current selection
      if (isEditable())
        text = ((JTextField) getEditor().getEditorComponent()).getText();
      else
        text = toString(getSelectedItem());

      // If valueColumn != displayColumn then the value we have is the display
      // value and we need to get the corresponding value
      if (getRecordSource() != null && getValueColumn() != getDisplayColumn()) {
        if (containsString(text)) {
          if (isEditable())
            val = valueList.get(indexOfString(text));
          else
            val = valueList.get(getSelectedIndex());
        }
        // If there is no corresponding value, give the unlistedSelectionHandler
        // a crack at the problem (if there is one) and respond accordingly
        // See UnlistedSelectionHandler.unlistedSelection for more information

        else {
          handleUnlistedSelection(text);
        }
      }
      else
        val = toObject(text, getFieldType());
    } catch (Exception ex) {
      // Display an error message and take back the focus
      JOptionPane.showMessageDialog(getParent(), getFormatErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);

      // The following is a workaround for a bug that causes the requestFocus method to generate another FocusLostEvent
      SwingUtilities.invokeLater(new Runnable() {
        public void run(){
          if (isEditable())
            getEditor().getEditorComponent().requestFocusInWindow();
          else
            requestFocusInWindow();
        }
      });

      // If the window is being closed throw an exception to allow the
      // possibility of the window close being canceled
      if (((AbstractDBContainer) getParent()).isWindowClosing())
        throw new WindowClosingException();
    }
    
    return val;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc=" Helper Methods ">  
  
  /** Convert the target Object to a String using the loaded Formatter */
  protected String toString(Object val) {
    return TypeConversionSupport.objectToString(val, getFormatter());
  }
  
  /** Convert the target String to an Object with class type using the loaded Formatter */
  protected Object toObject(String s, Class type) throws Exception {
    return TypeConversionSupport.stringToObject(s, type, getFormatter());
  }

  /** Returns true if anObject is an item in the ComboBox list, false if otherwise */
  public boolean containsItem(Object anObject) {
    return (indexOf(anObject) != -1);
  }
  
  /** Returns the index of the first occurance of anObject in the ComboBox list
   * or -1 if it isn't in the list
   */
  public int indexOf(Object anObject) {
    for (int i = 0; i < getItemCount(); i++) {
      if (anObject != null && anObject.equals(getItemAt(i)))
        return i;
    }
    return -1;
  }
  
  /** Returns true if there is an item in the ComboBox list whose String
   * reprsenetation is equal to s, false if otherwise
   */
  public boolean containsString(String s) {
    return (indexOfString(s) != -1);
  }
  
  /** Returns the index of the first occurance of an Object in the ComboBox list
   * whose whose String reprsenetation is equal to s or -1 if it isn't in the list
   */
  public int indexOfString(String s) {
    for (int i = 0; i < getItemCount(); i++) {
      if (toString(getItemAt(i)).equals(s))
        return i;
    }
    return -1;
  }
  protected void addDocumentListener(DocumentListener l) {
    ((JTextField) getEditor().getEditorComponent()).getDocument().addDocumentListener(l);
  }

  protected void removeDocumentListener(DocumentListener l) {
    ((JTextField) getEditor().getEditorComponent()).getDocument().removeDocumentListener(l);
  }

  protected Object handleUnlistedDBValue(Object value) {
    boolean handled = false; 
    if (unlistedSelectionHandler != null) {
      value = unlistedSelectionHandler.unlistedDBValue(this, value);
      if (value != UnlistedSelectionHandler.UNHANDLED)
        handled = true;
    }
    if (!handled) {
      // A typical situation is where the lookup value is 0 which would mean there
      // is no corresponding record. Since this makes sense we should not display
      // an error message
      if (!(value instanceof Number && NumberSupport.equals((Number) value, 0)))
        JOptionPane.showMessageDialog(getParent(),
          "There has been a DBComboBox data error: there is no record with a value of "
          + value + ".", "Error", JOptionPane.ERROR_MESSAGE);

      setInitialSelectedItem("");
      value = UnlistedSelectionHandler.HANDLED;
    }
    return value;
  }
  
  protected Object handleUnlistedSelection(String text) throws Exception {
    Object val = null;
    boolean handled = false;
    if (unlistedSelectionHandler != null) {
      val = unlistedSelectionHandler.unlistedSelection(this, text);
      if (val != UnlistedSelectionHandler.UNHANDLED)
        handled = true;
      if (val == UnlistedSelectionHandler.HANDLED)
        val = "";
    }
    if (!handled) {
      // If the display value is "", this probably means the associated
      // value should probably be trivial
      if (text.equals("")) {
        Class type = getFieldType();
        if (type.isPrimitive()) {
          // All the relevant primitives can accept a value of (byte) 0, expect for boolean
          if (type.equals(boolean.class))
            val = false;
          else
            val = (byte) 0;
        }
        else { // All non-privitives should be assigned null
          val = null;
        }
      }
      else
        throw new Exception("There is no value associated with the selected item");
    }
    return val;
  }
    
  /** Sets the selected item without causing the ComboBox to be dirtied*/
  protected void setInitialSelectedItem(Object value) {
    removeActionListener(actionListener);
    removeDocumentListener(this);
    setSelectedItem(value);
    addActionListener(actionListener);
    addDocumentListener(this);
  }
 
  // </editor-fold>    
 
  // <editor-fold defaultstate="collapsed" desc=" Implement FocusListener and DocumentListener ">  
  public void focusLost(FocusEvent e) {
    // Force a parse, so that any invalid formatting will be handled immediatly
    if (e.getOppositeComponent() != null) // will be null if another window has taken the focus
      getFieldValue();
    fireFormFieldUpdated();
  }

  public void focusGained(FocusEvent e) {}
  
  public void insertUpdate(DocumentEvent e) {
    fireFormFieldDirtied();
  }

  public void removeUpdate(DocumentEvent e) {
    fireFormFieldDirtied();
  }

  public void changedUpdate(DocumentEvent e) {}
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc=" Event Listener Methods ">  
  protected transient ArrayList<FormFieldUpdateListener> fieldUpdateListenerList = new ArrayList<FormFieldUpdateListener>();

  public void addFormFieldUpdateListener(FormFieldUpdateListener listener) {
    fieldUpdateListenerList.add (listener);
  }

  public void removeFormFieldUpdateListener(FormFieldUpdateListener listener) {
    fieldUpdateListenerList.remove (listener);
  }

  private void fireFormFieldUpdated() {
    FormFieldUpdateEvent evt = new FormFieldUpdateEvent(this);
    for (FormFieldUpdateListener listener : fieldUpdateListenerList) {
      listener.formFieldUpdated(evt);
    }
  }

  private void fireFormFieldDirtied() {
    FormFieldUpdateEvent evt = new FormFieldUpdateEvent(this);
    for (FormFieldUpdateListener listener : fieldUpdateListenerList) {
      listener.formFieldDirtied(evt);
    }
  }  
  // </editor-fold>  

  // <editor-fold defaultstate="collapsed" desc=" Trivial Accessors/Mutators ">  
  public String getFieldName() {return fieldName;}
  public void setFieldName(String fieldName) {this.fieldName = fieldName;}
 
  /** If displayColumn != valueColumn, the default value should be a display column value*/
  public Object getDefaultValue() {return defaultValue;}
  
  /** If displayColumn != valueColumn, the default value should be a display column value*/
  public void setDefaultValue(Object defaultValue) {this.defaultValue = defaultValue;}
  
  public Format getFormatter() {return formatter;}
  public void setFormatter(Format formatter) {this.formatter = formatter;}

  public String getFormatErrorMessage() {return formatErrorMessage;}
  public void setFormatErrorMessage(String formatErrorMessage) {this.formatErrorMessage = formatErrorMessage;}

  protected Class getFieldType() {return fieldType;}
  protected void setFieldType(Class fieldType) {this.fieldType = fieldType;}

  public Connection getConnection() {return connection;}
  public void setConnection(Connection connection) {this.connection = connection;}
  
  public int getDisplayColumn() {return displayColumn;}
  public void setDisplayColumn(int displayColumn) {this.displayColumn = displayColumn;}
  
  public int getValueColumn() {return valueColumn;}
  public void setValueColumn(int valueColumn) {this.valueColumn = valueColumn;}

  protected Class getDisplayType() {return displayType;}
  protected void setDisplayType(Class displayType) {this.displayType = displayType;}
  
  public UnlistedSelectionHandler getUnlistedSelectionHandler() {return unlistedSelectionHandler;}
  public void setUnlistedSelectionHandler(UnlistedSelectionHandler unlistedSelectionHandler) {this.unlistedSelectionHandler = unlistedSelectionHandler;}
  
  public String getRecordSource() {return recordSource;}
  // </editor-fold>  

  // <editor-fold defaultstate="collapsed" desc=" Variable Declaration ">
  private String fieldName = null;
  private Object defaultValue = null;
  private Format formatter = null;
  private String formatErrorMessage = "The value you entered isn't valid for this field.";
  private Class fieldType = null;
  private Class displayType = null;
  
  private Connection connection;
  private String recordSource;

  // The 1-based column number of the underlying resultSet to display
  private int displayColumn = 1;
  
  // The 1-based column number of the underlying resultSet to use as a return value
  private int valueColumn = 1;

  protected ArrayList valueList = null;
  
  private UnlistedSelectionHandler unlistedSelectionHandler = null;
  
  // DBComboBox cannot handle ActionEvents directly b/c its superclass needs to
  // process all ActionEvents, and DBComboBox needs to ignore some
  private ActionListener actionListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      fireFormFieldUpdated();
    }
  };
  // </editor-fold>  

  // <editor-fold defaultstate="collapsed" desc=" Helper Classes ">  
  private class DBComboBoxRenderer implements ListCellRenderer {  
    ListCellRenderer renderer;
    
    DBComboBoxRenderer() {renderer = getRenderer();}
            
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      String s = TypeConversionSupport.objectToString(value, getFormatter());
      return renderer.getListCellRendererComponent(list, s,
      index, isSelected, cellHasFocus);
    }
  }
  
  private class DBComboBoxEditor implements ComboBoxEditor {
    ComboBoxEditor editor;

    DBComboBoxEditor() {editor = getEditor();}
    
    public Component getEditorComponent() {return editor.getEditorComponent();}

    public void setItem(Object anObject) {
    String s;
    if (anObject instanceof String)
      s = (String) anObject;
    else
      s = TypeConversionSupport.objectToString(anObject, getFormatter());
      editor.setItem(s);
    }

    public Object getItem() {return editor.getItem();}

    public void selectAll() {editor.selectAll();}

    public void addActionListener(ActionListener l) {editor.addActionListener(l);}

    public void removeActionListener(ActionListener l) {editor.removeActionListener(l);}
  }
  // </editor-fold>  

  // <editor-fold defaultstate="collapsed" desc=" UnlistedSelectionHandler Interface ">    
 /**
   * Classes that implement this interface can be passed to a DBComboBox in order
   * to provide custom error handling in two situations. See the 2 methods for
   * further details
   */
  public static interface UnlistedSelectionHandler {
    public static Object UNHANDLED = new Object();
    public static Object HANDLED = new Object();
    /**
     * The DBComboBox will call this method if the displayColumn != valueColumn
     * and there is no value associated with the selected display. Typically,
     * this will occur if the ComboBox is editable and the user enters an unlisted
     * value; however it can also occur if someone sets the value directly using
     * code.
     * If the unlisted problem has been corrected and excecution can
     * continue, either with the old value or a new one, the method should
     * return that value. If the problem is not corrected and the DBComboBox
     * should display its default error message return UNHANDELED. If the
     * DBComboxBox should not provide the error handling (usually b/c the handler
     * has already done some kind of user notification) return HANDELED
     */
    public Object unlistedSelection(DBComboBox cmb, Object selection);
    
    /**
     * The DBComboBox will call this method if the displayColumn != valueColumn
     * and the database value to br reflected has no associated display value.
     * Typically this would occur if the value reffered to a dara record that
     * was deleted from the database.
     * If the unlisted problem has been corrected and excecution can
     * continue, either with the old value or a new one, the method should
     * return that value. If the problem is not corrected and the DBComboBox
     * should display its default error message return UNHANDELED. If the
     * DBComboxBox should not provide the error handling (usually b/c the handler
     * has already done some kind of user notification) return HANDELED
     */
    public Object unlistedDBValue(DBComboBox cmb, Object value);
  }
  // </editor-fold>  

}
