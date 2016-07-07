/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ext.java.lang.reflect;

import java.lang.reflect.Method;

/**
 *
 * @author Menachem & Shira
 */
public class MethodSupport {

	@SuppressWarnings("empty-statement")
	public static Method getMethod(Object source, String funcName, Object... params) {
		return getMethod(source.getClass().getMethods(), source, funcName, params);
	}
	public static Method getStaticMethod(Class sourceClass, String funcName, Object... params) {
		return getMethod(sourceClass.getMethods(), null, funcName, params);
	}

	private static Method getMethod(Method[] methods, Object source, String funcName, Object... params) {
		boolean foundName = false;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(funcName)) {
				Class[] types = methods[i].getParameterTypes();
				try {
					methods[i].invoke(source, params);
					return methods[i];
				} catch (Exception ex) {;}
			}
		}
		if (foundName) {
			throw new IllegalArgumentException("Cannot match arguments");
		}
		else {
			throw new IllegalArgumentException(source.toString() + " has no function named " + funcName);
		}
	}

}
