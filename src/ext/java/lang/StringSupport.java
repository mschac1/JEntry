/*
 * StringSupport.java
 *
 * Created on March 19, 2007, 1:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

/**
 *
 * @author Menachem & Shira
 */
public class StringSupport {
  public static String capitalize(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }
  
}
