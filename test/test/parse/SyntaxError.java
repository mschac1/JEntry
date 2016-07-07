/*
 * SynaxError.java
 *
 * Created on July 10, 2005, 12:38 PM
 */

package parse;

/**
 *
 * @author Menachem & Shira
 */
public class SyntaxError extends java.lang.RuntimeException {
    
    /**
     * Creates a new instance of <code>SynaxError</code> without detail message.
     */
    public SyntaxError() {
    }
    
    
    /**
     * Constructs an instance of <code>SynaxError</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SyntaxError(String msg) {
        super(msg);
    }
}
