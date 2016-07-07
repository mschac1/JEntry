/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ext.java.util;

import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Menachem
 */
public class EnumerationSupport {


  private EnumerationSupport() {};
  public static int getCount(Enumeration e) {
    int i = 0;
    while (e.hasMoreElements()) {
      i++;
      e.nextElement();
    }
    return i;
  }
}
