/*
 * DBFieldUpdateEvent.java
 *
 * Created on February 1, 2008, 3:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jentry.sql.events;

import jentry.sql.swing.DBRecordSetContainer;
import java.util.EventObject;

/**
 * DBFieldUpdateEvents are generated by ISourceControls whenever the current
 * recordNumber changes causing the database values to change. Events are
 * generated seperately for each column in the database and include the name of
 * the column and its new value
 *
 * @author Menachem & Shira
 */
public class DBFieldUpdateEvent extends EventObject {
  
  /**
   * Creates a new instance of DBFieldUpdateEvent
   */
  public DBFieldUpdateEvent(DBRecordSetContainer source, String fieldName, Object value) {
    super(source);
    setFieldName(fieldName);
    setValue(value);
  }

  public DBRecordSetContainer getSource() {return (DBRecordSetContainer) super.getSource();}
  
  public String getFieldName() {return fieldName;}
  public void setFieldName(String fieldName) {this.fieldName = fieldName;}
  
  public Object getValue() {return value;}
  public void setValue(Object value) {this.value = value;}
  
  protected String fieldName;
  protected Object value;
}
