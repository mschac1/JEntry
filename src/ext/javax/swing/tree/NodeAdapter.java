/*
 * NodeAdapter.java
 *
 * Created on February 2, 2007, 12:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Nachman Schachter
 */
public class NodeAdapter implements NodeListener {
  
  /** Creates a new instance of NodeAdapter */
  public NodeAdapter() {
  }
  
  //Implement NodeListener
  public void select(DefaultMutableTreeNode node) {select(node.getUserObject());}
  public void triggerPopup(DefaultMutableTreeNode node, JTree tree, int x, int y) {
    JPopupMenu popup = getPopup(node);
    if (popup != null) {
      popup.show(tree, x, y);
    }
  }
  public JPopupMenu getPopup(DefaultMutableTreeNode node) {return getPopup(node.getUserObject());}
  public void nodeAdded(DefaultTreeModel model, DefaultMutableTreeNode node) {
    DefaultMutableTreeNode[] nodes = getChildrenNodes(node.getUserObject());
    if (nodes != null)
      for (int i = 0; i < nodes.length; i++)
        model.insertNodeInto(nodes[i], node, i);
  }
  public void drop(DefaultMutableTreeNode node, Transferable t) {
    DataFlavor[] flavors = getSupportedDataFlavors();
    if (flavors != null) {
      for (int i = 0; i < flavors.length; i++) {
        if (t.isDataFlavorSupported(flavors[i])) {
          try {
            Object data = t.getTransferData(flavors[i]);
            drop(node, data);
            break;
          } catch (Exception e) {e.printStackTrace();}
        }
      }
    }
  }

  //Aditional Helper Methods
  public void select(Object userObject) {}
  public JPopupMenu getPopup(Object userObject) {return null;}
  public DefaultMutableTreeNode[] getChildrenNodes(Object userObject) {
    DefaultMutableTreeNode[] nodes = null;
    Object[] objects = getChildrenObjects(userObject);
    if (objects != null) {
      nodes = new DefaultMutableTreeNode[objects.length];
      for (int i = 0; i < objects.length; i++)
        nodes[i] = new DefaultMutableTreeNode(objects[i]);
    }
    return nodes;
  }
  public Object[] getChildrenObjects(Object userObject) {return null;}
  public void drop(DefaultMutableTreeNode node, Object data) {}
  public DataFlavor[] getSupportedDataFlavors() {return null;}
}
