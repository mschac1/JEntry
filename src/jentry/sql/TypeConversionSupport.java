/*
 * TypeConversionSupport.java
 *
 * Created on February 11, 2008, 9:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jentry.sql;

import ext.java.lang.ArraySupport;
import ext.java.lang.NumberSupport;
import ext.java.lang.StringSupport;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.text.Format;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This class provides static methods that facilitate type conversion form the
 * SQL types provided in java.sql.Types to their corresponding java types as well
 * as Object/String conversions with or without Formatters
 * @author Menachem & Shira
 */
public class TypeConversionSupport {
  
  private TypeConversionSupport() {
  }

  /** Convert a String to its appropriate database sql compatible class */
  public static Object stringToObject(String source, Class type) throws Exception {
    return stringToObject(source, type, null);
  }
  
  /** Convert a String to its appropriate database sql compatible class. Use the
   *  input Formatter if its not null
   */
  public static Object stringToObject(String source, Class type, Format formatter) throws Exception {
    // Parse the text to an object

    Object val = null;
    try {
      // If there's a formatter loaded, use it
      if (formatter != null) {
        val = formatter.parseObject(source);
        if (val instanceof java.util.Date) {
          // A typical date parser will use a java.util.date so we need to convert to the appropriate sql type
          java.util.Date date = (java.util.Date) val;
          if (type.equals(java.sql.Date.class))
            val = new java.sql.Date(date.getTime());
          else if (type.equals(Time.class))
            val = new Time(date.getTime());
          else if (type.equals(Timestamp.class))
            val = new Timestamp(date.getTime());
          else
            throw new IllegalArgumentException("Expected " + type +". Found java.util.Date");
        }
      }
      else { // If not then we have to parse ourselves
        if (type == BigDecimal.class)
          val = new BigDecimal(source);
        else  if (type == boolean.class) { // Boolean.parseBoolean is stupid
          if (source.equalsIgnoreCase("TRUE"))
            val = true;
          else if (source.equalsIgnoreCase("FALSE"))
            val = false;
          else
            throw new IllegalArgumentException(source + " is not a valid boolean value.");
        }
        else  if (type == byte.class)
          val = Byte.parseByte(source);
        else  if (type == java.sql.Date.class)
          val = java.sql.Date.valueOf(source);
        else  if (type == double.class)
          val = Double.parseDouble(source);
        else  if (type == float.class)
          val = Float.parseFloat(source);
        else  if (type == int.class)
          val = Integer.parseInt(source);
        else  if (type == long.class)
          val = Long.parseLong(source);
        else  if (type == short.class)
          val = Short.parseShort(source);
        else  if (type == String.class)
          val = source;
        else  if (type == Time.class)
          val = Time.valueOf(source);
        else  if (type == Timestamp.class)
          val = Timestamp.valueOf(source);
        else if (type == null)
          val = source;
        else
          throw new Exception("Cannot parse variables of type " + type);
      }
    }
    catch (Exception ex) {
      // Most parsers do not recognize "", but nulls will appear this way, so we need to handle this ourselves 
      if (source.equals("")) {
        if (type.isPrimitive()) {
          // All the relevant primitives can accept a value of (byte) 0, expect for boolean
          if (type.equals(boolean.class))
            val = false;
          else
            val = (byte) 0;
        }
        else { // All non-privitives should be assigned null
          val = null;
        }
      }
      else {
        throw ex;
      }
    }
    return val;
  }
  
  /**
   *  Convert an object to a String. This method simply uses the sources's
   *  toString method, unless the source is null, in which case an empty String
   *  is returned
   */
  public static String objectToString(Object source) {
    return objectToString(source, null);
  }
  
  /** 
   *  Convert the source Object to a String. Use the formatter if not null,
   *  otherwise use the source's toString method. Returns "" if the source is
   *  null
   */
  public static String objectToString(Object source, Format formatter) {
    if (source == null)
      return "";
    else if (formatter == null)
      return source.toString();
    else
      return formatter.format(source);
  }

  /** Uses the column to determine the apropriate getter and calls that method */
  public static Object getFieldValue(ResultSet rs, int column) throws SQLException {
    int fieldType = rs.getMetaData().getColumnType(column);
    return getFieldValue(rs, column, fieldType);
    
  }
  /** Uses the field type to determine the apropriate getter and calls that method */
  private static Object getFieldValue(ResultSet rs, int column, int fieldType) throws SQLException {
    String methodName = "get" + getMethodType(fieldType);
    try {
      Method m = ResultSet.class.getDeclaredMethod(methodName, int.class);
      return m.invoke(rs, column);
    } catch (Exception ex) {throw new SQLException(ex.getMessage());}
  }
  
