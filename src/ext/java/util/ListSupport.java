/*
 * ListSupport.java
 *
 * Created on April 15, 2008, 5:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

import ext.java.lang.NumberSupport;
import java.util.AbstractList;

/**
 *
 * @author Nachman Schachter
 */
public class ListSupport {
  
  /** Creates a new instance of ListSupport */
  private ListSupport() {
  }
  
  public static int liberalIndexOf(AbstractList list, Object o) {
   if (o instanceof Number) {
     int index = -1;
     for (int i = 0; i < list.size(); i++) {
       if (NumberSupport.equals((Number) list.get(i), (Number) o)) {
        index = i;
        break;
       }
     }
     return index;
   }
   else {
     return list.indexOf(o);
   }
  }
  
}
