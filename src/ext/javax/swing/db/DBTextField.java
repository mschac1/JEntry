/*
 * DBTextField.java
 *
 * Created on January 25, 2008, 4:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import ext.java.sql.TypeConversionSupport;
import ext.javax.swing.WindowClosingException;
import ext.javax.swing.db.event.DBFieldUpdateEvent;
import ext.javax.swing.db.event.FormFieldUpdateEvent;
import ext.javax.swing.db.event.FormFieldUpdateListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Format;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Menachem & Shira
 */

/* DBTextField will provide automatic formatting for: BigDecimal, boolean, byte,
 Date, double, float, int, long, short, String, Time, Timestamp*/

public class DBTextField extends JTextField implements DBFieldControl, DocumentListener, FocusListener {
  
  /** Creates a new instance of DBTextField */
  public DBTextField() {this("");}
  public DBTextField(String controlSource) {
    setControlSource(controlSource);
    getDocument().addDocumentListener(this);
    addFocusListener(this);
  }

  public void setControlSource(String controlSource) {
    this.controlSource = controlSource;
    setFieldNames(ControlSourceParser.getFieldNames(controlSource));
    if (getAllFieldNames().length > 1) {
      setEditable(false);
    }
  }

  public void dbFieldUpdated(DBFieldUpdateEvent evt) {
    DBRecordSetContainer db = evt.getSource();

    if (getFieldType() == null)
      setFieldType(db.getFieldType(getFieldName()));
    
    Object val;
    if (db.getRecordNum() != db.getRecordCount() + 1) {
			if (getAllFieldNames().length == 1) {
	      val = evt.getValue();
			}
			else {
				val = ControlSourceParser.evaluate(this);

			}
		}
    else
      val = getDefaultValue();
    
    // Avoid generating a field update when setting the text
    getDocument().removeDocumentListener(this);

    setText(TypeConversionSupport.objectToString(val, getFormatter()));
    setDirty(false);

    getDocument().addDocumentListener(this);
  }
  
  public void removeUpdate(DocumentEvent e) {
    fireFormFieldDirtied();
    setDirty(true);
  }
  public void insertUpdate(DocumentEvent e) {
    fireFormFieldDirtied();
    setDirty(true);
  }
  public void changedUpdate(DocumentEvent e) {}
  
  // TODO 3 - Need to figue out how to deal with navigating from a control with an invalid format to a different window
  // TODO 1.3 - Also need to implient this isDirty scheme for the ComboBox*/
  public void focusLost(FocusEvent e) {

    //The isDirty mechanisim needs to be modified so that it is cleared upon save
    if (isDirty()){// && e.getOppositeComponent() != null) {// will be null if another window has taken the focus
      // Force a parse, so that any invalid formatting will be handled immediatly
      getFieldValue();
      fireFormFieldUpdated();
			setDirty(false);
    }
  }
  public void focusGained(FocusEvent e) {}
  
  public Object getFieldValue() {
   Object val = null;
    
    try {
      val = TypeConversionSupport.stringToObject(getText(), getFieldType(), getFormatter());
    } catch (Exception ex) {
      
        JOptionPane.showMessageDialog(this.getParent(), getFormatErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);

      // The following is a workaround for a bug that causes the requestFocus method to generate another FocusLostEvent
      SwingUtilities.invokeLater(new Runnable() {
        public void run(){requestFocusInWindow();}
      });
      
      // If the window is being closed throw an exception to allow the
      // possibility of the window close being canceled
      if (((AbstractDBContainer) getParent()).isWindowClosing())
        throw new WindowClosingException();
    }
    return val;
  }

  public void setDefaultValue(Object defaultValue) {
    this.defaultValue = defaultValue.toString();
  }
  
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
  
  public String getFieldName() {
		if (getAllFieldNames().length > 0)
			return getAllFieldNames()[0];
		else
			return null;
	}
//  public void setFieldName(String fieldName) {this.fieldName = fieldName;}
  
  public String getDefaultValue() {return defaultValue;}
  public void setDefaultValue(String defaultValue) {this.defaultValue = defaultValue;}
  
  public Format getFormatter() {return formatter;}
  public void setFormatter(Format formatter) {this.formatter = formatter;}

  public String getFormatErrorMessage() {return formatErrorMessage;}
  public void setFormatErrorMessage(String formatErrorMessage) {this.formatErrorMessage = formatErrorMessage;}

  protected Class getFieldType() {return fieldType;}
  protected void setFieldType(Class fieldType) {this.fieldType = fieldType;}

	public boolean  isDirty() {return isDirty;}
	public void setDirty(boolean  isDirty) {this.isDirty = isDirty;}

	public String getControlSource() {return controlSource;}

	protected void setFieldNames(ArrayList<String> fieldNames) {this.fieldNames = fieldNames;}
	public String[] getAllFieldNames() {return fieldNames.toArray(new String[0]);}

	public DBRecordSetContainer getRecordSourceControl() {return recordSourceControl;}
	public void setRecordSourceControl(DBRecordSetContainer control) {
		this.recordSourceControl = control;
	}

  protected String fieldName = null;
  protected String defaultValue = null;
  protected Format formatter = null;
  protected String formatErrorMessage = "The value you entered isn't valid for this field.";
  protected Class fieldType = null;
  protected boolean isDirty = false; // Indicate whether FormFieldUpdateEvent should be fired the next time the control loses the focus
	protected String controlSource = "";
	protected ArrayList<String> fieldNames;
	protected DBRecordSetContainer recordSourceControl;

//	protected Hashtable<String,Object> fieldValueHash= new Hashtable<String, Object>();


}
