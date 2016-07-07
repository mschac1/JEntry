/*
 * CharEditor.java
 *
 * Created on January 14, 2007, 1:44 PM
 */

package ext.java.beans;

import ext.java.lang.CharacterExt;
import ext.java.lang.CharacterFormatException;
import java.beans.*;

/**
 *
 * @author Menachem & Shira
 */
public class CharEditor extends PropertyEditorSupport {
  
  Character value;
  
  public CharEditor() {
    super();
    value = null;
  }

  public CharEditor(Object source) {
    super(source);
    setValue(source);
  }
  
  public String getAsText() {
    if (value == null)
      return "null";
    else if (value.equals(new Character('\\')) || value.equals(new Character('\'')))
      return "'\\" + value + "'";
    else
      return "'" + value + "'";  }
  
  public String getJavaInitializationString() {return getAsText();}
  
  
  public String[] getTags() {return null;}
  
  public Object getValue() {return value;}
  
  public void setAsText(String text) throws CharacterFormatException {
    if (text.equals("null"))
      value = null;
    else if (text.length() < 2)
      throw new CharacterFormatException();
    else if (text.charAt(0) == '\'' && text.charAt(text.length() - 1) == '\'') {
      if (text.length() == 3 && (text.charAt(1) == '\\' || text.charAt(1) == '\'')) {
        throw new CharacterFormatException();
      }
      else {
        char c = CharacterExt.parseChar(text.substring(1, text.length() - 1));
        value = new Character(c);
      }
    }
    else
      throw new CharacterFormatException();
  }
  
  public void setValue(Object value) {this.value = (Character) value;}
  
}
