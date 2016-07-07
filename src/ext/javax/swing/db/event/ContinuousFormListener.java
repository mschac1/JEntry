/*
 * ContinuousFormListener.java
 *
 * Created on March 5, 2008, 12:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db.event;

import java.util.EventListener;

/**
 *
 * @author Menachem & Shira
 */
public interface ContinuousFormListener extends EventListener {
  /** Called when a Subform is added to a ContinuousForm */
  public void subformAdded(ContinuousFormEvent evt);
  
  /** Called when a Subform is removed from a ContinuousForm */
  public void subformRemoved(ContinuousFormEvent evt);
}
