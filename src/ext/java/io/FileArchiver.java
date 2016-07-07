/*
 * FileArchiver.java
 *
 * Created on July 3, 2007, 6:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 *
 * @author Menachem & Shira
 */
public class FileArchiver {
  
  /** Creates a new instance of FileArchiver */
  public FileArchiver() {
  }
  
  public static void main(String[] args) {
    String file1 = "c:\\Menachem\\temp\\nagafire1.jpg";
    String file2 = "c:\\Menachem\\temp\\batteryOrder.pdf";
    String source = file2;
    int index = source.indexOf('.');
//    testCopy(source,  source.substring(0, index) + "_1" + source.substring(index));
    showBytes(source);
//    byte b = (byte) 156;
//    System.out.println(b);
  }
  public static void testCopy(String source, String dest) {
    File file, file2;
    FileInputStream in;
    FileOutputStream out;
    byte[] bytes;
    
    try {
      file = new File(source);
      file2 = new File(dest);
      
      file2.createNewFile();
      
      in = new FileInputStream(file);
      bytes = new byte[in.available()];
      in.read(bytes);
/*
      for (int i = 0; i < bytes.length; i++) {
        if (bytes[i] < 0)
          bytes[i] += 256;
      }
*/      
      out = new FileOutputStream(file2);
      out.write(bytes);
      
      in.close();
      out.close();
      
    } catch (Exception e) {e.printStackTrace();}

  }
  public static void showBytes(String source) {
    File file;
    FileInputStream in;
    byte[] bytes;
    
    try {
      file = new File(source);
      
      in = new FileInputStream(file);
      bytes = new byte[in.available()];
      in.read(bytes);

      in.close();

      int[] counts = new int[256];
      for (int i = 0; i < bytes.length; i++) {
        if (bytes[i] >= 0)
          counts[bytes[i]]++;
        else
          counts[bytes[i] + 256]++;
      }
      Arrays.sort(counts);
      for (int i = 0; i < counts.length; i++) {
        System.out.println(i + ": " + counts[i]);
      }
      System.out.println("Total Bytes: " + bytes.length);
    } catch (Exception e) {e.printStackTrace();}
  }
  
}
