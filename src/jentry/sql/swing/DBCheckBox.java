/*
 * DBTextField.java
 *
 * Created on January 25, 2008, 4:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jentry.sql.swing;

import jentry.sql.ControlSourceParser;
import jentry.sql.events.DBFieldUpdateEvent;
import jentry.sql.events.FormFieldUpdateEvent;
import jentry.sql.events.FormFieldUpdateListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;

/**
 *
 * @author Menachem & Shira
 */

/* DBTextField will provide automatic formatting for: BigDecimal, boolean, byte,
 Date, double, float, int, long, short, String, Time, Timestamp*/

public class DBCheckBox extends JCheckBox implements DBFieldControl{
  
  /** Creates a new instance of DBTextField */
  public DBCheckBox() {this("");}
  public DBCheckBox(String controlSource) {
    setControlSource(controlSource);
    addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            fireFormFieldDirtied();
            setDirty(true);
        }        
    });
  }

  public void setControlSource(String controlSource) {
    this.controlSource = controlSource;
    setFieldNames(ControlSourceParser.getFieldNames(controlSource));
    if (getAllFieldNames().length > 1) {
      this.setEnabled(false);
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
    

    this.setSelected(((Boolean) val));

    setDirty(false);
  }  
 
  public Object getFieldValue() {
    return isSelected();
  }

  public void setDefaultValue(Object defaultValue) {
    this.defaultValue = ((Boolean) defaultValue);
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
  
  public Boolean getDefaultValue() {return defaultValue;}
  

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
    protected Boolean defaultValue = null;
    protected Class fieldType = null;
    protected boolean isDirty = false; // Indicate whether FormFieldUpdateEvent should be fired the next time the control loses the focus
    protected String controlSource = "";
    protected ArrayList<String> fieldNames;
    protected DBRecordSetContainer recordSourceControl;

//	protected Hashtable<String,Object> fieldValueHash= new Hashtable<String, Object>();


}
