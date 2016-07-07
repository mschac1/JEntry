/*
 * ArraySupport.java
 *
 * Created on February 18, 2007, 3:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

/**
 *
 * @author Menachem & Shira
 */
public class ArraySupport {

  public static boolean contains(Object[] array, Object key) {
    boolean flag = false;
    for (int i = 0; i < array.length; i++) {
      if (array[i].equals(key))
        flag = true;
    }
    return flag;
  }
  
  public static int indexOf(Object[] array, Object key) {
    for (int i = 0; i < array.length; i++) {
      if (array[i].equals(key))
        return i;
    }
    return -1;
  }
  
  public static boolean contains(char[] array, char key) {
    boolean flag = false;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == key)
        flag = true;
    }
    return flag;
  }
  
}
