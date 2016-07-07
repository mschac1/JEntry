/*
 * NoSuchPropertyException.java
 *
 * Created on December 21, 2006, 3:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.beans;

/**
 *
 * @author Nachman Schachter
 */
public class NoSuchPropertyException extends java.lang.RuntimeException {
   
   /**
    * Creates a new instance of <code>NoSuchPropertyException</code> without detail message.
    */
   public NoSuchPropertyException() {
   }
   
   
   /**
    * Constructs an instance of <code>NoSuchPropertyException</code> with the specified detail message.
    * 
    * @param msg the detail message.
    */
   public NoSuchPropertyException(String msg) {
      super(msg);
   }
}
