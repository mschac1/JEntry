/*
 * Field.java
 *
 * Created on December 10, 2006, 8:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql;

/**
 *
 * @author Menachem & Shira
 */
public class Field {
   
   protected String name;
   protected String type;
   protected Table table;
   
   /** Creates a new instance of Field */
   public Field() {this(null, null);}
   public Field(String name, String type) {this(name, type, null);}
   
   public Field(String name, String type, Table table) { 
      setName(name);
      setType(type);
      setTable(table);
   }
   /**
    * Getter for property name.
    * @return Value of property name.
    */
   public String getName() {
      return this.name;
   }

   /**
    * Setter for property name.
    * @param name New value of property name.
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Getter for property type.
    * @return Value of property type.
    */
   public String getType() {
      return this.type;
   }

   /**
    * Setter for property type.
    * @param type New value of property type.
    */
   public void setType(String type) {
      this.type = type;
   }
   public Table getTable() {return table;}
   public void setTable(Table table) {this.table = table;}
   
   public String toString() {return getType() + " " + getName();}
}