  /** Uses the sql type to determine the apropriate getter and calls that method */
  public static Object getFieldValue(ResultSet rs, String fieldName, int fieldType) throws SQLException {
    String methodName = "get" + getMethodType(fieldType);
    try {
      Method m = ResultSet.class.getDeclaredMethod(methodName, String.class);
      return m.invoke(rs, fieldName);
    } catch (Exception ex) {
      throw new SQLException(ex.getMessage());
    }
  }
  
  /** Uses the sql type to determine the apropriate updater and calls that method */
  public static void updateFieldValue(ResultSet rs, String fieldName, int fieldType, Object value) throws SQLException {
    Class c = getJavaClass(fieldType);
    // If the class we have and the one we need are two primitives that can be converted
    // with a forced cast, do it
    if (value != null && !c.isAssignableFrom(value.getClass()) && value instanceof Number) {
      value = NumberSupport.convert(value, c);
    }
    try {
      String methodName = "update" + getMethodType(fieldType);
      Method m = ResultSet.class.getDeclaredMethod(methodName, String.class, c);

//     rs.updateString(fieldName, (String) value);
      m.invoke(rs, fieldName, value);
    } catch (Exception ex) {throw new SQLException(ex.getMessage());}
  }
  
  /** Returns the apropriate method suffix for the input SQL type.
     For example, if the java class that corresponds to the type is long, the
     method will return "Long", so that it could be appended either to "get" or
     "update" to create the method name "getLong" or "setLong" */
  private static String getMethodType(int fieldType) {
    String methodType;
    switch (fieldType) {
      // These 4 SQL types have method suffixes that are not the same as the
      // corresponding java type
      case(Types.BINARY): methodType = "Bytes"; break;
      case(Types.VARBINARY): methodType = "Bytes"; break;
      case(Types.LONGVARBINARY): methodType = "BinaryStream"; break;
      case(Types.LONGVARCHAR): methodType = "CharacterStream"; break;
      
      // All other types, have method suffixes that are the same as the
      // corresponding java type; however it needs ro be uppercase
      default: {
        methodType = getJavaClass(fieldType).getSimpleName();
        methodType = StringSupport.capitalize(methodType);
      }
    }
    return methodType;
  }
  
  public static Class getJavaClass(int sqlType) {return map.get(sqlType);}
  
  private static Hashtable<Integer, Class <?>> map;
  static {
    map = new Hashtable<Integer, Class <?>>();
    // Add all of the SQL types and their corresponding java types to the map
    map.put(new Integer(Types.ARRAY), java.sql.Array.class);
    map.put(new Integer(Types.DECIMAL), BigDecimal.class);
    map.put(new Integer(Types.NUMERIC), BigDecimal.class);
    map.put(new Integer(Types.BLOB), Blob.class);
    map.put(new Integer(Types.BIT), boolean.class);
    map.put(new Integer(Types.BOOLEAN), boolean.class);
    map.put(new Integer(Types.TINYINT), byte.class);
    map.put(new Integer(Types.BINARY), byte[].class);
    map.put(new Integer(Types.VARBINARY), byte[].class);
    map.put(new Integer(Types.LONGVARBINARY), InputStream.class);
    map.put(new Integer(Types.CLOB), Clob.class);
    map.put(new Integer(Types.DATE), Date.class);
    map.put(new Integer(Types.DOUBLE), double.class);
    map.put(new Integer(Types.FLOAT), double.class);
    map.put(new Integer(Types.REAL), float.class);
    map.put(new Integer(Types.INTEGER), int.class);
    map.put(new Integer(Types.BIGINT), long.class);
    map.put(new Integer(Types.OTHER), Object.class);
    map.put(new Integer(Types.JAVA_OBJECT), Object.class);
    map.put(new Integer(Types.STRUCT), Object.class);
    map.put(new Integer(Types.REF), Ref.class);
    map.put(new Integer(Types.SMALLINT), short.class);
    map.put(new Integer(Types.CHAR), String.class);
    map.put(new Integer(Types.VARCHAR), String.class);
    map.put(new Integer(Types.LONGVARCHAR), Reader.class);
    map.put(new Integer(Types.TIME), Time.class);
    map.put(new Integer(Types.TIMESTAMP), Timestamp.class);
   
/*  Unknown class conversion
    map.put(new Integer(Types.NULL), ??.class);
    map.put(new Integer(Types.DISTINCT), ??.class);
    map.put(new Integer(Types.DATALINK), ??.class);*/
   }
}
