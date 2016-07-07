/*
 * ComponentVector.java
 *
 * Created on February 12, 2007, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing;

import ext.java.util.ICreator;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author HP_Owner
 */
public class ComponentVector <E extends Component> extends JPanel {
  public enum Orientation {VERTICAL, HORIZONTAL};
  
  Vector<E> v;
  int size;
  Orientation orientation;
  Dimension componentSize;
  ICreator creator;
  
  /**
   * Creates a new instance of ComponentVector
   */
  public ComponentVector() {this(null);}
  public ComponentVector(ICreator creator) {this(creator, Orientation.VERTICAL);}
  public ComponentVector(ICreator creator, Orientation o) {this(creator, o, null);}
  public ComponentVector(ICreator creator, Orientation o, Dimension componentSize) {
    super();
    if (o == Orientation.VERTICAL)
      setLayout(new GridLayout(0,1));
    else
      setLayout(new GridLayout(1,0));
    v = new Vector<E>();
    size = 0;
    orientation = o;
    setComponentSize(componentSize);
    setCreator(creator);
  }
  
  public void resize(int newSize) {
    int currentSize = getCurrentSize();
    int capacity = getCapacity();
    
    if (newSize > capacity) {
      for (int i = capacity; i < newSize; i++) {
        try {
          v.add((E) creator.create());
        } catch (Exception e) {v.add(null);}
      }
    }
    if (newSize < currentSize) {
      for (int i = newSize; i < currentSize; i++)
        remove(v.get(i));
    }
    else if (newSize > currentSize) {
      for (int i = currentSize; i < newSize; i++)
        add(v.get(i));
    }
    
    setCurrentSize(newSize);
    setPreferredSize(getPreferredSize());
    revalidate();
  }

  // Component Management
  public ICreator getCreator() {return creator;}
  public void setCreator(ICreator creator) {this.creator = creator;}
  
  public int getCurrentSize() {return size;}
  private void setCurrentSize(int size) {this.size = size;}
  
  private int getCapacity() {return v.size();}
 
  public E getComponent(int index) {return v.get(index);}
  
  public Enumeration<E> components() {return v.elements();}

  // Layout
  public Dimension getComponentSize() {return componentSize;}
  public void setComponentSize(Dimension componentSize) {this.componentSize = componentSize;}
  
  public Orientation getOrientation() {return orientation;}
  public void setOrientation(Orientation orientation) {this.orientation = orientation;}
  
  public Dimension getPreferredSize() {
    Dimension size;
    if (getComponentSize() != null)
      size = componentSize;
    else if (getCurrentSize() > 0)
      size = getComponent(0).getPreferredSize();
    else
      size = new Dimension(0, 0);
    
    int width = size.width;
    int height = size.height;
    
    if (orientation == Orientation.VERTICAL)
      height *= getCurrentSize();
    else
      width *= getCurrentSize();
      
    return new Dimension(width, height);
  }
  
}
