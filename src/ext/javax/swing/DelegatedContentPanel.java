/*
 * DelegatedContentPanel.java
 *
 * Created on February 18, 2008, 4:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;

/**
 *
 * @author Nachman Schachter
 */
public class DelegatedContentPanel extends JPanel {
  //TODO 5 - add more delegated methods
  
  /** Creates a new instance of DelegatedLayoutPanel */
  public DelegatedContentPanel() {this(new JPanel());}
  
  public DelegatedContentPanel(JPanel contentPane) {
    super();
    setContentPane(contentPane);
  }
  
  public void addAncestorListener(AncestorListener listener) {
    if (getDelegateContent())
      getContentPane().addAncestorListener(listener);
    else
      super.addAncestorListener(listener);
  }
  
  public void addContainerListener(ContainerListener l) {
    if (getDelegateContent())
      getContentPane().addContainerListener(l);
    else
      super.addContainerListener(l);
  }

  public void addComponentListener(ComponentListener l) {
    if (getDelegateContent())
      getContentPane().addComponentListener(l);
    else
      super.addComponentListener(l);
  }
  
  public void addImpl(Component comp, Object constraints, int index) {
    if (getDelegateContent())
      getContentPane().add(comp, constraints, index);
    else
      super.addImpl(comp, constraints, index);
  }  
  
  public Component getComponent(int n) {
    if (getDelegateContent())
      return getContentPane().getComponent(n);
    else
      return super.getComponent(n);
  }
  
  public int getComponentCount() {
    if (getDelegateContent())
      return getContentPane().getComponentCount();
    else
      return super.getComponentCount();
  }
  
  public void remove(Component comp) {
    if (getDelegateContent())
      getContentPane().remove(comp);
    else
      super.remove(comp);
  }
  
  public void remove(int index) {
    if (getDelegateContent())
      getContentPane().remove(index);
    else
      super.remove(index);
  }
  
  public void remove(MenuComponent popup) {
    if (getDelegateContent())
      getContentPane().remove(popup);
    else
      super.remove(popup);
  }
  
  public void removeAll() {
    if (getDelegateContent())
      getContentPane().removeAll();
    else
      super.removeAll();
  }
  
  public void revalidate() {
    if (getDelegateContent())
      getContentPane().revalidate();
    else
      super.revalidate();
  }
  
  public void setLayout(LayoutManager mgr) {
    if (getDelegateContent())
      getContentPane().setLayout(mgr);
    else
      super.setLayout(mgr);
  }
  
  public boolean getDelegateContent() {return delegateContent;}
  public void setDelegateContent(boolean delegateContent) {this.delegateContent = delegateContent;}
  
  protected JPanel getContentPane() {return contentPane;}
  protected void setContentPane(JPanel contentPane) {this.contentPane = contentPane;}
  
  protected boolean delegateContent;
  protected JPanel contentPane;
}
