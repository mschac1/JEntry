/*
 * DBFieldControl.java
 *
 * Created on January 25, 2008, 4:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jentry.sql.swing;

import jentry.sql.events.DBFieldUpdateListener;
import jentry.sql.events.FormFieldUpdateListener;

/**
 *
 * @author Menachem & Shira
 */
public interface DBFieldControl extends DBFieldUpdateListener {

  public String[] getAllFieldNames();

  public DBRecordSetContainer getRecordSourceControl();
  public void setRecordSourceControl(DBRecordSetContainer control);

  public String getControlSource();
  public void setControlSource(String conrolSource);

  public String getFieldName();
  public Object getFieldValue();
  
  public Object getDefaultValue();
  public void setDefaultValue(Object defaultValue);
  
  public void addFormFieldUpdateListener(FormFieldUpdateListener listener);
  public void removeFormFieldUpdateListener(FormFieldUpdateListener listener);

}
