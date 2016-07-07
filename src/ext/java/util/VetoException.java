/*
 * VetoException.java
 *
 * Created on February 1, 2008, 3:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

/**
 *
 * @author Menachem & Shira
 */
public class VetoException extends java.lang.Exception {
  
  /**
   * Creates a new instance of <code>VetoException</code> without detail message.
   */
  public VetoException() {
  }
  
  
  /**
   * Constructs an instance of <code>VetoException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public VetoException(String msg) {
    super(msg);
  }
}
