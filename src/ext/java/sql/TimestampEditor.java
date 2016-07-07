/*
 * TimestampEditor.java
 *
 * Created on February 27, 2008, 4:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql;

import ext.java.util.DateFormatException;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;

/**
 *
 * @author Nachman Schachter
 */
public class TimestampEditor extends PropertyEditorSupport {
 
  public TimestampEditor() {
    super();
  }
  public TimestampEditor(Object source) {
    super(source);
  }
  public String getAsText() {return getValue().toString();}
  public Timestamp getValue() {return (Timestamp) getSource();}
  public boolean isPaintable() {return false;}
  public void setAsText(String text) throws IllegalArgumentException {
    try {
      setSource(Timestamp.valueOf(text));
    } catch (IllegalArgumentException ex) {throw new DateFormatException(ex.getMessage());}
  }
  public void setValue(Timestamp value) {setSource(value);}
    
}
