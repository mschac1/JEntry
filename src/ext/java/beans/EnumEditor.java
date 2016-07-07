/*
 * EnumEditor.java
 *
 * Created on January 14, 2007, 1:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.beans;

import java.beans.*;

/**
 *
 * @author Menachem & Shira
 */
public class EnumEditor extends PropertyEditorSupport {
  
  Object value;
  Class enumClass;
  
  public EnumEditor() {
    super();
    value = null;
  }
  public EnumEditor(Object source) {
    super(source);
    setValue(source);
  }
  
  public String getAsText() {
    if (value == null)
      return "null";
    else
      return value.getClass().getCanonicalName() + "." + getAsText();
  }
  public String getJavaInitializationString() {return getAsText();}
  
  public String[] getTags() {
    Object[] enums = enumClass.getEnumConstants();
    String[] tags = new String[enums.length];
    for (int i = 0; i < enums.length; i++) {
      tags[i] = enums[i].toString();
    }
    return tags;
  }
  public Object getValue() {return value;}
  public void setAsText(String text) throws IllegalArgumentException {
    if (text.equals("null"))
      value = null;
    else
      value = Enum.valueOf(enumClass, text);
  }
  public void setValue(Object value) {this.value = value;}
  
  protected void setClass(Class enumClass) {this.enumClass = enumClass;}
}
