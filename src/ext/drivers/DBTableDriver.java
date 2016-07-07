/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DBTableDriver.java
 *
 * Created on Apr 12, 2010, 10:10:08 PM
 */

package ext.drivers;

import ext.java.sql.DBMetaSupport;
import ext.java.sql.MSAccessConnectionManager;
import ext.java.sql.MySQLConnectionManager;
import ext.java.sql.Query;
import ext.javax.swing.db.DBTable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Menachem
 */
public class DBTableDriver extends javax.swing.JFrame {

    /** Creates new form DBTableDriver */
    public DBTableDriver() {
        initComponents();
        initMyComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    dBTable1 = new ext.javax.swing.db.DBTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    dBTable1.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane1.setViewportView(dBTable1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        .addGap(22, 22, 22))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
    testTable();

     // testQuery();
    }

  public  static void testTable() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
      new DBTableDriver().setVisible(true);
      }
      });

  }
  public static void testQuery() {
    try {
      Connection conn = new MSAccessConnectionManager("testDB1.mdb").getConnection();
      String[] s = DBMetaSupport.getTableNames(conn);
      for (int i = 0; i < s.length; i++) {
        System.out.println(s[i]);
      }
      s = DBMetaSupport.getColumnNames(conn, "MSysQueries");
      for (int i = 0; i < s.length; i++) {
        System.out.println(s[i]);
      }

    } catch (Exception ex) {
      Logger.getLogger(DBTableDriver.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private ext.javax.swing.db.DBTable dBTable1;
  private javax.swing.JScrollPane jScrollPane1;
  // End of variables declaration//GEN-END:variables

  private void initMyComponents() {
    try {
//      dBTable1.setConnection(new MSAccessConnectionManager("testDB1.mdb").getConnection());
      dBTable1.setConnection(new MySQLConnectionManager("password", "test").getConnection());

//            dBTable1.setRecordSource("Query2");
//      dBTable1.setRecordSource("qryTest1");
  dBTable1.setRecordSource("test2");

//      dBTable1.setRecordSource("SELECT Test1.ID, Test1.[last name], Sum(Test2.zip) AS SumOfzip FROM Test1, Test2 GROUP BY Test1.ID, Test1.[last name];");
//      System.out.println(dBTable1.getQuery().isEditable());
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }


}
