/*
 * LabeledTreeNode.java
 *
 * Created on February 2, 2007, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Nachman Schachter
 */
public class LabeledTreeNode extends DefaultMutableTreeNode {
  String label;
  /**
   * Creates a new instance of LabeledTreeNode
   */
  public LabeledTreeNode() {this(null, null);}
  public LabeledTreeNode(Object userObject, String label) {
    super(userObject);
    setLabel(label);
  }
  
  public String getLabel() {return label;}
  public void setLabel(String label) {this.label = label;}
  
  public String toString() {return label;}
}
