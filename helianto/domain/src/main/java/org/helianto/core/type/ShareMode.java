package org.helianto.core.type;

/** 
 * An enumeration to supply char modes for 
 * {@link Partner#setShareMode(char)}.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: Appellation.java,v 1.2 2006/03/17 23:36:38 iserv Exp $
 */
public enum ShareMode {
    
    RESTRICTED('R'),
    FULL('F');
    
    private char value;
    
    private ShareMode(char value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
