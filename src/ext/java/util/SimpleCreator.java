/*
 * SimpleCreator.java
 *
 * Created on February 13, 2007, 3:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

/**
 *
 * @author Nachman Schachter
 */
public class SimpleCreator implements ICreator {
  Class type;
  /** Creates a new instance of SimpleCreator */
  public SimpleCreator(Class type) {this.type = type;}
  
  public Object create() throws InstantiationException, IllegalAccessException {
    return type.newInstance();
  }
}
