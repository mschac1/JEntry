/*
 * MSAccessConnectionManager.java
 *
 * Created on February 4, 2007, 9:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Menachem & Shira
 */
public class MSAccessConnectionManager implements IConnectionManager {
  String fileName;
  /** Creates a new instance of MSAccessConnectionManager */
  public MSAccessConnectionManager() {this(null);}
  public MSAccessConnectionManager(String fileName) {
    setFileName(fileName);
  }
  
  public Connection getConnection() throws SQLException {
    try {
      return getMSAccessConnection(fileName);
    } catch (Exception e) {throw new SQLException(e.getMessage());}
  }
  public void closeConnection(Connection conn) throws SQLException {conn.close();}

  public String getFileName() {return fileName;}
  public void setFileName(String fileName) {this.fileName = fileName;}
  
  private static Connection getMSAccessConnection(String fileName) throws Exception
  {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    
    return DriverManager.getConnection(
      "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + fileName);
  }
  
}
