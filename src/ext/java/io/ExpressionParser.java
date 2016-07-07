/*
 * ExpressionParser.java
 *
 * Created on July 8, 2005, 2:16 PM
 */

package ext.java.io;

import ext.java.lang.NumberSupport;
import ext.java.lang.reflect.AmbiguousNameException;
import ext.java.lang.reflect.ClassSupport;
import ext.java.lang.reflect.MethodSupport;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

//TODO 4 - should we use JavaCodeTokenizer for clarity

/**
 *
 * @author Menachem & Shira
 */
public class ExpressionParser {

	protected StreamTokenizer st;
	protected Reader r;
	protected Stack tokBuffer;
	protected HashMap<String,Method> functions;
	protected HashMap<String, Object> variables;

	/** Creates a new instance of ExpressionParser */
	public ExpressionParser() {
		functions = new HashMap<String,Method>();
		variables = new HashMap<String, Object>();

		try {
			addStaticFunction("IF", ExpressionParser.class.getMethod("functionIF", Boolean.class, Object.class, Object.class));
		} catch (Exception ex) {ex.printStackTrace();}
		try {
			addStaticFunction("NZ", ExpressionParser.class.getMethod("functionNZ", Object.class, Object.class));
		} catch (Exception ex) {ex.printStackTrace();}
	}

	public Object parse (String s) throws IOException {
		if (s == null)
			throw new IOException("Cannot parse a null String");
			return parse(new StringReader(s));
	}
	public Object parse(Reader r) throws IOException {
		this.r = r;
		st = new StreamTokenizer(r);
		st.parseNumbers();

		st.eolIsSignificant(true);
		st.ordinaryChar('/');
		st.ordinaryChar('.');
		st.wordChars('_', '_');
		return parse();
	}

	protected Object parse(StreamTokenizer st) throws IOException {
		this.st = st;
		return parse();
	}
	protected synchronized Object parse() throws IOException {
		if (st == null)
			throw new RuntimeException("Tokenizer not initiated");

		tokBuffer = new Stack();

		return parseStatement();
	}

	protected  Object parseStatement() throws IOException {
		Object o =  parseExpression();
		if (nextToken().getType() != Token.EOF)
			throw new IOException("Syntax Error: Finished parsing but there are additional tokens in the Stream");
		return o;
	}

	protected Object parseExpression() throws IOException {
		return parseLogicalOr();
	}
	protected Object parseLogicalOr() throws IOException {
		Object val = parseLogicalAnd();

		Token token = nextToken();

		while (token.getType() == '|') {
			token = nextToken();
			if (token.getType() != '|')
				throw new IOException("Syntax Error: Found |"+ token +".|| expected.");
			val = or(val, parseLogicalAnd());
			token = nextToken();
		}
		pushBack(token);

		return val;
	}
	protected Object parseLogicalAnd() throws IOException {
		Object val = parseComparison();
		Token token = nextToken();

		while (token.getType() == '&') {
			token = nextToken();
			if (token.getType() != '&')
				throw new IOException("Syntax Error: Found &"+ token +".&& expected.");
			val = and(val, parseComparison());
			token = nextToken();
		}
		pushBack(token);

		return val;
	}
	protected Object parseComparison() throws IOException {
			Object val = parseAdditionSubtraction();

			Token token = nextToken();

			while (token.getType() == '=' || token.getType() == '!' ||
						 token.getType() == '<' || token.getType() == '>' ) {
					if (token.getType() == '=') {
							val = (equals(val, parseAdditionSubtraction()));
							token = nextToken();
					}
					else if (token.getType() == '!') {
							Token token2 = nextToken();
							if (token2.getType() == '=') {
								val = (!equals(val,parseAdditionSubtraction()));
								token = nextToken();
							}
							else {
								throw new IOException ("Found !, expected !=");
							}
					}
					else if (token.getType() == '>') {
							Token token2 = nextToken();
							if (token2.getType() == '=') {
								val = (compare(val,parseAdditionSubtraction()) >= 0);
								token = nextToken();
							}
							else {
								pushBack(token2);
								val = (compare(val,parseAdditionSubtraction()) > 0);
								token = nextToken();
							}
					}
					else if (token.getType() == '<') {
							Token token2 = nextToken();
							if (token2.getType() == '=') {
								val = (compare(val,parseAdditionSubtraction()) <= 0);
								token = nextToken();
							}
							else {
								pushBack(token2);
								val = (compare(val,parseAdditionSubtraction()) < 0);
								token = nextToken();
							}
					}

			}
			pushBack(token);
			return val;
	}
	protected	Object parseAdditionSubtraction() throws IOException {
			Object val = parseMultiplicationDivision();

			Token token = nextToken();

			while (token.getType() == '+' || token.getType() == '-') {
					if (token.getType() == '+') {
							val = add(val, parseMultiplicationDivision());
							token = nextToken();
					}
					else {
							val = subtract(val,parseMultiplicationDivision());
							token = nextToken();
					}
			}
			pushBack(token);
			return val;
	}
	protected Object parseMultiplicationDivision() throws IOException {
			Object val = parseArrayAndMember();

			Token token = nextToken();
//        while (token.getType() == '*' || token.getType() == '/')
			while (token.getType() == '/' || token.getType() == '*') {
					if (token.getType() == '/') {
							val = divide(val, parseArrayAndMember());
							token = nextToken();
					}
					else if (token.getType() == '*') {
							val = multiply(val, parseArrayAndMember());
							token = nextToken();
					}
			}

			pushBack(token);
			return val;
	}

