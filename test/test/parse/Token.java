/*
 * Token.java
 *
 * Created on July 10, 2005, 4:52 PM
 */

package ext.java.io;

import java.io.StreamTokenizer;
/**
 *
 * @author Menachem & Shira
 */
public class Token {
    
    /** Creates a new instance of Token */
    public Token(int type, Object val) {
        this.type = type;
        this.val = val;
    }
    
    private int type;
    public int getType() {return type;}
    
    private Object val;
    public Object getVal() {return val;}
    
    public static final int EOF = StreamTokenizer.TT_EOF;
    public static final int EOL = StreamTokenizer.TT_EOL;
    public static final int NUMBER = StreamTokenizer.TT_NUMBER;
    public static final int WORD = StreamTokenizer.TT_WORD;
}
