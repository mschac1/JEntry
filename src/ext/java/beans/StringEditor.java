/*
 * StringEditor.java
 *
 * Created on January 14, 2007, 1:44 PM
 */

package ext.java.beans;

import java.beans.*;

/**
 *
 * @author Menachem & Shira
 */
public class StringEditor extends PropertyEditorSupport {
  
  String value;
  
  public StringEditor() {
    super();
    value = null;
  }

  public StringEditor(Object source) {
    super(source);
    setValue(source);
  }
  
  public String getAsText() {
    if (value == null)
      return "null";
    else
      return "\"" + value + "\"";  }
  
  public String getJavaInitializationString() {return getAsText();}
  
  
  public String[] getTags() {return null;}
  
  public Object getValue() {return value;}
  
  public void setAsText(String text) throws IllegalArgumentException {
    if (text.equals("null"))
      value = null;
    else if (text.length() < 2)
      throw new IllegalArgumentException();
    else if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"')
      value = text.substring(1, text.length() - 1);
    else
      throw new IllegalArgumentException();
  }
  
  public void setValue(Object value) {this.value = (String) value;}
}
