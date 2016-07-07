/*
 * JavaCodeTokenizer.java
 *
 * Created on April 5, 2007, 5:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.io;

import ext.java.lang.ArraySupport;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import ext.java.io.JavaCodeToken.Type;
import java.util.ListIterator;

/**
 *
 * @author Nachman Schachter
 */
public class JavaCodeTokenizer {
    ArrayList<JavaCodeToken> list = new ArrayList<JavaCodeToken>();
    boolean parsed;
    String code;
    
  /** Creates a new instance of JavaCodeTokenizer */
  public JavaCodeTokenizer(String code) {
    parsed = false;
    this.code = code;
  }
  
  protected void parse() {
    if (parsed) return;
    for(int i = 0; i < code.length(); i++) {
      char c = code.charAt(i);
      if (c == '\0')
        list.add(new JavaCodeToken(Type.EOF, "\0", i));
      else if (c == '\n')
        list.add(new JavaCodeToken(Type.EOL, "\n", i));
      else if (Character.isWhitespace(c))
        list.add(new JavaCodeToken(Type.WHITESPACE, Character.toString(c), i));
      else if (c == '/' && code.charAt(i + 1) == '/')
        i = addSlashSlash(i);
      else if (c == '/' && code.charAt(i + 1) == '*')
        i = addSlashStar(i);
      else if (c == '"')
        i = addString(i);
      else if (c == '\'')
        i = addChar(i);
      else if (Character.isJavaIdentifierStart(c))
        i = addIdentifier(i);
      else if (Character.isDigit(c) || (c == '.' && Character.isDigit(code.charAt(i + 1))))
        i = addNumber(i);
      else
        i = addOperator(i);
    }
  }
  public List<JavaCodeToken> getTokens() {
    parse();
    return list;
  }
  
  private int addIdentifier(int begin) {
    int end = begin + 1;
    while (code.length() > end + 1 && Character.isJavaIdentifierPart(code.charAt(end)))
      end++;
    list.add(new JavaCodeToken(Type.IDENTIFIER, code.substring(begin, end), begin));
    return end - 1;
  }

  private int addNumber(int begin) {
    int end = begin + 1;
    if (code.length() > end && (code.charAt(end) == '+' || code.charAt(end) == '-')) {
      char sign = code.charAt(end);
      while (code.length() > end && code.charAt(end) == toggle(sign))
        end++;
    }
    while (code.length() > end && Character.isDigit(code.charAt(end)))
      end++;
    if (code.length() > end && code.charAt(end) == '.')
      end++;
    while (code.length() > end && Character.isDigit(code.charAt(end)))
      end++;
    if (code.length() > end && code.charAt(end) == 'e')
      end++;
    while (code.length() > end && Character.isDigit(code.charAt(end)))
      end++;
    
    list.add(new JavaCodeToken(Type.NUMBER, code.substring(begin, end), begin));
    return --end;
  }
  private char toggle(char sign) {
    if (sign == '+')
      return '-';
    else if (sign == '-')
      return '+';
    else
      return '\0';
  }

  private int addChar(int begin) {
    int end = begin + 1;
    end = code.indexOf('\'', end) + 1;
    if (code.length() > end && code.charAt(end) == '\'')
      end++;
    list.add(new JavaCodeToken(Type.CHAR, code.substring(begin, end), begin));
    return end - 1;
  }

  private int addString(int begin) {
    int end = begin + 1;
    boolean slash = false;
    char c;
    while (true) {
      c = code.charAt(end);
      if (c == '"' && !slash)
        break;
      else if (c == '\\' && !slash)
        slash = true;
      else
        slash = false;
      end++;
    }
    list.add(new JavaCodeToken(Type.STRING, code.substring(begin, end + 1), begin));
    return end;
  }

  private int addSlashSlash(int begin) {
    int end = begin + 1;
    while (code.length() > end && code.charAt(end) != '\n')
      end++;
    list.add(new JavaCodeToken(Type.COMMENT, code.substring(begin, end), begin));
    return end - 1;
  }

  private int addSlashStar(int begin) {
    int end = begin + 1;
    boolean slash = false;
    char c1, c2;
    while (!(code.charAt(end) == '*' && code.charAt(end + 1) == '/'))
      end++;
    list.add(new JavaCodeToken(Type.COMMENT, code.substring(begin, end + 2), begin));
    return end + 1;
  }

  private int addOperator(int begin) {
    int end = begin + 1;
    if (code.length() - 1 > end) {
      char c1 = code.charAt(begin);
      char c2 = code.charAt(end);

      if (c1 == '<' && c2 == '<' && code.charAt(end + 1) == '=')
        end += 1;
      else if (c2 == '>' && c2 == '>') {
        if (code.charAt(end + 1) == '=')
          end += 1;
        else if (code.charAt(end + 1) == '>') {
          if (code.charAt(end + 2) == '=')
            end += 2;
          else
            end += 1;
        }
      }
      else if (c1 == c2 && ArraySupport.contains(doublePair, c1))
        ;
      else if (c1 == '=' && ArraySupport.contains(equalPair, c1))
        ;
      else
        end--;
    }
    else {
      end--;
    }
      
    list.add(new JavaCodeToken(Type.OPERATOR, code.substring(begin, end + 1), begin));
    return end;
  }
    
  static char[] doublePair = {'-', '+', '<', '>', '&', '|', '='};
  static char[] equalPair = {'!', '^', '&', '*', '-', '+', '|', '<', '>', '/', '%'};
  
  public static void main(String... args) {
//    String filename = "E:\\Menachem\\Java\\DAOGenerator\\Test.txt";
    String filename = "E:\\Menachem\\Java\\DAOGenerator\\src\\ext\\java\\io\\CodeStyle.java";
    String s = null;
    try {
      FileReader reader = new FileReader(filename);
      char[] buff = new char[5000];
      int length = reader.read(buff);
      s = new String(buff, 0, length + 1);
    } catch (IOException e) {}
    
//    JavaCodeTokenizer jct = new JavaCodeTokenizer(s);
    System.out.println(JavaCodeFormatter.reformat(s, new CodeStyle()));
  }
}
