/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ext.java.lang.reflect;

/**
 *
 * @author Menachem & Shira
 */
public class ClassSupport {

	public static Class forName(String className) throws ClassNotFoundException, AmbiguousNameException{
		Package[] p = Package.getPackages();
		Class c = null;
		for (int i = 0; i < p.length; i++) {
			try {
				Class c2 = Class.forName(p[i].getName() + "." + className);
				if (c == null) {
					c = c2;
				}
				else {
					throw new AmbiguousNameException("Class name " + className + " is ambiguous");
				}
			} catch (ClassNotFoundException ex) {}
		}
		if (c == null)
			throw new ClassNotFoundException("Could not find class " + className);
		return c;
	}

        public static Class<?> toNonPrimitive(Class<?> c) {
          if (c.equals(int.class))
            return Integer.class;
          else if (c.equals(boolean.class))
            return Boolean.class;
          else if (c.equals(double.class))
            return Double.class;
          else if (c.equals(short.class))
            return Short.class;
           else if (c.equals(float.class))
            return Float.class;
          else if (c.equals(long.class))
            return Long.class;
          else if (c.equals(char.class))
            return Character.class;
          else
            return c;
        }

}
