/*
 * FileFilterSupport.java
 *
 * Created on January 3, 2007, 3:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing.filechooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Nachman Schachter
 */
public class FileFilterSupport {
  public static FileFilter createFilter(String extension, String description) {
    final String fExtension = extension;
    final String fDescription = description;
    return new FileFilter() {
      public boolean accept(File file) {
        return (file.isDirectory() || FileFilterSupport.getExtension(file).equals(fExtension));
      }
      public String getDescription() {return fDescription;}
    };
  }
  public static String getExtension(File file) {
    String s = "";
    String filename = file.getName();
    int dot = filename.lastIndexOf('.');
    if (dot != -1) {
      s = filename.substring(dot + 1);
    }
    return s;
  }
}
