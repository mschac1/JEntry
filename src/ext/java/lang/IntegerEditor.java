/*
 * IntegerEditor.java
 *
 * Created on January 11, 2007, 5:36 PM
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
public class IntegerEditor extends PrimitiveClassEditor {
  /** Creates a new instance of IntegerEditor */
  public IntegerEditor() {
    super();
  }
  public IntegerEditor(Object source) {
    super(source);
  }
  public Class getPrimitiveClass() {return int.class;}
  /*
  static {
    PropertyEditorManager.registerEditor(Integer.class, IntegerEditor.class);
  }
  */ 
}
