/*
 * MyAQLAccessConnectionManager.java
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
public class MyDerbyConnectionManager implements IConnectionManager {
  String hostName, userName, password, database;
  /** Creates a new instance of MySQLConnectionManager */
  public MyDerbyConnectionManager() {this(null, null);}
  public MyDerbyConnectionManager(String password, String database) {this("localhost:1527", "root", password, database);}
  public MyDerbyConnectionManager(String userName, String password, String database) {this("localhost", userName, password, database);}
  public MyDerbyConnectionManager(String hostName, String userName, String password, String database) {
    setHostName(hostName);
    setUserName(userName);
    setPassword(password);
    setDatabase(database);
  }
  
  public Connection getConnection() throws SQLException {
    try {
      return getDerbyConnection(hostName, userName, password, database);
    } catch (Exception e) {throw new SQLException(e.getMessage());}
  }
  public void closeConnection(Connection conn) throws SQLException {conn.close();}

  public String getHostName() {return hostName;}
  public void setHostName(String hostName) {this.hostName = hostName;}

  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getDatabase() {
    return database;
  }
  public void setDatabase(String database) {
    this.database = database;
  }
  private static Connection getDerbyConnection(String hostName, String userName, String password, String database) throws Exception
  {
/*   try {
    Class.forName ("org.apache.derby.jdbc.ClientDriver").newInstance();
   }
   catch (Exception e) {System.out.println(e);}
*/    String URL = "jdbc:derby://" + hostName + "/" + database + ";create=true;user="+userName+";password="+ password;
//    return DriverManager.getConnection(URL);
    return DriverManager.getConnection("jdbc:derby://" + hostName + "/" + database);//, database, database);
  }
  
}
