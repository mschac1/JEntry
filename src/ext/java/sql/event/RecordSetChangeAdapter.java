/*
 * RecordSetChangeAdapter.java
 *
 * Created on February 13, 2008, 4:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql.event;

import ext.java.util.VetoException;
import ext.javax.swing.db.event.*;
import java.sql.SQLException;

/**
 *
 * @author Nachman Schachter
 */
public abstract class RecordSetChangeAdapter implements RecordSetChangeListener {
  public void recordSelected(RecordSetChangeEvent evt) {}
  public void recordSetChanged(RecordSetChangeEvent evt) {}
  public void recordWillBeInserted (RecordSetChangeEvent evt) throws VetoException {}
  public void recordInserted (RecordSetChangeEvent evt) {}
  public void recordDirtied(RecordSetChangeEvent evt) {}
  public void recordWillBeUpdated(RecordSetChangeEvent evt) throws VetoException {}
  public void recordUpdated(RecordSetChangeEvent evt) {}
  public void recordWillBeDeleted(RecordSetChangeEvent evt) throws VetoException {}
  public void recordDeleted(RecordSetChangeEvent evt) {}
  
}
