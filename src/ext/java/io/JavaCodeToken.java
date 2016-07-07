/*
 * JavaCodeToken.java
 *
 * Created on April 5, 2007, 5:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.io;

/**
 *
 * @author Nachman Schachter
 */
public class JavaCodeToken {
  public enum Type {CHAR, COMMENT, EOF, EOL, IDENTIFIER, NUMBER, OPERATOR,
    STRING, WHITESPACE};
  
  Type type;
  String val;
  int position;
  
  /** Creates a new instance of JavaCodeToken */
  public JavaCodeToken(Type type, String val, int position) {
    this.type = type;
    this.val = val;
    this.position = position;
  }
  public Type getType() {return type;}
//  public void setType(Type type) {this.type = type;}
  
  public String getVal() {return val;}
//  public void setVal(String val) {this.val = val;}
  
  public int getPosition() {return position;}
}
