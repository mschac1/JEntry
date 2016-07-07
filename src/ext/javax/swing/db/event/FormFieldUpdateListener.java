/*
 * FormFieldUpdateListener.java
 *
 * Created on February 5, 2008, 8:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db.event;

import java.sql.SQLException;
import java.util.EventListener;

/**
 *
 * @author Menachem & Shira
 */
public interface FormFieldUpdateListener extends EventListener {

  /**
   * Notifies any listeners - specifically the DBSourceControl - that
   * a field value has changed and therefore the database should be updated to
   * reflect that change
   */
  public void formFieldUpdated(FormFieldUpdateEvent evt);
  
  /**
   * Notifies any listeners - specifcally the DBSourceControl - that
   * a field value has been dirtied, but may but is not yet in a permanent
   * state yet. For example if the end user is typing data into a text field
   * but is not yet finished typing
   */
  public void formFieldDirtied(FormFieldUpdateEvent evt);
}
