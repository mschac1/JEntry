/*
 * RecordSetChangeEvent.java
 *
 * Created on January 24, 2008, 1:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql.event;

/**
 * A RecordSetChangeEvent is generated when a significant change is made or
 * about to be made to a Query objects underlying database
 *
 * @author Menachem & Shira
 */
public class RecordSetChangeEvent extends java.util.EventObject {
  
  /**
   * Creates a new instance of RecordSetChangeEvent
   */
  public RecordSetChangeEvent(Object source) {
    super(source);
  }
}
