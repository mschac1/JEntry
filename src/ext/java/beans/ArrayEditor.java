/*
 * ArrayEditor.java
 *
 * Created on January 14, 2007, 1:44 PM
 */

package ext.java.beans;

import ext.java.io.JavaCodeFormatter;
import ext.java.io.JavaCodeToken;
import ext.java.io.JavaCodeTokenizer;
import java.beans.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Menachem & Shira
 */
public class ArrayEditor extends PropertyEditorSupport {
  
  Object value;
  PropertyEditor componentEditor;
  
  public ArrayEditor() {
    super();
    value = null;
    componentEditor = null;
  }

  public ArrayEditor(Object source) {
    super(source);
    setValue(source);
    componentEditor = null;
  }
  
  public String getAsText() {
    if (value == null)
      return "null";
    String s = "{";
    Object[] arr = (Object[]) value;
    for (int i = 0; i < arr.length; i++) {
      componentEditor.setValue(arr[i]);
      s += componentEditor.getJavaInitializationString();
      if (i < arr.length - 1)
        s += ", ";
    }
    s += "}";
    return s;
  }
  
  public String getJavaInitializationString() {return getAsText();}
    
  public String[] getTags() {return null;}
  
  public Object getValue() {return value;}
  
  //
  public void setAsText(String text) throws IllegalArgumentException {
    if (text.equals("null"))
      value = null;
    else {
      JavaCodeTokenizer tokenizer = new JavaCodeTokenizer(text);
      List<JavaCodeToken> tokens = tokenizer.getTokens();
      JavaCodeFormatter.stripWhiteSpace(tokens);
      value = toArray(tokens.listIterator());
    }
  }
  protected Object[] toArray(ListIterator<JavaCodeToken> iter)
    throws IllegalArgumentException {
    try {
      ArrayList list = new ArrayList();
      JavaCodeToken token = iter.next();

      if (!token.getVal().equals("{"))
        throw new IllegalArgumentException();

      token = iter.next();
      while (!token.getVal().equals("}")) {
        if (token.getVal().equals("{")) {
          ArrayEditor arrayEditor = (ArrayEditor) componentEditor;
          iter.previous();
          list.add(arrayEditor.toArray(iter));
        }
        else {
          componentEditor.setAsText(token.getVal());
          list.add(componentEditor.getValue());
        }
        
        if (!iter.next().getVal().equals(","))
          iter.previous();
          
        token = iter.next();
      }
      return list.toArray();
    } catch (Exception e) {throw new IllegalArgumentException();} 
  }
  
  
  public void setValue(Object value) {this.value = value;}
  
  protected void setClass(Class arrayClass) {
    componentEditor = PropertyEditorManagerExt.findEditor(arrayClass.getComponentType());
  }
}
