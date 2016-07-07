/*
 * Driver.java
 *
 * Created on July 8, 2005, 2:31 PM
 */

package test.parse;
import java.io.*;
//import math.arith.Prime;

/**
 *
 * @author Menachem & Shira
 */
public class Driver {
    
    /** Creates a new instance of Driver */
    public static void test() throws IOException {
//        testGUI();
//        testParser();
//        testPrimes();
//        testMatrix();
    }
    
    public static void testParser() {
        ArithParser p = new ArithParser();
        System.out.println(p.parse("4 + |1 + (5 - 8)|"));
    }
    public static void testGUI() {
        //math.gui.CalcGUI.main(null);
    }
    /*
    public static void testPrimes() {
        double val = 1;
        int n = 1000000;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                val *= (double) Prime.getPrime(i);
            }
            else {
                val /= (double) Prime.getPrime(i);
            }
            if (i % (n / 10) > (n / 10 - 3)) {
                System.out.println(i + " : " + val);
            } 
        }
//        System.out.println(val);
    }*/

    public static void testMatrix() {
/*        Double data[][] = {{3.0, -1.0, 1.0}, {2.0, 3.0, 8.0}};
        Matrix m = new Matrix();
        m.load(data);
        System.out.println(m.toString());
*/  }
    
}