	protected Object parseArrayAndMember() throws IOException {
		Object val = parseBaseUnit();
		Token token = nextToken();
		while (token.getType() == '[' || token.getType() == '.') {
			if (token.getType() == '[') {
				val = parseArray(val);
				token = nextToken();
				if (token.getType() != ']') throw new IOException("Syntax Error: Missing right bracket");
				token = nextToken();
			}
			else if (token.getType() == '.') {
				val = parseMember(val);
				token = nextToken();
			}
		}
		pushBack(token);
		return val;
	}
	protected Object parseBaseUnit() throws IOException {
			Token token = nextToken();

			// Parenthesis
			if (token.getType() == '(') {
					Object val = parseExpression();
					token = nextToken();
					if (token.getType() != ')') throw new IOException("Syntax Error: Missing right parenthesis");
					return val;
			}
			else if (token.getType() == '[') {
					Object val = parseExpression();
					token = nextToken();
					if (token.getType() != ']') throw new IOException("Syntax Error: Missing right bracket");
					return val;
			}

			// Numeric Negation
			else if (token.getType() == '-') {
					return negate(parseBaseUnit());
			}
			// Logical Negation
			else if (token.getType() == '!') {
				return not(parseBaseUnit());
			}

			// Numbers
			else if (token.getType() == Token.NUMBER) {
					pushBack(token);
					return parseNumber();
			}

			// Fuctions and variables and Strings
			else if (token.getVal() != null) {
					pushBack(token);
					return parseNonNumber();
			}

			throw new IOException("Syntax Error: Cannot continue. Found unexpected " + token);
	}

	protected Object parseNumber() throws IOException {
		return nextToken().getVal();
	}

