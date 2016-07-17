/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jentry.sql.swing;

import ext.java.lang.reflect.ClassSupport;
import jentry.sql.Query;
import ext.java.util.EnumerationSupport;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Menachem
 */
public class DBTable extends JTable {
  public DBTable(){
  }
  // TODO 2.0 continue DBTable
  public void setRecordSource(String recordSource) throws SQLException {
    this.recordSource = recordSource;
    Query q = new Query(getConnection(), getRecordSource());
//    q.addRecordSetChangeListener(this);
//    q.addRecordSetChangeListener(getNavigationPane());
    setQuery(q);
    q.initialize();
    this.setModel(new DBTableModel());
  }


  public Query getQuery() {return query;}
  public void setQuery(Query query) {this.query = query;}

  public Connection getConnection() {return connection;}
  public void setConnection(Connection connection) {this.connection = connection;}

  public String getRecordSource() {return recordSource;}

  protected Query query = null;
  protected Connection connection = null;
  protected String recordSource = null;

  public class DBTableModel extends DefaultTableModel {
    public DBTableModel() {
      super();
      try {
        setDataVector(getQuery().getData(), getQuery().getColumnNames());
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
      Class c =  getQuery().getFieldType(getColumnName(columnIndex));
      return ClassSupport.toNonPrimitive(c);
    }

/*
    @Override
    public int getRowCount() {
      return getQuery().getRecordCount();
    }

    public int getColumnCount() {
      Enumeration e = getQuery().getFieldNames();
      return EnumerationSupport.getCount(e);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
      try {
        Query q = getQuery();
        q.setRecordNum(rowIndex);
System.out.println(i++);
        return q.getField(columnIndex);
      } catch (SQLException ex) {
        return null;
      }
    }
int i = 0;

    @Override
    public String getColumnName(int columnIndex) {return getQuery().getColumnHash().getKey(columnIndex);}
 
*/
 }



}
