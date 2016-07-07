/*
 * Column.java
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
public class Column<E> {
  Object[] data = null;
  int height = -1;
  
  /** Creates a new instance of Column */
  public Column(int height) {
    data = new Object[height];
  }
  
  public Column(E[] data) {
    this(data.length);
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
  
  public void setHeight(int height) {
    this.height = height;
    data = new Object[height];
  }
  
  public int getHeight() {
    return height;
  }
}
