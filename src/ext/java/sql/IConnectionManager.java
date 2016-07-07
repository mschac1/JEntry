/*
 * IConnectionManager.java
 *
 * Created on February 4, 2007, 8:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Menachem & Shira
 */
public interface IConnectionManager {
  public Connection getConnection() throws SQLException;
  public void closeConnection(Connection conn) throws SQLException;
}
