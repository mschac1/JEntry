/*
 * DBRecordSetContainer.java
 *
 * Created on March 3, 2008, 9:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.db;

/**
 *
 * @author Menachem & Shira
 */
public interface DBRecordSetContainer extends DBRecordSetControl {

  public String[][] getParentChildLink();
  public void setParentChildLink(String[][] parentChildLink);

}
