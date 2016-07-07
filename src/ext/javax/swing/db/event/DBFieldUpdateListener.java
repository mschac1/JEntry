/*
 * DBFieldUpdateListener.java
 *
 * Created on February 1, 2008, 3:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db.event;
import ext.java.util.VetoException;
import ext.javax.swing.db.*;
import java.util.EventListener;

/**
 *
 * @author Menachem & Shira
 */
public interface DBFieldUpdateListener extends EventListener {
  /** This method is called to inform listeners that b/c the recordNumber
   changed, the value of a particular field should be updated
   */
  public void dbFieldUpdated(DBFieldUpdateEvent evt);
}
