/*
 * TreeAdapter.java
 *
 * Created on January 4, 2007, 5:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.tree;

import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.*;
import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.Hashtable;

/**
 *
 * @author Nachman Schachter
 */
public class TreeAdapter extends MouseAdapter
      implements TreeSelectionListener, TreeModelListener, DropTargetListener {
  protected /*static*/ Hashtable table = new Hashtable();

  /**
   * Creates a new instance of TreeAdapter
   */
  public TreeAdapter() {
  }
  
  // Implememt TreeSelectionListener
  public void valueChanged(TreeSelectionEvent e) {
    JTree tree = (JTree) e.getSource();
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    
    if (node != null) {
      Object userObject = node.getUserObject();
      NodeListener listener = this.getNodeListener(userObject);
      if (listener != null) {
        listener.select(node);
      }
    }
  }
  
  // Implement TreeModelListener
  public void treeNodesChanged(TreeModelEvent e) {}
  public void treeNodesInserted(TreeModelEvent e) {
    DefaultTreeModel model = (DefaultTreeModel) e.getSource();
    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) e.getTreePath().getLastPathComponent();
    int[] indices = e.getChildIndices();
    for (int i = 0; i < indices.length; i++)
      nodeAdded(model, (DefaultMutableTreeNode) parent.getChildAt(indices[i]));
  }
  public void treeNodesRemoved(TreeModelEvent e) {}
  public void treeStructureChanged(TreeModelEvent e) {
    if (e.getChildIndices() == null && e.getPath().length == 1)
      nodeAdded((DefaultTreeModel) e.getSource(), (DefaultMutableTreeNode) e.getTreePath().getLastPathComponent());
  }
  public void nodeAdded(DefaultTreeModel model, DefaultMutableTreeNode node) {
    NodeListener listener = getNodeListener(node.getUserObject());
    if (listener != null)
      listener.nodeAdded(model, node);
  }
  
  // Override MouseAdapter
  public void mouseReleased(MouseEvent e) {
    if (e.isPopupTrigger()) {
      triggerPopup(e);
    }
  }
  public void mousePressed(MouseEvent e) {
    if (e.isPopupTrigger()) {
      triggerPopup(e);
    }
  }

  //Implement DropTargetListener
  public void dragEnter(DropTargetDragEvent e) {}
  public void drop(DropTargetDropEvent e) {
    DropTarget target = (DropTarget) e.getSource();
    JTree tree = (JTree) target.getComponent();
    Point p = e.getLocation();
    TreePath path = tree.getPathForLocation(p.x, p.y);
    
    if (path != null) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
      if (node != null) {
        Object userObject = node.getUserObject();
        NodeListener listener = this.getNodeListener(userObject);
        if (listener != null) {
          listener.drop(node, e.getTransferable());
        }
      }
    }
  }
  public void dragOver(DropTargetDragEvent e) {}
  
  public void dragExit(DropTargetEvent e) {}
  public void dropActionChanged(DropTargetDragEvent e) {}
  
  // Helper Methods
  protected void triggerPopup(MouseEvent e) {
    JTree tree = (JTree) e.getSource();
    int x = e.getX();
    int y = e.getY();
    
    TreePath path = tree.getPathForLocation(x, y);
    if (path != null) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
      if (node != null) {
        Object userObject = node.getUserObject();
        NodeListener listener = this.getNodeListener(userObject);
        if (listener != null) {
          listener.triggerPopup(node, tree, x, y);
        }
      }
    }
  }
  
  // Node Registrartion
  public /*static*/ void registerNodeListener(Class objectClass, NodeListener l) {
    table.put(objectClass, l);
  }
  protected /*static*/ NodeListener getNodeListener(Object userObject) {
    NodeListener listener = null;
    if (userObject != null)
      listener = getNodeListener(userObject.getClass());
    return listener;
  }
  protected /*static*/ NodeListener getNodeListener(Class objectClass) {
    NodeListener listener = null;
    if (objectClass != null && table.containsKey(objectClass))
        listener = (NodeListener) table.get(objectClass);
    return listener;
  }
}
