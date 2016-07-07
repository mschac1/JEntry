/*
 * DateFormatException.java
 *
 * Created on February 27, 2008, 5:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

/**
 *
 * @author Nachman Schachter
 */
public class DateFormatException extends java.lang.IllegalArgumentException {
  
  /**
   * Creates a new instance of <code>DateFormatException</code> without detail message.
   */
  public DateFormatException() {
    super();
  }
  
  
  /**
   * Constructs an instance of <code>DateFormatException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public DateFormatException(String msg) {
    super(msg);
  }
}
