/*
 * RuntimeBeanManager.java
 *
 * Created on December 18, 2006, 5:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.beans;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ext.java.beans.RuntimeBeanSupport.Visibility;
import ext.java.lang.PrimitiveClassEditor;
import ext.javax.swing.ComponentVector;
import ext.javax.swing.ComponentVector.Orientation;
import ext.java.util.ICreator;
import ext.java.util.SimpleCreator;

/**
 *
 * @author Nachman Schachter
 */
public class RuntimeBeanManager extends JPanel{

   Object bean;
   RuntimeBeanSupport beanSupport;

   Visibility visibility;
   boolean showDescription;

   JSplitPane verticalSplitPane;
   JPanel descriptionPane;
   JPanel propertyPane;
   JLabel noPropertyLabel;
   JScrollPane scrollPane;
   JSplitPane horizontalSplitPane;
   JPanel valuePane;
   ComponentVector<JLabel> labelPane;   
   ComponentVector<PropertyEditorField> editorPane;   
   JPanel buttonPane;

   JLabel labelTemplate;
   JTextField textTemplate;
   JButton buttonTemplate;

   JPopupMenu popup;   
   ActionListener popupListener;
   
   private static final int HEIGHT = 400;
   private static final int DESC_HEIGHT = 75;
   private static final int FIELD_HEIGHT = 20;
   private static final int VERTICAL_BAR = 5;
   private static final int PROPERTY_HEIGHT = HEIGHT - DESC_HEIGHT - VERTICAL_BAR;

   private static final int WIDTH = 200;
   private static final int LABEL_WIDTH = 100;
   private static final int BUTTON_WIDTH = 15;
   private static final int TEXT_WIDTH = 70;
   
   public RuntimeBeanManager() {
     this(null);
   }
   public RuntimeBeanManager(Object bean) {
     this(bean, Visibility.DEFAULT, true);
   }
   public RuntimeBeanManager(Object bean, Visibility  visibility, boolean showDescription) {
     this.bean = bean;
     this.visibility = visibility;
     this.showDescription = showDescription;

     initialize();
     layoutPanels();
     updatePropertyPane();
   }
   private void initialize() {

      beanSupport = null;

      verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
      descriptionPane = new JPanel();
      propertyPane = new JPanel();
      noPropertyLabel = new JLabel();
      scrollPane = new JScrollPane();
      horizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
      valuePane = new JPanel();
      buttonPane = new JPanel();
      
      ICreator creator = new ICreator() {
        public JLabel create() {
          JLabel label = new JLabel();
          label.setBorder(labelTemplate.getBorder());
          label.setBackground(labelTemplate.getBackground());
          label.setOpaque(labelTemplate.isOpaque());
          label.setFocusable(labelTemplate.isFocusable());
          label.setFont(labelTemplate.getFont());
          label.addFocusListener(labelFocusListener);
          return label;
        }
      };
      labelPane = new ComponentVector<JLabel>(creator, Orientation.VERTICAL, new Dimension(LABEL_WIDTH, FIELD_HEIGHT));
/*      creator = new ICreator() {
        public PropertyEditorField create() {return new PropertyEditorField();}
      };*/
      editorPane = new ComponentVector<PropertyEditorField> (
            new SimpleCreator(PropertyEditorField.class),
            Orientation.VERTICAL, new Dimension(TEXT_WIDTH, FIELD_HEIGHT));

     
      labelTemplate = new JLabel();
      textTemplate = new JTextField();
      buttonTemplate = new JButton();
      
//      labelTemplate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      labelTemplate.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY, 1));
      labelTemplate.setBackground(Color.WHITE);
      labelTemplate.setFont(textTemplate.getFont());
      labelTemplate.setFocusable(true);
      labelTemplate.setOpaque(true);

//      textTemplate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      textTemplate.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY, 1));
      textTemplate.setBackground(Color.WHITE);
      textTemplate.setOpaque(true);
      
      buttonTemplate.setText("...");
      
//      popup = new JPopupMenu();
/*      
this.setToolTipText("Bean Manager Pane");
verticalSplitPane.setToolTipText("Vertical Split Pane");
propertyPane.setToolTipText("Property Pane");
scrollPane.setToolTipText("Scroll Pane");
descriptionPane.setToolTipText("Description Pane");
labelPane.setToolTipText("Label Pane");
editorPane.setToolTipText("Text Pane");
valuePane.setToolTipText("Value Pane");
buttonPane.setToolTipText("Button Pane");
/*
descriptionPane.addComponentListener(new ComponentAdapter()  {
  public void componentResized(ComponentEvent e) {
    if (e.getID() == e.COMPONENT_RESIZED) {
//      System.out.println("Scroll Pane = " + scrollPane.getBounds());
//      System.out.println("Horiz Pane = " + horizontalSplitPane.getBounds());
//      System.out.println("Property Pane = " + propertyPane.getBounds());
    }
  }
});
 */
}
   protected void layoutPanels() {
     this.setLayout(new BorderLayout());

     //Vertical Pane
     if (showDescription == true) {
        add(verticalSplitPane, BorderLayout.CENTER);
        verticalSplitPane.setBottomComponent(descriptionPane);
        verticalSplitPane.setTopComponent(scrollPane);
        verticalSplitPane.setDividerSize(VERTICAL_BAR);
verticalSplitPane.setDividerLocation(300);
        verticalSplitPane.setContinuousLayout(true);
      }
      else {
        add(propertyPane, BorderLayout.CENTER);
      }
      //TODO 5 - Add Description Pane Functionality
     
      //Scroll Pane
      scrollPane.setViewportView(propertyPane);
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

      // PropertyPane
      propertyPane.setLayout(new BorderLayout());
      propertyPane.add(horizontalSplitPane, BorderLayout.NORTH);
      propertyPane.setOpaque(true);
      propertyPane.setBackground(Color.WHITE);

      // Horizontal SplitPane
      horizontalSplitPane.setLeftComponent(labelPane);
      horizontalSplitPane.setRightComponent(valuePane);
      horizontalSplitPane.setDividerSize(2);
      horizontalSplitPane.setDividerLocation(-1);

      //Label Pane
      labelPane.setLayout(new GridLayout(0, 1));

      // Value Pane
      valuePane.setLayout(new BorderLayout());
      valuePane.add(editorPane, BorderLayout.CENTER);

      //Text Field Pane
      editorPane.setLayout(new GridLayout(0, 1));
      
/*      //Popup Menu
      descriptionPane.addMouseListener(new PopupListener());
      JMenuItem mi = new JCheckBoxMenuItem("Show Description Area", showDescription);
      popup.add(mi);
 */
   }
   
