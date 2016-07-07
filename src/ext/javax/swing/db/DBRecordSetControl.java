/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ext.javax.swing.db;

import ext.java.sql.Query;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Menachem
 */
public interface DBRecordSetControl {
  public void saveRecord();

  public void deleteRecord();

  public void requery();

  public void firstRecord();

  public void previousRecord();

  public void nextRecord();

  public void lastRecord();

  public void newRecord();

  public int getRecordNum();
  public boolean setRecordNum(int i);

  public boolean getInsertsAllowed();
  public void setInsertsAllowed(boolean allowInserts);

  public boolean getDeletesAllowed();
  public void setDeletesAllowed(boolean allowDeletes);

  public int getRecordCount();

  public Class getFieldType(String fieldName);

  public Query getQuery();

  //TODO 2.5 - Consider using a ConnectionManager instead of a connection, b/c then the value could be set without a try/catch block
  public Connection getConnection();
  public void setConnection(Connection connection);

  public void setRecordSource(String source) throws SQLException;

  public void setFilter(String filter);
  public String getFilter();

  public void setSort(String sort);
  public String getSort();

}
