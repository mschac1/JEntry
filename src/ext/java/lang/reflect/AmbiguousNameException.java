/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ext.java.lang.reflect;

/**
 *
 * @author Menachem & Shira
 */
public class AmbiguousNameException extends Exception {

    /**
     * Creates a new instance of <code>AmbiguousNameException</code> without detail message.
     */
    public AmbiguousNameException() {
    }


    /**
     * Constructs an instance of <code>AmbiguousNameException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AmbiguousNameException(String msg) {
        super(msg);
    }
}
