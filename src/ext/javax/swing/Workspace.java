/*
 * Workspace.java
 *
 * Created on January 7, 2007, 4:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing;

//import judge.gui.DAOProjectManager;
import javax.swing.*;
import java.awt.*;
import ext.java.beans.RuntimeBeanManager;
//import newpackage.TestBean;
/**
 *
 * @author Menachem & Shira
 */
public class Workspace extends JFrame {

  public static final int EAST = 0;
  public static final int CENTER = 1;
  public static final int WEST = 2;
  public static final int SOUTH = 3;
  
  JPanel toolbarSpace;
  JTabbedPane[] workspaces;//southSpace, eastSpace, westSpace, centerSpace;
  
  /** Creates a new instance of Workspace */
  public Workspace() {
    initialize();
  }
  protected void initialize() {
    toolbarSpace = new JPanel();
    workspaces = new JTabbedPane[4];
    for (int i = 0; i < workspaces.length; i++) {
      workspaces[i] = new JTabbedPane();
    }

    JPanel workspace = new JPanel();
    JPanel northspace = new JPanel();
    JSplitPane verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane leftHorizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    JSplitPane rightHorizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
    this.setContentPane(new JPanel(new BorderLayout()));
    this.getContentPane().add(toolbarSpace, BorderLayout.NORTH);
    this.getContentPane().add(workspace, BorderLayout.CENTER);
    
    workspace.add(verticalSplit);
    
    verticalSplit.setBottomComponent(workspaces[SOUTH]);
    verticalSplit.setTopComponent(leftHorizontalSplit);
    
    leftHorizontalSplit.setLeftComponent(workspaces[EAST]);
    leftHorizontalSplit.setRightComponent(rightHorizontalSplit);

    rightHorizontalSplit.setLeftComponent(workspaces[CENTER]);
    rightHorizontalSplit.setRightComponent(workspaces[WEST]);
  }
  
  public void addPanel(JPanel panel, int location) {
    addPanel(null, panel, location);
  }
  public void addPanel(String title, JPanel panel, int location) {
    workspaces[location].add(title, panel);
  }

    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        Workspace workspace = new Workspace();
        workspace.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

//        workspace.addPanel(new RuntimeBeanManager(new TestBean()), SOUTH);
        workspace.addPanel("BeanManager", new RuntimeBeanManager(), SOUTH);
//        workspace.addPanel("ProjectManager", new DAOProjectManager(), CENTER);
        workspace.setVisible(true);
        workspace.setSize(500, 500);
      }
    });
  }

}
