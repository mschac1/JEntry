/*
 * Grid.java
 *
 * Created on April 25, 2007, 10:34 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

/**
 *
 * @author HP_Owner
 */
public class Grid<E> {
  Object[][] data = null;
  int width = -1;
  int height = -1;
  
  /** Creates a new instance of Grid */
  public Grid(int width, int height) {
    setSize(width, height);
  }
  
  public Grid(E[][] data) {
    this(data[0].length, data.length);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        this.data[i][j] = data[i][j];
      } 
    }
  }
  
  public E getEntry(int i, int j) {
    return (E) data[i][j];
  }
  
  public void setEntry(int i, int j, E entry) {
    data[i][j] = entry;
  }
  
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
    data = new Object[height][width];
  }
      
  public int getWidth() {
    return width;
  }
 
  public int getHeight() {
    return height;
  }
  
  public Row<E> getRow(int i) {
    return new Row(data[i]);
  }
  
  public void setRow(int i, Row<E> r) {
    data[i] = r.data;
  }
  
  public Column<E> getColumn(int i) {
    Column<E> c = new Column(height);
    for (int j = 0; j < height; j++) {
      c.data[j] = data[j][i];
    }
    return c;
  }
  
  public void setColumn(int i, Column<E> c) {
    for (int j = 0; j < data.length; j++) {
      data[j][i] = c.getEntry(j);
    }
  }
  public static void main(String... args) {
     String[][] s = {
      {"1", "2"},
      {"3", "4"},
      {"5", "6"}};
     Grid<String> g = new Grid <String>(s);
     System.out.println(g.getEntry(0, 1));
     Row r = g.getRow(2);
     System.out.println(r.getEntry(1));
     Column c = g.getColumn(0);
     System.out.println(c.getEntry(2));
  }
}
