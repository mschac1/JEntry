/*
 * ArithParser.java
 *
 * Created on July 8, 2005, 2:17 PM
 */

package test.parse;

import java.io.*;
import java.util.*;

/**
 *
 * @author Menachem & Shira
 */
public class ArithParser extends Parser{
    
//    public ArithParser(String s) {super(s);}
//    public ArithParser(Reader r) {super(r);}
    public ArithParser() {
        super();
        constants = new HashMap();
        constants.put("pi", Math.PI);
        constants.put("e", Math.E);
        constants.put("\u03c0", Math.PI);
        addFunctions();
    }
    
    protected Object statement() {
        Token token = nextToken();
        if (token.getType() == Token.WORD && !isFuncName((String) token.getVal())) {
            Token token2 = nextToken(); 
            if (token2.getType() == '=') {
                if (constants.containsKey(token.getVal()) || token.getVal().equals("ans"))
                    throw new SyntaxError("Cannot assign to " + token.getVal());
                Object val = statement();
                vars.put(token.getVal(), val);
                return val;
            }
            pushBack(token2);
            pushBack(token);
        }
        else
            pushBack(token);
            
        Object val =  expr();
        vars.put("ans", val);
        return val;
    }
    protected Object add(Object rhs, Object lhs) {
        return Double.valueOf(((Double) rhs).doubleValue() + ((Double) lhs).doubleValue());
    }
    protected Object subtract(Object rhs, Object lhs) {
        return Double.valueOf(((Double) rhs).doubleValue() - ((Double) lhs).doubleValue());
    }
    protected Object multiply(Object rhs, Object lhs) {
        return Double.valueOf(((Double) rhs).doubleValue() * ((Double) lhs).doubleValue());
    }
    protected Object divide(Object rhs, Object lhs) {
        return Double.valueOf(((Double) rhs).doubleValue() / ((Double) lhs).doubleValue());
    }
    protected Object power(Object rhs, Object lhs) {
        return Double.valueOf(Math.pow(((Double) rhs).doubleValue(),((Double) lhs).doubleValue()));
    }
    protected Object abs(Object val) {
        return Double.valueOf(Math.abs(((Double) val).doubleValue()));
    }
    protected Object negate(Object val) {
        return Double.valueOf( -((Double) val).doubleValue());
    }
    
    protected Object numeric() {
        Token token = nextToken();
        return token.getVal();
    }
    protected Object nonNumeric() {
        Token token = nextToken();
        if (constants.containsKey(token.getVal())) {
            return constants.get(token.getVal());
        }
        if (vars.containsKey(token.getVal())) {
            return vars.get(token.getVal());
        }
        else if (isFuncName((String) token.getVal())) {
            Object x = factor();
            return ((Function) functions.get(token.getVal())).f(x);
        }
        else {
            throw new SyntaxError(token.getVal() + " is not a variable or a function name");
        }
        
    }
    
    protected void addFunctions() {
        functions.put("sin", new Function() {
            public Object f(Object x) {
                return Math.sin(((Double) x).doubleValue());
            }
        });
        functions.put("cos", new Function() {
            public Object f(Object x) {
                return Math.cos(((Double) x).doubleValue());
            }
        });
        functions.put("tan", new Function() {
            public Object f(Object x) {
                return Math.tan(((Double) x).doubleValue());
            }
        });
    }
    
    protected HashMap constants;
}
