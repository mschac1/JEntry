/*
 * WindowClosingException.java
 *
 * Created on March 31, 2008, 3:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing;

import java.io.PrintStream;

/**
 *
 * @author Nachman Schachter
 */
public class WindowClosingException extends RuntimeException {
  
  /**
   * Creates a new instance of <code>WindowClosingException</code> without detail message.
   */
  public WindowClosingException() {
  }
  
  
  /**
   * Constructs an instance of <code>WindowClosingException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public WindowClosingException(String msg) {
    super(msg);
  }

  /** Override so that nothing is printed when this error is handled by the swing infrastructure*/
  public void printStackTrace(PrintStream s) {
  }
}
