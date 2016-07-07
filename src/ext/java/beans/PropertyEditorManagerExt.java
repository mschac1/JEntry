/*
 * PropertyEditorManagerExt.java
 *
 * Created on April 17, 2007, 5:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.beans;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Array;

/**
 *
 * @author Nachman Schachter
 */
public class PropertyEditorManagerExt {
  public static void registerEditor(Class<?> targetType, Class<?> editorClass) {
    PropertyEditorManager.registerEditor(targetType, editorClass);
  }  

  private static void load(Class targetType, String editorClassName) {
    Class editorClass = null;
    try {
      editorClass = Class.forName(editorClassName);
    } catch (Exception e) {}
    PropertyEditorManager.registerEditor(targetType, editorClass);
  }

  public static synchronized PropertyEditor findEditor(Class targetType) {
    PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
    
    if (editor == null) {
      if (targetType.isEnum()) {
        editor = new EnumEditor();
        ((EnumEditor) editor).setClass(targetType);
      }
      else if(targetType.isArray()) {
        editor = new ArrayEditor();
        ((ArrayEditor) editor).setClass(targetType);
      }
    }
    
    return editor;
  }
    public static synchronized String[] getEditorSearchPath() {
	return PropertyEditorManager.getEditorSearchPath();
    }

    public static synchronized void setEditorSearchPath(String path[]) {
      PropertyEditorManager.setEditorSearchPath(path);
    }
  
  static {
    load(Byte.class, "sun.beans.editors.ByteEditor");
    load(Short.class, "sun.beans.editors.ShortEditor");
    load(Integer.class, "sun.beans.editors.IntEditor");
    load(Long.class ,"sun.beans.editors.LongEditor");
    load(Boolean.class, "sun.beans.editors.BoolEditor");
    load(Float.class, "sun.beans.editors.FloatEditor");
    load(Double.class, "sun.beans.editors.DoubleEditor");
    
    load(char.class, "ext.java.beans.CharEditor");
    load(Character.class, "ext.java.beans.CharEditor");
    load(String.class, "ext.java.beans.StringEditor");
    load(Enum.class, "ext.java.beans.EnumEditor");
    load(Array.class, "ext.java.beans.ArrayEditor");
  }
  
}