	protected Object parseNonNumber() throws IOException {
		Object val = null;
		Token token = nextToken();

		//String
		if (token.getType() == '\'' || token.getType() == '"')
			return token.getVal();

		if (token.getType() == Token.WORD) {
			//Boolean
			if (token.getVal().equals("true") || token.getVal().equals("false"))
				return Boolean.valueOf(token.getVal().toString());

			//Null
			if (token.getVal().equals("null"))
				return null;

			else {
				Token token2 = nextToken();

				//Function
				if (token2.getType() == '(') {
					val = parseFunction((String) token.getVal(), null);
					token = nextToken();
					if (token.getType() != ')') throw new IOException("Syntax Error: Missing right parenthesis");
					return val;
				}

				//Variable, Class
				else {
					pushBack(token2);

					//Variable
					if (variables.containsKey(token.getVal())) {
						return variables.get(token.getVal());
					}

					// Static member or methods
					else {
						Class c = null;

						// Unqualified Classname
						try {
							c = ClassSupport.forName((String) token.getVal());
						} catch (ClassNotFoundException ex) {}
							catch (AmbiguousNameException ex) {
							throw new IOException(ex.getMessage());
						}

						// Qualified Classname
						if (c == null) {
							token2 = nextToken();
							String className = (String) token.getVal();
							while (token2.getType() == '.') {
								token2 = nextToken();
								if (token2.getType() != Token.WORD) {
									throw new IOException("Invalid classname: " + className + "." + token2);
								}
								else {
									className += "." + (String) token2.getVal();
									// Try to load the class (If we don't do this on every iteration we will not be ableto correctly resolve static member fields of Qualified classes
									try {
										c = Class.forName(className);
										break;
									} catch (ClassNotFoundException ex) {}

									token2 = nextToken();
								}
							}
							if (className.equals((String) token.getVal()))
									throw new IOException("Could not find class " + className);
						}
						if (c!= null) {
							token = nextToken();
							if (token.getType() != '.')
								throw new IOException("Excpected '.' (dot operator), found " + token);
							token = nextToken();
							if (token.getType() != Token.WORD)
								throw new IOException("Excpected member name, found " + token);
							String name = (String) token.getVal();
							token = nextToken();

							//Static method
							if (token.getType() == '('){
								val = parseFunction(name, c, true);
								if (nextToken().getType() != ')') throw new IOException("Syntax Error: Missing right parenthesis");
								return val;
							}
							//Static member
							else {
								pushBack(token);
								if (name.equals("class"))
									return c;
								try {
									return c.getField(name).get(null);
	//								parseMember(c, true);
								} catch (NoSuchFieldException ex) {
									throw new IOException("Class " + c + " does not have a public static member " + name);
								} catch (SecurityException ex) {
									throw new IOException("The member " + name + "of class " + c + " is not accessable");
								} catch (IllegalArgumentException ex) {
									throw new IOException("The member " + name + "of class " + c + " is not static");
								} catch (IllegalAccessException ex) {
									throw new IOException("The member " + name + "of class " + c + " is not accessable");
								}
							}
						}
						throw new IOException("Variable " + token.getVal() + " not found.");
					}
				}
			}
		}
		throw new IOException("Syntax Error: Invalid use of " + token);
	}
	protected Object parseFunction(String funcName, Object source) throws IOException {
		return parseFunction(funcName, source, false);
	}
	protected Object parseFunction(String funcName, Object source, boolean staticFlag) throws IOException {
		Vector<Object> params = new Vector<Object>();
		Token token = nextToken();
		// Only get args if there are some
		if (token.getType() != ')') {
			pushBack(token);
			params.add(parseExpression());
			token = nextToken();
			while (token.getType() == ',') {
				params.add(parseExpression());
				token = nextToken();
			}
		}
		pushBack(token);
			Method m = null;
			if (source == null) { // User Provided Function
				m = functions.get(funcName.toUpperCase());
			}
			else { // Member function
				try {
					if (staticFlag)
						m = MethodSupport.getStaticMethod((Class) source, funcName, params.toArray(new Object[0]));
					else
						m = MethodSupport.getMethod(source, funcName, params.toArray(new Object[0]));
				} catch (Exception ex) {throw new IOException("Cannot find function " + funcName);}
			}
			if (m == null)
				throw new IOException("Cannot find function " + funcName);
			try {
				return m.invoke(source, params.toArray(new Object[0]));
			} catch (IllegalAccessException ex) {ex.printStackTrace();}
				catch (IllegalArgumentException ex) {
					throw new IOException("Invalid arguments for function " + funcName);}
				catch (InvocationTargetException ex) {ex.printStackTrace();}
		return null;
	}
	protected Object parseArray(Object array) throws IOException {
		Object val = null;
		boolean intFlag = true;
		double indexDouble = 0;
		try {
			//Redo this so only integer types work
			 indexDouble = Double.parseDouble(parseExpression().toString());
		} catch (Exception ex) {intFlag = false;}
		int index = (int) indexDouble;
		if (index != indexDouble)
			intFlag = false;

			try {
				val = Array.get(array, index);
			} catch (IllegalArgumentException ex) {throw new IOException(array + " is not an array");}
				catch (ArrayIndexOutOfBoundsException ex) {throw new IOException("" + index + " is out of bounds for array " + array);}

		return val;
	}
	protected Object parseMember(Object obj) throws IOException {
		Object val = null;
		Token token = nextToken();
		Class c;

		if (token.getVal() == null || token.getType() != Token.WORD)
			throw new IOException(" The member operator(.) must be followed by a field or function name");
		Token token2 = nextToken();


		if (token2.getType() == '(') { //Member Function
			val = parseFunction((String) token.getVal(), obj);
			if (nextToken().getType() != ')') throw new IOException("Syntax Error: Missing )");

		}
		else { //Member Field
			pushBack(token2);
			try {
				Field f = null;
				if (obj.getClass().isArray()) {
					if (token.getVal().equals("length")){
						return Array.getLength(obj);
					}
					else throw new NoSuchFieldException();
				}
				else if (obj instanceof Class) {
					f = ((Class) obj).getField((String) token.getVal());

				}
				else {
					f = obj.getClass().getField((String) token.getVal());
				}
				return f.get(obj);
			} catch (NoSuchFieldException ex) {
				throw new IOException("The object " + obj + " does not have a member " + token.getVal());
			} catch (SecurityException ex) {
				throw new IOException("The member " + token.getVal() + " of object " + obj + " is inaccessable");
			} catch (IllegalAccessException ex) {
				throw new IOException("The member " + token.getVal() + " of object " + obj + " is inaccessable");
			}

		}
		return val;
	}

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Evaulation Methods ">

