/*
 * Parser.java
 *
 * Created on July 8, 2005, 2:16 PM
 */

package test.parse;

import java.io.*;
import java.util.*;

/**
 *
 * @author Menachem & Shira
 */
public abstract class Parser {
    
    /** Creates a new instance of Parser */
    protected Parser() {
        parsing = false;
        vars = new HashMap();
        functions = new HashMap();
    }

    public Object parse (String s) {
        return parse(new StringReader(s));
    }
    public Object parse(Reader r) {
        st = new StreamTokenizer(r);
        st.parseNumbers();
        st.eolIsSignificant(true);
        st.ordinaryChar('/');
        st.lowerCaseMode(true);
        return parse();
    }
    
    protected Object parse(StreamTokenizer st) {
        this.st = st;
        return parse();
    }
    protected synchronized Object parse() {
        if (st == null)
            throw new RuntimeException("Tokenizer not initiated");
        
        parsing = true;
        tokBuffer = new Stack();
        Object val = statement();
        parsing = false;
        return val;
    }

    protected abstract Object statement();
    
    protected Object expr() {
        Object val = term();

        Token token = nextToken();
        
        while (token.getType() == '+' || token.getType() == '-')
        {
            if (token.getType() == '+')
            {
                val = add(val, term());
                token = nextToken();
            }
            else
            {
                val = subtract(val,term());
                token = nextToken();
            }
        }
        pushBack(token);        
        return val;
    }
    protected Object term() {
        Object val = factor();

        Token token = nextToken();
//        while (token.getType() == '*' || token.getType() == '/')
        while (token.getType() == '/' || token.getType() == '*' ||
               token.getType() == Token.WORD || token.getType() == '(' ||
               token.getType() == '[')
        {
            if (token.getType() == '/')
            {
                val = divide(val, factor());
                token = nextToken();
            }
            else if (token.getType() == '*')
            {
                val = multiply(val, factor());
                token = nextToken();
            }
            else {
                pushBack(token);
                val = multiply(val, factor());
                token = nextToken();
            }
        }

        pushBack(token);
        return val;
    }

    protected Object factor() {
        Object val = factor2();
        Token token = nextToken();
        while (token.getType() == '^') {
            val = power(val, factor2());
            token = nextToken();
        }
        pushBack(token);
        return val;
        
    }
    protected Object factor2() {
        Token token = nextToken();
        
        // Parenthesis
        if (token.getType() == '(') {
            Object val = expr();
            token = nextToken();
            if (token.getType() != ')') throw new SyntaxError("Missing right parenthesis");
            return val;
        }
        else if (token.getType() == '[') {
            Object val = expr();
            token = nextToken();
            if (token.getType() != ']') throw new SyntaxError("Missing right bracket");
            return val;
        }
        
        //Absolute value
        else if (token.getType() == '|') {
            Object val = abs(expr());
            token = nextToken();
            if (token.getType() != '|') throw new SyntaxError("Missing right bar");
            return val;
        }
        
        // Fuctions and variables
        else if (token.getType() == Token.WORD) {
            pushBack(token);
            return nonNumeric();
        }
        
        // Numbers
        else if (token.getType() == Token.NUMBER) {
            pushBack(token);
            return numeric();
        }
        
        else if (token.getType() == '-') {
            return negate(factor());
        }

        return Double.NaN;
    }
    protected abstract Object add(Object lhs, Object rhs);
    protected abstract Object subtract(Object lhs, Object rhs);
    protected abstract Object multiply(Object lhs, Object rhs);
    protected abstract Object divide(Object lhs, Object rhs);
    protected abstract Object power(Object lhs, Object rhs);
    protected abstract Object abs(Object val);
    protected abstract Object negate(Object val);
    
    protected abstract Object numeric();
    protected abstract Object nonNumeric();

    protected Token nextToken() {
        if (tokBuffer.isEmpty()) {
            int type;
            try {
                type = st.nextToken();
            }
            catch (java.io.IOException e) {
                throw new SyntaxError(e.getMessage());
            }
            if (type == Token.NUMBER)
                return new Token(st.ttype, st.nval);
            else
                return new Token(st.ttype, st.sval);
            
        }
        else
        {
            return (Token) tokBuffer.pop();
        }
    }
    protected void pushBack(Token token) {
        tokBuffer.push(token);
    }
    
    public boolean isFuncName(String name) {
        return functions.containsKey(name);
    }
    
    protected StreamTokenizer st;
    protected Stack tokBuffer;
    protected boolean parsing;
    protected HashMap functions;
    
    protected HashMap vars;
    public void setVars(HashMap _vars) {vars = _vars;}
    public HashMap getVars() {return vars;}
    
}
