/*
 * RuntimeBeanSupport.java
 *
 * Created on December 20, 2006, 10:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.beans;


import java.beans.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import ext.java.lang.EnumEditor;

//import utils.beans.RuntimeBeanSupport.Visibility;
/**
 *
 * @author HP_Owner
 */
public class RuntimeBeanSupport {
  
          
  protected Object bean;
  protected int visibility;
  
  protected PropertyDescriptor[] allPds;
  protected PropertyDescriptor[] visPds; //Visible pds
  protected String[] allNames;
  protected String[] visNames;
  protected Hashtable hashtable;

  public enum Visibility {PREFERRED, DEFAULT, EXPERT, ALL};
  
  public RuntimeBeanSupport(Object bean) {
    this.bean = bean;
    allPds = null;
    allNames = null;
    hashtable = null;
  }

  public Object getBean() {return bean;}

  public void serialize(String dest) throws FileNotFoundException {
    if (bean == null)
       return;
    XMLEncoder e = new XMLEncoder(new BufferedOutputStream(
      new FileOutputStream(dest)));
    e.writeObject(bean);
    e.close();
  }
  
  public static Object deserialize(String source) throws FileNotFoundException {
    XMLDecoder d = new XMLDecoder(new BufferedInputStream(
      new FileInputStream(source)));
    Object o = d.readObject();
    d.close();
    return o;
  }
  
  public String getPropertyDisplayName(String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return (String) pd.getDisplayName();
  }

  public Class getPropertyType(String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return (Class) pd.getPropertyType();
  }

  public String getPropertyDescription(String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return (String) pd.getShortDescription();
  }
  
  public boolean isPropertyReadable (String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return (pd.getReadMethod() != null);
  }
  public boolean isPropertyWritable (String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return (pd.getWriteMethod() != null);
  }
  
  public boolean isPropertyBound (String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return pd.isBound();
  }
  
  public boolean isPropertyConstrained (String propertyName)
    throws NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    return pd.isConstrained();
  }
  
  public Object getPropertyValue(String propertyName)
    throws IllegalAccessException, InvocationTargetException, 
    NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    Method m = (Method) pd.getReadMethod();
    return m.invoke(bean);
  }

  public void setPropertyValue(String propertyName, Object value)
    throws IllegalAccessException, InvocationTargetException,
    NoSuchPropertyException {
    PropertyDescriptor pd = getProperty(propertyName);
    if (pd == null) throw new NoSuchPropertyException();
    Method m = (Method) pd.getWriteMethod();
    m.invoke(bean, value);
  }

  protected PropertyDescriptor getProperty(String propertyName) {
     Hashtable hashtable = getHashtable();
     if (hashtable == null)
        return null;
     
     return (PropertyDescriptor) hashtable.get(propertyName);
  }
  
  protected Hashtable getHashtable() {
    if (hashtable == null) {
      String[] names = getAllPropertyNames();
      if (names != null) {
        PropertyDescriptor[] pds = getAllPropertyDescriptors();
        hashtable = new Hashtable();
        for (int i = 0; i < names.length; i++) {
          hashtable.put(names[i], pds[i]);
        }
      }
    }
    return hashtable;
  }

  public String[] getPropertyNames(Visibility v) {
    if (visNames == null) {
       PropertyDescriptor[] pds = getPropertyDescriptors(v);
       if (pds != null) {
          visNames = new String[pds.length];
          for (int i = 0; i < visNames.length; i++) {
             visNames[i] = pds[i].getName();
          }
       }
    }
    return visNames;
  }
  
  protected String[] getAllPropertyNames() {
    if (allNames == null) {
       PropertyDescriptor[] pds = getAllPropertyDescriptors();
       if (pds != null) {
          allNames = new String[pds.length];
          for (int i = 0; i < allNames.length; i++) {
             allNames[i] = pds[i].getName();
          }
       }
    }
    return allNames;
  }
  
  public PropertyDescriptor[] getPropertyDescriptors(Visibility v) {
    if (visPds == null) {
      PropertyDescriptor[] pds = getAllPropertyDescriptors();
      if (pds != null) {
        ArrayList list = new ArrayList();
        for(int i = 0; i < pds.length; i++) {
          if (isVisibleProperty(pds[i], v))
            list.add(pds[i]);
        }
        visPds = (PropertyDescriptor []) list.toArray(new PropertyDescriptor[0]);
      }
    }
    return visPds;
  }

  protected PropertyDescriptor[] getAllPropertyDescriptors() {
    if (allPds == null) {
      if (bean != null) {
        try {
          allPds = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
        }
        catch (Exception e) {}
      }
    }
    return allPds;
  }
  
  protected boolean isVisibleProperty(PropertyDescriptor pd, Visibility v) {
    if (v == Visibility.PREFERRED)
       return pd.isPreferred();
    else if (v == Visibility.DEFAULT)
       return (!(pd.isExpert() || pd.isHidden()));
    else if (v == Visibility.EXPERT)
       return (!pd.isHidden());
    else // v == Visibility.ALL
       return true;
  }
  
  public String getPropertyAsText(String propertyName)
    throws IllegalAccessException, InvocationTargetException,
    NoSuchPropertyException {
    Object o = getPropertyValue(propertyName);
    PropertyEditor editor = getPropertyEditor(propertyName);
    editor.setValue(o);
    return editor.getAsText();
  }
  public void setPropertyAsText(String propertyName, String text)
    throws IllegalAccessException, InvocationTargetException,
    NoSuchPropertyException {
    PropertyEditor editor = getPropertyEditor(propertyName);
    if (editor.getClass() == EnumEditor.class) {
      editor.setValue(getPropertyValue(propertyName));
    }
    editor.setAsText(text);
    Object value = editor.getValue();
    setPropertyValue(propertyName, value);
  }
  
  public PropertyEditor getPropertyEditor(String propertyName)
    throws NoSuchPropertyException {
    Class type = getPropertyType(propertyName);
//    return PropertyEditorManager.findEditor(type);
    return PropertyEditorManagerExt.findEditor(type);
  }
}
