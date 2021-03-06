/*
 * DBDriver.java
 *
 * Created on January 29, 2008, 8:32 PM
 */

package jentry.drivers;

import ext.java.lang.NumberSupport;
import jentry.sql.swing.DBContinuousForm.Subform;
import ext.java.sql.*;
import ext.java.util.DateFormatSupport;
import jentry.sql.swing.DBContinuousForm;
import jentry.sql.swing.DBForm;
import jentry.sql.swing.DBTextField;
import jentry.sql.events.ContinuousFormEvent;
import jentry.sql.events.ContinuousFormListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author  Menachem & Shira
 */
public class xDBFormDriver extends javax.swing.JFrame {

  public xDBFormDriver (int i) {
    initComponents();
    try {
      dbf.setConnection(new MSAccessConnectionManager("testDB1.mdb").getConnection());
      dbf.setRecordSource("Test1");
    } catch (SQLException e) {e.printStackTrace();}
  }
  public xDBFormDriver(Subform subform) {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//    subform.setPreferredSize(new Dimension(200, 100));
    DBContinuousForm dbcf = new DBContinuousForm();
getContentPane().add(dbcf, BorderLayout.CENTER);
    
//    dbcf.setInsertsAllowed(false);
//    dbcf.setSubformTemplate(subform);
    dbcf.addContinuousFormListener(new ContinuousFormListener() {
      public void subformAdded(ContinuousFormEvent evt) {
        Subform subform = evt.getSubform();
//        subform.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        DBTextField dbtf = new DBTextField("age");
        dbtf.setDefaultValue("18");
        dbtf.setPreferredSize(new Dimension(75, 20));
        subform.add(dbtf);
/*
        DBCheckBox dbcb = new DBCheckBox("fat?");
        dbcb.setDefaultValue(true);
        subform.add(dbcb);
*/
        dbtf  = new DBTextField("last name");
        dbtf.setPreferredSize(new Dimension(75, 20));
        subform.add(dbtf);
/*
        dbtf = new DBTextField("testDateTime");
        dbtf.setPreferredSize(new Dimension(150, 20));
        dbtf.setFormatter(DateFormatSupport.STD_MEDIUM_TIME);
        subform.add(dbtf);
*/
/*        
        JPanel jp = new JPanel();
        JTextField jtx = new JTextField();
        jtx.setPreferredSize(new Dimension(100, 30));
        jp.add(jtx);
        subform.add(jp);
*/
      }
      public void subformRemoved(ContinuousFormEvent evt) {}
    });
    
    try {
      conn = new MSAccessConnectionManager("g:\\Other\\testDB1.mdb").getConnection();
      dbcf.setConnection(conn);
      dbcf.setRecordSource("Test1");
      dbcf.setSort("[last name]");
//			dbcf.setFilter("[last name] like \"J\"");
      dbcf.requery();
    } catch (SQLException e) {e.printStackTrace();}
//getContentPane().add(dbcf, BorderLayout.CENTER);
    pack();
  }

  
  /** Creates new form DBDriver */
  public xDBFormDriver() {
    initComponents();
    initMyComponents();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
  public void initMyComponents() {
/*    
    DBNavigationPane dbn = new DBNavigationPane();
    getContentPane().add(dbn, BorderLayout.SOUTH);
    dbf.setNavigationPane(dbn);
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);    
    DBCheckBox dbcb = new DBCheckBox("fat?");
    dbcb.setDefaultValue(true);
    dbf.add(dbcb);
*/

		DBTextField dbtf  = new DBTextField("last_name");
		dbtf.setPreferredSize(new Dimension(75, 20));
//		dbtf.setDefaultValue("Smith");
    dbf.add(dbtf);
		dbtf  = new DBTextField("first_name");
		dbtf.setPreferredSize(new Dimension(75, 20));
    dbf.add(dbtf);
		dbtf = new DBTextField("[first_name] + \" \" + [last_name]");
		dbtf.setPreferredSize(new Dimension(75, 20));
    dbf.add(dbtf);
		System.out.println(dbtf.getFieldName());

		/*
    dbtf  = new DBTextField("fat?");
    dbtf.setPreferredSize(new Dimension(75, 20));
    dbf.add(dbtf);
    
    dbtf  = new DBTextField("age");
    dbtf.setDefaultValue("18");
    dbtf.setPreferredSize(new Dimension(75, 20));
    dbf.add(dbtf);
    
    dbtf = new DBTextField("testDouble");
    dbtf.setPreferredSize(new Dimension(75, 20));
    dbf.add(dbtf);
/*
    dbtf = new DBTextField("testDateTime");
    dbtf.setPreferredSize(new Dimension(150, 20));
    dbtf.setFormatter(DateFormatSupport.STD_MEDIUM_TIME);
    dbf.add(dbtf);
*/    
//    DBComboBox dbcmb = new DBComboBox("first name", "Jason", "Amy", "Bob");
//    DBComboBox dbcmb = new DBComboBox();
/*
    DBComboBox dbcmb = new DBComboBox("table");
    dbcmb.setEditable(true);
    try {
      dbcmb.setConnection(new MSAccessConnectionManager("g:\\Other\\testDB1.mdb").getConnection());
      dbcmb.setDisplayColumn(2); //Last Name
//      dbcmb.setDefaultValue("Schachter");
//      dbcmb.setDisplayColumn(5); //age
//      dbcmb.setValueColumn(7); //time
      dbcmb.setRecordSource("Test2");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
 * /
//    Timestamp ts = new Timestamp(1000), ts2 = new Timestamp(200000);
//    DBComboBox dbcmb = new DBComboBox("testDateTime", ts, ts2);
//    dbcmb.setFormatter(DateFormatSupport.STD_MEDIUM_TIME);
    dbcmb.setPreferredSize(new Dimension(100, 20));
//    dbcmb.setDefaultValue("Buffet");
    dbf.add(dbcmb);
    
    DBLabel dbl = new DBLabel("first name");
    dbl.setPreferredSize(new Dimension(100, 20));
    dbf.add(dbl);
/*
    DBForm childForm = new DBForm();
    try {
      childForm.setRecordSource("Test2");
    } catch (SQLException ex) {}
    childForm.setParentChildLink(new String[][] {{"table", "ID"}});
    childForm.setPreferredSize(new Dimension(200, 100));
    childForm.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//    childForm.setLayout(new FlowLayout());
    dbtf = new DBTextField("table name");
    dbtf.setPreferredSize(new Dimension(100, 20));
    childForm.add(dbtf);
    dbf.add(childForm);
*/
    try {
      conn = new MSAccessConnectionManager("testDB1.mdb").getConnection();
      dbf.setConnection(conn);
      dbf.setRecordSource("Test1");
//      dbf.setFilter("table = 1");
    } catch (SQLException e) {e.printStackTrace();}

    pack();
  }
  
  Connection conn;

  public static void main(String args[]) {
    testDBForm();
//    testDBContinuousForm();
//    testNetBeans();
  }
  public static void testDBForm() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new xDBFormDriver().setVisible(true);
      }
    });
  }
  
  public static void testDBContinuousForm() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new xDBFormDriver(null).setVisible(true);
      }
    });
  }
	public static void testNetBeans() {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new xDBFormDriver(1).setVisible(true);
      }
    });

	}
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
  DBForm dbf = new DBForm();
}
