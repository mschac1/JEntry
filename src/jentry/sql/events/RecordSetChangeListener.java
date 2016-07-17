/*
 * RecordSetChangeListener.java
 *
 * Created on January 24, 2008, 1:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jentry.sql.events;

import ext.java.util.VetoException;
import java.sql.SQLException;

/**
 *
 * @author Menachem & Shira
 */
public interface RecordSetChangeListener extends java.util.EventListener {
  /** A new record has been selected (the recordNumber has changed) */
  public void recordSelected(RecordSetChangeEvent evt);
  
  /** The underlying recordSet has been replaced */
  public void recordSetChanged(RecordSetChangeEvent evt);
  
  /** A new record will be inserted into the database table. Can be vetoed */
  public void recordWillBeInserted (RecordSetChangeEvent evt) throws VetoException;
  
  /** A new record was inserted into the database table */
  public void recordInserted (RecordSetChangeEvent evt);
  
  /** The underlying record set was dirtied and will be updated before the
   * recordNumber is changed
   */
  public void recordDirtied(RecordSetChangeEvent evt);
  
  /** A record will be updated. Can be vetoed.
   * NOTE: this method is not called before a record is inserted
   */
  public void recordWillBeUpdated(RecordSetChangeEvent evt) throws VetoException;
  
  /** A record was updated
   * NOTE: this method is not called before a record is inserted
   */
  public void recordUpdated(RecordSetChangeEvent evt);
  
  /** A record will be deleted. Can be vetoed. */
  public void recordWillBeDeleted(RecordSetChangeEvent evt) throws VetoException;

  /** A record was deleted. */
  public void recordDeleted(RecordSetChangeEvent evt);
}
