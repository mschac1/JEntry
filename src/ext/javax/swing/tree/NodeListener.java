/*
 * NodeListener.java
 *
 * Created on February 2, 2007, 12:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.tree;

import java.awt.datatransfer.Transferable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Nachman Schachter
 */
public interface NodeListener {
  public void select(DefaultMutableTreeNode node);
  public void triggerPopup(DefaultMutableTreeNode node, JTree tree, int x, int y);
  public void nodeAdded(DefaultTreeModel model, DefaultMutableTreeNode node);
  public void drop(DefaultMutableTreeNode node, Transferable t);
}
