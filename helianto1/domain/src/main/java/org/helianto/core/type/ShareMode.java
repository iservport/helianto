package org.helianto.core.type;

/** 
 * An enumeration to supply char modes for 
 * {@link Partner#setShareMode(char)}.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum ShareMode {
    
    RESTRICTED('R'),
    FULL('F');
    
    private char value;
    
    private ShareMode(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
