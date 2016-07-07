/*
 * Table.java
 *
 * Created on December 10, 2006, 8:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Menachem & Shira
 */
public class Table {
  
   private ArrayList<Field> fields;
   private String name;
   
   /** Creates a new instance of Table */
   public Table() {this(null);}
   public Table(String name) {
     setName(name);
     fields = new ArrayList<Field>();
   }

   public String getName() {return this.name;}
   public void setName(String name) {this.name = name;}

   public Field[] getFields() {
      return fields.toArray(new Field[0]);
   }
   public void setFields(Field... fields) {
      this.fields = new ArrayList<Field>(Arrays.asList(fields));
   }
   public void addField(Field field) {
     if (field == null || field.getName() == null || field.getType() == null)
       throw new IllegalArgumentException();
       
     field.setTable(this);
     fields.add(field);
   }
   public Field getField(int i) {return fields.get(i);}
   
   public String toString() {
     String s = "";
     String newline = System.getProperty("line.separator");
     s += getName() + ":" + newline;
     for (int i = 0; i < fields.size(); i++)
       s += fields.get(i).toString() + newline;
     s += newline;
     return s;
   }
}
