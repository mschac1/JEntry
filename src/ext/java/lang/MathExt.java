/*
 * MathExt.java
 *
 * Created on April 24, 2007, 11:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

import java.util.Arrays;

/**
 *
 * @author HP_Owner
 */
public class MathExt {
  
  public static int max(int... arr) {
    return nth(1, arr);
  }
  public static int nth(int n, int... arr) {
    return getSorted(arr)[arr.length - n];
  }
  private static int[] getSorted(int... arr) {
    int[] sorted = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      sorted[i] = arr[i];
    }
    Arrays.sort(sorted);
    return sorted;
  }
  
}
