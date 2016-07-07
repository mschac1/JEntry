/*
 * PropertyEditorField.java
 *
 * Created on January 21, 2007, 4:29 PM
 */

package ext.java.beans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

/**
 *
 * @author Menachem & Shira
 */
public class PropertyEditorField extends JPanel {
  
  RuntimeBeanSupport beanSupport;
  String propertyName;
  JTextField text;
  JComboBox combo;
  JButton button;
  
  /** Creates a new instance of PropertyEditorField */
  public PropertyEditorField() {this(null, null);}

  public PropertyEditorField(RuntimeBeanSupport beanSupport, String propertyName) {
    TextListener textListener = new TextListener();
    ComboListener comboListener = new ComboListener();
    
    text = new JTextField();
    text.addActionListener(textListener);
    text.addFocusListener(textListener);
    
    combo = new JComboBox();
    combo.addActionListener(comboListener);

    button = new JButton();
    
    this.setLayout(new BorderLayout());
    this.beanSupport = beanSupport;
    this.propertyName = propertyName;

    update();
  }
  
  public void setBeanSupport(RuntimeBeanSupport beanSupport) {this.beanSupport = beanSupport;}
  public RuntimeBeanSupport getBeanSupport() {return beanSupport;}
  public void setPropertyName(String propertyName) {this.propertyName = propertyName;}
  public String getPropertyName() {return propertyName;}
  
  public void update() {
    // Clear data
    remove(text);
    remove(combo);
    remove(button);
    
    if (getBeanSupport() == null || getPropertyName() == null) {
      setToDefault();
      revalidate();
      return;
    }
    
    PropertyEditor editor = null;
    String[] tags = null;
    boolean writable = false;
    String stringValue = null;
    Object objectValue = null;
    
    try {
      editor = beanSupport.getPropertyEditor(propertyName);
      if (editor == null) {
        setToDefault();
      }
      else {
        stringValue = beanSupport.getPropertyAsText(propertyName);
        objectValue = beanSupport.getPropertyValue(propertyName);
        editor.setValue(objectValue);
        tags = editor.getTags();
        writable = beanSupport.isPropertyWritable(propertyName);

        if (editor.getTags() == null) {
          add(text, BorderLayout.CENTER);
          text.setEnabled(writable);
          text.setText(stringValue);
        }
        else {
          add(combo, BorderLayout.CENTER);
          combo.setModel(new DefaultComboBoxModel(tags));
          combo.setEnabled(writable);
          combo.setSelectedItem(stringValue);
        }
      }
    }
    catch (Exception e) {
      setToDefault();
    }
    
    revalidate();
  }
  protected void setToDefault() {
    text.setText("");
    text.setEnabled(false);
    add(text, BorderLayout.CENTER);
  }
  
  protected void setValue(String newTextValue) {
    if (getBeanSupport() != null && getPropertyName() != null &&
            getBeanSupport().isPropertyWritable(getPropertyName())) {
      try {
        getBeanSupport().setPropertyAsText(getPropertyName(), newTextValue);
      }
      catch (Exception e) {}
    }
  }
  
  class TextListener implements ActionListener, FocusListener {
    public void actionPerformed(ActionEvent evt) {
      setValue(text.getText());
    }
    public void focusLost(FocusEvent evt) {
      setValue(text.getText());
    }
    public void focusGained(FocusEvent evt) {
      
    }
  }
  
  class ComboListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      setValue((String) combo.getSelectedItem());
    }
  }
}
