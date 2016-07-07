/*
 * JavaCodeGenerator.java
 *
 * Created on March 3, 2007, 8:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.io;

import ext.java.io.CodeStyle.BraceStyle;
import ext.java.lang.ArraySupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 *
 * @author Menachem & Shira
 */
public class JavaCodeGenerator {
  
  protected PrintWriter out;
  protected CodeStyle style;
  protected int indent;
  protected String current;
  protected StringBuffer buffer;
  
  protected static Hashtable<Character, String> hash = new Hashtable<Character, String>();

  /** Creates a new instance of JavaCodeGenerator */
  public JavaCodeGenerator(String filename)
    throws IOException {
    this(filename, new CodeStyle());
  }
  public JavaCodeGenerator(String filename, CodeStyle style)
    throws IOException {
    this.style = style.clone();
    File file = new File(filename); 
    out = new PrintWriter(new FileWriter(file));
    current = "";
  }
   
  public void print(Object obj) {
    if (current.equals(""))
      current = indentString();
    current += obj.toString();
  }
  public void println(Object obj) {
    print(obj);
    println();
  }
  public void println() {
//    print("");
    flush();
  }

  public void openBrace(Object obj) {
    print(obj);
    if (style.openBraceStyle == BraceStyle.SAME_LINE)
       print(" ");
    else // if (style.openBraceStyle == BraceStyle.NEXT_LINE) {
       println();
    
    println("{");
    indent++;
  }
  public void closeBrace() {
    indent--;
    println("}");
  }
  
  public void flush() {
    if (current == "")
      out.print(style.newline.getNewlineString());
    String[] lines = parseLine();
    for (int i = 0; i < lines.length; i++)
      out.print(lines[i] + style.newline.getNewlineString());
    
    out.flush();
    current = "";
  }
  protected String[] parseLine() {
    //TODO 5 - Consider Comments
    ArrayList<String> lines = new ArrayList<String>();
    StringBuffer line = new StringBuffer(current);
    while (line.length() > style.lineWidth) {
      int index = split(line);
      lines.add(line.substring(0, index));
      line = new StringBuffer(line.substring(index));
      if (line.length() != 0)
        line.insert(0, continueIndentString());
    }
    if (line.length() != 0)
      lines.add(line.toString());
    return lines.toArray(new String[0]);
  }
  protected int split(StringBuffer line) {
    int splitIndex = -1;
    int sqIndex = 0;
    int dqIndex = 0;
    boolean singlequote = (line.charAt(0) == '\'');
    boolean doublequote = (line.charAt(0) == '"');
    boolean slash = (line.charAt(0) == '\\');
    
    for (int i = 1; i < style.lineWidth; i++) {
      char c = line.charAt(i);
      if (c == '\\') {
        slash = !slash;
      }
      else {
        if (c == '"' && !slash) {
          doublequote = !doublequote;
          dqIndex = i;
        }
        else if (c == '\'' && !slash) {
          singlequote = !singlequote;
          if (singlequote)
            sqIndex = i;
        }
        slash = false;
      }
    }
    if (doublequote) { //Line ends in midquote
      if (dqIndex < style.lineWidth / 2) {
        int insertIndex = style.lineWidth - 3;
        boolean liveSlash = false;
        int i = insertIndex - 1;
        while (line.charAt(i) == '\\') {
          liveSlash = !liveSlash;
          i--;
        }
        if (liveSlash) {
          insertIndex--;
        }
        else {
          for (int j = 0; j < 4; j++) {
            int k = insertIndex - 2 - j;
            if (line.charAt(k) == '\\' && line.charAt(k + 1) == 'u') {
              i = k - 1;
              liveSlash = true;
              while (line.charAt(i) == '\\') {
                liveSlash = !liveSlash;
                i--;
              }
              if (liveSlash)
                insertIndex -= j + 2;
            }
          }
        }
        line.insert(insertIndex, "\" +\"");
        splitIndex = insertIndex + 3;
      }
      else {
        splitIndex = dqIndex;
      }
    }
    else if (singlequote) {
      splitIndex = sqIndex;
    }
    else {
      splitIndex = -1;
      int start = 0;
      while (Character.isWhitespace(line.charAt(start)))
        start++;
      for (int i = style.lineWidth; i > start; i--) {
        if (isValidSplit(line.charAt(i - 1), line.charAt(i))) {
          splitIndex = i;
          break;
        }
      }
      if (splitIndex == -1) {
        splitIndex = line.length();
        for (int i = style.lineWidth + 1; i < line.length(); i++) {
          if (isValidSplit(line.charAt(i - 1), line.charAt(i))) {
            splitIndex = i;
            break;
          }
        }
      }
    }
    return splitIndex;
  }
  protected boolean isValidSplit(char left, char right) {
    if (Character.isWhitespace(left) || Character.isWhitespace(right))
      return true;
    else if (Character.isJavaIdentifierStart(left)) { // Identifier
      if (Character.isJavaIdentifierPart(right))
        return false;
      else
        return true;
    }
    else if (Character.isDigit(left)) { // Number
      if (Character.isJavaIdentifierPart(right) || right == '.')
        return false;
      else
        return true;
    }
    return !isSpecialPair(left, right);
  }
  protected boolean isSpecialPair(char left, char right) {
    char[] doublePair = {'-', '+', '<', '>', '&', '|', '='};
    char[] equalPair = {'!', '^', '&', '*', '-', '+', '|', '<', '>', '/'};
    
    if (left == right && ArraySupport.contains(doublePair, left))
      return true;
    else if (right == '=' && ArraySupport.contains(equalPair, left))
      return true;
    else
      return false;
  }
  
  protected String tabString() {
    String tab;
    if (style.tabsToSpaces) {
      tab = "";
      for (int i = 0; i < style.tabLength; i++)
         tab += " ";
    }
    else {
      tab = "\t";
    }
    return tab;
  }

  protected String indentString() {
    String s = "";
    for (int i = 0; i < indent; i++)
       s += tabString();
    return s;
  }
  protected String continueIndentString() {
    String s = indentString();
    for (int i = 0; i < style.getContinueIndent(); i++)
      s+= tabString();
    return s;
  }
  
  public CodeStyle getStyle() {return style;}
  public void setStyle(CodeStyle style) {this.style = style;}

  public void finalize() {
    out.close();
  }

  public static void main(String[] args) {
    CodeStyle style = new CodeStyle();
    String dest = "codeTest.java";
    JavaCodeGenerator core = null;
    try {
      core = new JavaCodeGenerator(dest, style);
    } catch(IOException e) {e.printStackTrace();}
/*    
    core.println("package test1;");
    core.println();
    core.openBrace("public class codeTest");
      core.openBrace("public void foo()");
        core.println("int x = 1;");
        core.println("if (x > 1)");
          core.indent++;
          core.println("x = 2;");
          core.indent--;
      core.closeBrace();
    core.closeBrace();
 */
/*    
    core.println("\"01234567911234567892123456789312345678941234567895123456789612345678971234\\\\u000056789812345\"67899123456789");
    core.println("\"01234567911234567892123456789312345678941234567895123456789612345678\\\\\\\\u000097123456789812345\"67899123456789");
    core.println("\"01234567911234567892123456789312345678941234567895123456789612345678\\\\\\\\\\u000097123456789812345\"67899123456789");
*/ 
  }
}
