/*
 * EnumEditor.java
 *
 * Created on January 14, 2007, 1:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

import java.beans.*;

/**
 *
 * @author Menachem & Shira
 */
public class EnumEditor extends PropertyEditorSupport {
  
  Object value;
  
  public EnumEditor() {
    super();
    value = null;
  }
  public EnumEditor(Object source) {
    super(source);
    System.out.println("Hi");
    setValue(source);
  }
  
  public String getAsText() {return value.toString();}
  public String getJavaInitializationString() {
    return value.getClass().getCanonicalName() + "." + getAsText();
  }
  public String[] getTags() {
    if (value == null)
      throw new NullPointerException("Value must be set before getTags is called");
    Class c = value.getClass();
    Object[] enums = c.getEnumConstants();
    String[] tags = new String[enums.length];
    for (int i = 0; i < enums.length; i++) {
      tags[i] = enums[i].toString();
    }
    return tags;
  }
  public Object getValue() {return value;}
  public void setAsText(String text) throws IllegalArgumentException {
    if (value == null) throw new java.lang.RuntimeException("Unknown Enumeration Type");
    value = Enum.valueOf((Class) value.getClass(), text);
  }
  public void setValue(Object value) {this.value = value;}
}
