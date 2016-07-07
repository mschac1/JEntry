/*
 * DBMetaSupport.java
 * Created on November 8, 2006, 5:52 PM
 */

package ext.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nachman Schachter
 */
public class DBMetaSupport {
  public static Table[] getTables(Connection conn) throws Exception {
    ArrayList<Table> tables = new ArrayList<Table>();
    String[] tableNames = getTableNames(conn);
    Table table = null;
    if (tableNames != null)
      for (int i = 0; i < tableNames.length; i++) {
        try {
          table = getTable(conn, tableNames[i]);
          if (table != null) tables.add(table);
        }
        catch (Exception e) {}
      }
      
    return tables.toArray(new Table[0]);
  } 
  
   public static String[] getTableNames(Connection conn) throws SQLException{
      String[] names = new String[0];
      ResultSet rs = null;
      try {
         DatabaseMetaData dbmd = conn.getMetaData();
         rs = dbmd.getTables(null, null, null, null);
         ArrayList list = new ArrayList();
         
         while(rs.next())
           list.add(rs.getString("TABLE_NAME"));
         names = (String[]) list.toArray(names);
      }
      finally {
         try {
            rs.close();
         }
         catch (Exception e) {}
      }
      return names;
   }
    public static Table getTable(Connection conn, String tableName) {
      Table table = new Table(tableName);
      String[] columnNames = null;
      ArrayList<String> types = new ArrayList<String>();
      try {
        columnNames = getColumnNames(conn, tableName, types);
      }
      catch (Exception e) {}
      
      for (int i = 0; i < columnNames.length; i++)
        table.addField(new Field(columnNames[i], types.get(i)));
      return table;
    }

    public static String[] getColumnNames(Connection conn, String tableName) throws Exception{
       return getColumnNames(conn, tableName, null);
    }
    public static String[] getColumnNames(Connection conn, String tableName, ArrayList typeList) throws Exception{
       String fields[] = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM " + tableName);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
          
            fields = new String[rsmd.getColumnCount()];
            for (int i = 0; i < fields.length; i++) {
               fields[i] = rsmd.getColumnName(i + 1);
               if (typeList != null)
                  typeList.add(rsmd.getColumnTypeName(i + 1));
            }
        }
        finally {
           try {
            rs.close();
            ps.close();
           }
           catch (Exception e) {}
        }
        return fields;
    }
    
    public static String[] getPrimaryKeyNames(Connection conn, String tableName) throws Exception{
       String pKeys[] = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {
          DatabaseMetaData dbmd = conn.getMetaData();
          rs = dbmd.getPrimaryKeys(null, null, tableName);
          ArrayList list = new ArrayList();
         
          while(rs.next())
            list.add(rs.getString(4)); // 4 = Column Name; 5 = Seq Num; 6 = Key Name
          pKeys = (String[]) list.toArray(pKeys);
        }
        finally {
           try {
            rs.close();
            ps.close();
        }
        catch (Exception e) {}
      }
      return pKeys;
   }    
}
