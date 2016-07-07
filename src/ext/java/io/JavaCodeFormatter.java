/*
 * JavaCodeFormatter.java
 *
 * Created on April 11, 2007, 4:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.io;

import java.util.List;
import ext.java.io.JavaCodeToken.Type;
/**
 *
 * @author Nachman Schachter
 */
public class JavaCodeFormatter {
  
  /** Creates a new instance of JavaCodeFormatter */
  public static String reformat(String codeString, CodeStyle style) {
    String newCode = "";
    JavaCodeTokenizer tokenizer = new JavaCodeTokenizer(codeString);
    List<JavaCodeToken> tokens = tokenizer.getTokens();
    stripWhiteSpace(tokens);
    
for (int i = 0; i < tokens.size(); i++) {
  newCode += tokens.get(i).getVal();
}    
    return newCode;
  }

  public static void stripWhiteSpace(List<JavaCodeToken> tokens) {
    for (int i = 0; i < tokens.size(); i++) {
      Type type = tokens.get(i).getType();
      if (type == Type.WHITESPACE || type == Type.EOL  || type == Type.EOF)
        tokens.remove(i--);
    }
  }
}
