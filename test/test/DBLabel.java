/*
 * DBLabel.java
 *
 * Created on April 28, 2008, 4:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import jentry.sql.swing.DBFieldControl;
import jentry.sql.swing.DBRecordSetContainer;
import jentry.sql.TypeConversionSupport;
import jentry.sql.events.DBFieldUpdateEvent;
import jentry.sql.events.DBFieldUpdateListener;
import jentry.sql.events.FormFieldUpdateListener;
import java.text.Format;
import javax.swing.JLabel;

/**
 *
 * @author Nachman Schachter
 */


public class DBLabel extends JLabel implements DBFieldControl, DBFieldUpdateListener {

  private String fieldName = "";
  private Object defaultValue = null;
  private Object fieldValue = null;
  private Format formatter = null;
  
  /** Creates a new instance of DBLabel */
  public DBLabel() {this(null);}
  
  public DBLabel(String fieldName) {
    super();
    setFieldName(fieldName);
  }

  public void dbFieldUpdated(DBFieldUpdateEvent evt) {
    DBRecordSetContainer db = evt.getSource();

    if (db.getRecordNum() != db.getRecordCount() + 1)
      setFieldValue(evt.getValue());
    else
      setFieldValue(getDefaultValue());
  }
  
  public String getFieldName() {return fieldName;}
  public void setFieldName(String fieldName) {this.fieldName = fieldName;}
  
  public Object getDefaultValue() {return defaultValue;}
  public void setDefaultValue(Object defaultValue) {this.defaultValue = defaultValue;}
  
  public Object getFieldValue() {return fieldValue;}
  public void setFieldValue(Object fieldValue) {
    this.fieldValue = fieldValue;
    setText(TypeConversionSupport.objectToString(fieldValue, getFormatter()));
  }
  
  public Format getFormatter() {return formatter;}
  public void setFormatter(Format formatter) {this.formatter = formatter;}

  public void addFormFieldUpdateListener(FormFieldUpdateListener listener) {
    // Do nothing
  }

  public void removeFormFieldUpdateListener(FormFieldUpdateListener listener) {
    // Do nothing
  }

}
