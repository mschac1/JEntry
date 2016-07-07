/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ext.java.io;

import java.util.List;
import java.util.Vector;
import ext.java.lang.NumberSupport;
import ext.java.lang.reflect.AmbiguousNameException;
import ext.java.lang.reflect.ClassSupport;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Menachem & Shira
 */
public class ParseDriver {
	public static void main(String[] args) {
		/*
		Package[] p = Package.getPackages();
		String[] names = new String[p.length];
		for (int i = 0; i < p.length; i++) {
			names[i] = p[i].getName();
		}
		Arrays.sort(names);
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}

*/
		testParseAdapter();
	}
	public static void testParseAdapter() {
/**/
		String[] testStrings = {"3","a+2","5+6/(-12+  6)", "v.getClass()", "x[1]",
		"2 > 2 ", "1 + x[1] <= a+2", "(x[(6-2)/2])", "v.getClass().getClass()",
		"x.length", "st.TT_EOF", "x[1.0]", "Class.forName('java.lang.Short')",
		"java.lang.Double.NaN", "Integer.class", "('jump' + 'ed' + ' up').toUpperCase()",
		"'a' < 'b'"}; 
		Object[] solutions = {(byte) 3, (byte) 2, (byte) 4, Vector.class, 2,
		false, false, 3, Class.class,
		3, -1, 2, Short.class,
		java.lang.Double.NaN,Integer.class, "JUMPED UP", true};

/**/

//		String[] testStrings = {"Integer"};
//		Object[] solutions = {Integer.class};

		ExpressionParser p = new ExpressionParser();
			p.addVariable("x", new int[] {0,2,3});
			Vector v = new Vector();
			v.add(new Integer(3));
			p.addVariable("v", v);
			p.addVariable("a", new Short((short) 0));
			p.addVariable("B", 2.0);
			p.addVariable("p", p);
			p.addVariable("st", new StreamTokenizer(new StringReader("")));
			for (int i = 0; i < testStrings.length; i++) {
				try {
					Object val = p.parse(testStrings[i]);
					if (val.equals(solutions[i])) {
							System.out.println("Test " + i + " successful.");
					}
					else {
						if (solutions[i].equals("?")) {
							System.out.println("Test " + i + ": The parse of " +
								testStrings[i] + " returned " + val);
						}
						else {
							System.out.println("Test " + i + " failed. The parse of " +
								testStrings[i] + " returned " + val + " instead of " + solutions[i]);
						}
					}
				} catch (Exception ex) {System.out.println("Test " + i + " failed: " + ex);}
			}

	}

	private static void testJavaCodeTokenizer() {
		JavaCodeTokenizer jct = new JavaCodeTokenizer("\\*hi *\\bobby.getX(5) == \u1234 'bc' \"hi\" [First Name] -5");
		List<JavaCodeToken> l = jct.getTokens();
		for (JavaCodeToken tok : l) {
			System.out.println("" + tok.getType() + " " + tok.getVal() + " " + tok.getPosition());
		}
	}
}
