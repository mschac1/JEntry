/*
 * FormFieldUpdateEvent.java
 *
 * Created on February 5, 2008, 8:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db.event;

import ext.javax.swing.db.DBFieldControl;
import java.util.EventObject;

/**
 * FormFieldUpdateEvents are generated to let the ISourceControl know that
 * a field value has changed and therefore the database should be updated to
 * reflect that change
 *
 * @author Menachem & Shira
 */
public class FormFieldUpdateEvent extends EventObject{
  
  /**
   * Creates a new instance of FormFieldUpdateEvent
   */
  public FormFieldUpdateEvent(Object source) {super(source);}
  
  public DBFieldControl getSource() {return (DBFieldControl) super.getSource();}
}