	protected Object add(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Number && rhs instanceof Number) {
			return NumberSupport.add((Number) lhs, (Number) rhs);
		}
		else if (lhs instanceof String || rhs instanceof String) {
			return lhs.toString() + rhs.toString();
		}
		throw new IOException("Cannot perform + operation on " + lhs + " and " + rhs);
	}

	protected Object subtract(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Number && rhs instanceof Number) {
			return NumberSupport.subtract((Number) lhs, (Number) rhs);
		}
		throw new IOException("Cannot perform - operation on " + lhs + " and " + rhs);
	}

	protected Object multiply(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Number && rhs instanceof Number) {
			return NumberSupport.multiply((Number) lhs, (Number) rhs);
		}
		throw new IOException("Cannot perform * operation on " + lhs + " and " + rhs);
	}

	protected Object divide(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Number && rhs instanceof Number) {
			return NumberSupport.divide((Number) lhs, (Number) rhs);
		}
		throw new IOException("Cannot perform / operation on " + lhs + " and " + rhs);
	}

	protected Object negate(Object val) throws IOException {
		if (val instanceof Number)
			return multiply(val, (byte) -1);
		else
			throw new IOException("Cannot perform - operation on " + val);
	}

	protected boolean not(Object val) throws IOException {
		if (val instanceof Boolean)
			return !((Boolean) val);
			throw new IOException("Cannot perform ! operation on " + val);
	}

	protected boolean and(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Boolean && rhs instanceof Boolean)
			return (Boolean) lhs && (Boolean) rhs;
		else
			throw new IOException("Cannot perform && operation on " + lhs + " and " + rhs);
	}

	protected boolean or(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Boolean && rhs instanceof Boolean)
			return (Boolean) lhs || (Boolean) rhs;
		else
			throw new IOException("Cannot perform || operation on " + lhs + " and " + rhs);
	}

	protected boolean equals(Object lhs, Object rhs) {
		return lhs.equals(rhs);
	}

	protected int compare(Object lhs, Object rhs) throws IOException {
		if (lhs instanceof Comparable && rhs instanceof Comparable)
			return ((Comparable) lhs).compareTo(rhs);
		else
			throw new IOException(lhs + " and " + rhs + " are not Comparable");
	}

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Token Operations ">

	/**
	 * Get the next token from the Token stack
	 * @return
	 * @throws java.io.IOException
	 */
	protected Token nextToken() throws IOException {
		// if there is already a token in the buffer (someone got it and pushed it back), use that token
		if (!tokBuffer.isEmpty())
			return (Token) tokBuffer.pop();

		// Create a new Token form the tokenizer data
		int type = st.nextToken();

		if (type == Token.NUMBER) {
			// The tokenizer always returns Doubles so we use NumberSupport to cast the Double to the least Number type
			Number nval = NumberSupport.toStrict(st.nval);

			// If the Tokenizer returns a negative number, manually seperate the '-' from the number
			// If we don't do this expressions like 6-2 will fail b/c the parser will parse it as 6, -2 instead of 6,-,2
			if (NumberSupport.compare(nval, new Integer(0)) >= 0) {
				return new Token(st.ttype, nval);
			}
			else {
				tokBuffer.push(new Token(st.ttype, multiply(nval, (byte) -1)));
				return new Token('-', null);
			}
		}
		else if (type == Token.WORD) {
			return new Token(st.ttype, st.sval);
		}
		else {
			return new Token(st.ttype, st.sval);
		}
		
	}
	/**
	 * Return a token to the Token stack so that it may be reused
	 * @param token
	 */
	protected void pushBack(Token token) {
		tokBuffer.push(token);
	}
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Variable and Static Function Manipulation">
	public void addStaticFunction(String functionName, Method m) {
		functions.put(functionName.toUpperCase(), m);
	}
	public void removeStaticFunction(String functionName) {
		functions.remove(functionName);
	}
	public void  removeAllStaticFunctions() {
		functions = new HashMap<String,Method>();
	}
	public void addVariable(String name, Object value) {
		variables.put(name, value);
	}
	public void removeVariable(String name) {
		variables.remove(name);
	}
	public void  removeAllVariable() {
		variables = new HashMap<String,Object>();
	}
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Static Functions ">

	public static Object functionIF(Boolean b, Object object1, Object object2) {
		if (b)
			return object1;
		else
			return object2;
	}
	public static Object functionNZ(Object obj, Object ifNull) {
		if (obj == null)
			return ifNull;
		else
			return obj;
	}
  // </editor-fold>
}
