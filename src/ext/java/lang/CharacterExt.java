/*
 * CharacterExt.java
 *
 * Created on April 30, 2007, 5:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

/**
 *
 * @author Nachman Schachter
 */
public class CharacterExt {
  public static boolean isLatinDigit(char c) {
    return (c >= '0' && c <= '9');
  }
  public static boolean isHexidecimal(char c) {
    return isLatinDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
  }
  public static boolean isOctal(char c) {
    return (c >= '0' && c < '8');
  }
  
  public static char parseChar(String s) throws CharacterFormatException {
    if (s.length() == 0)
      throw new CharacterFormatException();
    char c = s.charAt(0);
    if (s.length() == 1) {
        return c;
    }
    else if (c == '\\') {
      c = s.charAt(1);
      if (c == 'u' && s.length() == 6) {
         for (int i = 2; i < 6; i++) {
           if (!isHexidecimal(s.charAt(i)))
            throw new CharacterFormatException();
         }
         return (char) Integer.parseInt(s.substring(2), 16);
      }
      else if (isOctal(c) && s.length() < 5) {
         for (int i = 1; i < s.length() - 1; i++) {
           if (!isOctal(s.charAt(i)))
            throw new CharacterFormatException();
         }
         if (Integer.parseInt(s.substring(1)) > 377)
           throw new CharacterFormatException();
         return (char) Integer.parseInt(s.substring(1), 8);
      }
      else if (s.length() == 2) {
        switch(c) {
          case('b'): return '\b'; 
          case('t'): return '\t'; 
          case('n'): return '\n'; 
          case('f'): return '\f'; 
          case('r'): return '\r'; 
          case('"'): return '"'; 
          case('\''): return '\''; 
          case('\\'): return '\\'; 
        }
      }
    }
    throw new CharacterFormatException();
  }  
}
