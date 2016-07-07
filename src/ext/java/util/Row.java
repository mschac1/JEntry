/*
 * Row.java
 *
 * Created on April 25, 2007, 10:21 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

/**
 *
 * @author HP_Owner
 */
public class Row<E> {
  Object[] data = null;
  int width = -1;
  
  /** Creates a new instance of Row */
  public Row(int width) {
    data = new Object[width];
  }
  
  public Row(E[] data) {
    setWidth(data.length);
    for (int i = 0; i < data.length; i++) {
      this.data[i] = data;
    }
  }
  
  public E getEntry(int i) {
    return (E) data[i];
  }
  
  public void setEntry(int i, E entry) {
    data[i] = entry;
  }
  
  public void setWidth(int width) {
    this.width = width;
    data = new Object[width];
  }
  
  public int getWidth() {
    return width;
  }
}
