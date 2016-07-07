/*
 * PrimitiveClassEditor.java
 *
 * Created on January 11, 2007, 5:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

import java.beans.*;

/**
 *
 * @author Nachman Schachter
 */
public abstract class PrimitiveClassEditor extends PropertyEditorSupport {
  
  PropertyEditor editor;
  
  public PrimitiveClassEditor() {
    super();
    editor = PropertyEditorManager.findEditor(getPrimitiveClass());
  }
  public PrimitiveClassEditor(Object source) {
    super(source);
    editor = PropertyEditorManager.findEditor(getPrimitiveClass());
  }
  public String getAsText() {return editor.getAsText();}
  public String getJavaInitializationString() {return editor.getJavaInitializationString();}
  public String[] getTags() {return editor.getTags();}
  public Object getValue() {return editor.getValue();}
  public boolean isPaintable() {return editor.isPaintable();}
  public void paintValue(java.awt.Graphics gfx, java.awt.Rectangle box) {editor.paintValue(gfx, box);}
  public void setAsText(String text) throws IllegalArgumentException {editor.setAsText(text);}
  public void setValue(Object value) {editor.setValue(value);}
  
  public abstract Class getPrimitiveClass();
  
  public static void loadEditors() {
    PropertyEditorManager.registerEditor(Integer.class, IntegerEditor.class);
    PropertyEditorManager.registerEditor(Boolean.class, BooleanEditor.class);
  }
}
