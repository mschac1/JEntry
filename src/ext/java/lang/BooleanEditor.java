/*
 * BooleanEditor.java
 *
 * Created on January 14, 2007, 1:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

/**
 *
 * @author Menachem & Shira
 */
public class BooleanEditor extends PrimitiveClassEditor {
  /**
   * Creates a new instance of BooleanEditor
   */
  public BooleanEditor() {
    super();
  }
  public BooleanEditor(Object source) {
    super(source);
  }
  public Class getPrimitiveClass() {return boolean.class;}
}
