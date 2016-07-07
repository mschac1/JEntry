/*
 * NumberSupport.java
 *
 * Created on April 15, 2008, 4:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.lang;

/**
 * Provides helper methods for dealing with numbers
 * @author Nachman Schachter
 */
public class NumberSupport {
  
  /** Creates a new instance of NumberSupport */
  private NumberSupport() {
  }
  
  /**
   * Converts the primitive number represented by value to a primitive value of type
   * c
   */
  public static Number convert(Object value, Class c) {
    if (!ArraySupport.contains(new Class[] {byte.class, double.class,
      float.class, int.class, long.class, short.class, Byte.class, Double.class,
      Float.class, Integer.class, Long.class, Short.class}, c))
      throw new IllegalArgumentException("Can only convert to primitive number types");

    if (!ArraySupport.contains(new Class[] {Byte.class, Double.class,
      Float.class, Integer.class, Long.class, Short.class}, value.getClass()))
      throw new IllegalArgumentException("Can only convert from primitive number types");
    
    Number n = (Number) value;
    
    if (c.equals(byte.class) || c.equals(Byte.class))
      return n.byteValue();
    else if (c.equals(double.class) || c.equals(Double.class))
      return n.doubleValue();
    else if (c.equals(float.class) || c.equals(Float.class))
      return n.floatValue();
    else if (c.equals(int.class) || c.equals(Integer.class))
      return n.intValue();
    else if (c.equals(long.class) || c.equals(Long.class))
      return n.longValue();
    else //short.class Short.class
      return n.shortValue();
  }
  
  public static boolean equals(Number n1, Number n2) {
    Class strict = getCommonType(n1, n2);
    n1 = convert(n1, strict);
    n2 = convert(n2, strict);
    return n1.equals(n2);
  }
  public static int compare(Number n1, Number n2) {
    Class strict = getCommonType(n1, n2);
    return ((Comparable) convert(n1, strict)).compareTo((convert(n2, strict)));
  }

  private static Class getCommonType(Number... numbers) {
    Class c = null;
    boolean isByte = false;
    boolean isShort = false;
    boolean isInt = false;
    boolean isLong = false;
    boolean isFloat = false;
    boolean isDouble = false;
    
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] instanceof Byte)
        isByte = true;
      else if (numbers[i] instanceof Short)
        isShort = true;
      else if (numbers[i] instanceof Integer)
        isInt = true;
      else if (numbers[i] instanceof Long)
        isLong = true;
      else if (numbers[i] instanceof Float)
        isFloat = true;
      else // Double
        isDouble = true;
    }
    
    if (isDouble)
      return Double.class;
    else if (isFloat)
      return Float.class;
    else if (isLong)
      return Long.class;
    else if (isInt)
      return Integer.class;
    else if (isShort)
      return Short.class;
    else 
      return Byte.class;
  }
	public static Number toStrict(double d) {
		if (((byte) d) == d)
			return (byte) d;
		else if (((short) d) == d)
			return (short) d;
		else if (((int) d) == d)
			return (int) d;
		else if (((long) d) == d)
			return (long) d;
		else if (((float) d) == d)
			return (float) d;
		else
			return d;
	}
	public static Number add(Number n1, Number n2) {
    if (ArraySupport.contains(wholeNumberTypes, n1.getClass()) &&
						ArraySupport.contains(wholeNumberTypes, n2.getClass()))
			return toStrict(n1.longValue() + n2.longValue());

		double val = n1.doubleValue() + n2.doubleValue();
		if (toStrict(val).getClass() == double.class)
			return val;
		else
			return (float) val;
	}
	public static Number subtract(Number n1, Number n2) {
    if (ArraySupport.contains(wholeNumberTypes, n1.getClass()) &&
						ArraySupport.contains(wholeNumberTypes, n2.getClass()))
			return toStrict(n1.longValue() - n2.longValue());

		double val = n1.doubleValue() - n2.doubleValue();
		if (toStrict(val).getClass() == double.class)
			return val;
		else
			return (float) val;
	}

	public static Number multiply(Number n1, Number n2) {
    if (ArraySupport.contains(wholeNumberTypes, n1.getClass()) &&
						ArraySupport.contains(wholeNumberTypes, n2.getClass()))
			return toStrict(n1.doubleValue() * n2.doubleValue());

		double val = n1.doubleValue() * n2.doubleValue();
		if (toStrict(val).getClass() == double.class)
			return val;
		else
			return (float) val;
	}
	public static Number divide(Number n1, Number n2) {
    if (ArraySupport.contains(wholeNumberTypes, n1.getClass()) &&
						ArraySupport.contains(wholeNumberTypes, n2.getClass()))
			return toStrict(n1.doubleValue() / n2.doubleValue());

		double val = n1.doubleValue() / n2.doubleValue();
		if (toStrict(val).getClass() == double.class)
			return val;
		else
			return (float) val;
	}

	public static Class[] wholeNumberTypes = {byte.class, int.class, long.class,
		short.class, Byte.class, Integer.class, Long.class, Short.class};

}
