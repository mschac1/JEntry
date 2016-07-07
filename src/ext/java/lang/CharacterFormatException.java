/*
 * CharacterFormatException.java
 *
 * Created on May 1, 2007, 3:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

/**
 *
 * @author Nachman Schachter
 */
public class CharacterFormatException extends java.lang.RuntimeException {
  
  /**
   * Creates a new instance of <code>CharacterFormatException</code> without detail message.
   */
  public CharacterFormatException() {
  }
  
  
  /**
   * Constructs an instance of <code>CharacterFormatException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public CharacterFormatException(String msg) {
    super(msg);
  }
}