  public void updatePropertyPane() {
    beanSupport = getBeanSupport();
    String[] propertyNames = null;

    if (beanSupport == null) {
      // Handle
    }
    else {
      propertyNames = beanSupport.getPropertyNames(visibility);
    }
    
    int newSize = 0;
    if (propertyNames != null && propertyNames.length != 0)
      newSize = propertyNames.length;

    labelPane.resize(newSize);   
    editorPane.resize(newSize);
    
    for (int i = 0; i < newSize; i++) {
      labelPane.getComponent(i).setText(propertyNames[i]);
      
      PropertyEditorField editorField = editorPane.getComponent(i);

      editorField.setBeanSupport(getBeanSupport());
      editorField.setPropertyName(propertyNames[i]);
      editorField.update();
    }
    
    revalidate();
  }
  
// <editor-fold defaultstate="collapsed" desc=" PropertyChangeSupport ">   
   private java.beans.PropertyChangeSupport propertyChangeSupport =  new java.beans.PropertyChangeSupport(this);

   public void addPropertyChangeListener(java.beans.PropertyChangeListener l) {
      propertyChangeSupport.addPropertyChangeListener(l);
   }

   public void removePropertyChangeListener(java.beans.PropertyChangeListener l) {
      propertyChangeSupport.removePropertyChangeListener(l);
   }
// </editor-fold> 

   public Object getBean() {
      return this.bean;
   }

   public void setBean(Object bean) {
      this.bean = bean;
      updatePropertyPane();
   }
   public RuntimeBeanSupport getBeanSupport() {
     if (getBean() == null)
       return null;
     if (beanSupport == null || beanSupport.getBean() != getBean())
       beanSupport = new RuntimeBeanSupport(bean);
     return beanSupport;
   }
   
   class PopupListener extends MouseAdapter {
      public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
          popup.show((Component)e.getSource(), e.getX(), e.getY());
        }
      }
      public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
          popup.show((Component)e.getSource(), e.getX(), e.getY());
        }
      }
     
   }
   protected final LabelFocusListener labelFocusListener = new LabelFocusListener();
   class LabelFocusListener extends FocusAdapter {
     public void focusGained(FocusEvent e) {
       JLabel label = (JLabel) e.getComponent();
       label.setBackground(SystemColor.textHighlight);
       label.setForeground(SystemColor.textHighlightText);
     }
     public void focusLost(FocusEvent e) {
       JLabel label = (JLabel) e.getComponent();
       label.setBackground(SystemColor.window);
       label.setForeground(SystemColor.textText);
     }
   }
//   protected final static TextActionListener textActionListner = new TextActionListener();
  static {
    // Load Property Editors
    PrimitiveClassEditor.loadEditors();
  }
}

