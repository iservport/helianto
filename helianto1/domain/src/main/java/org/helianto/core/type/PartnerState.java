package org.helianto.core.type;


/**
 * Enumeration to represent a state for 
 * a <code>Partner</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum PartnerState {
    
    ACTIVE('A'),
    CANCELLED('C'),
    IDLE('I'),
    SUSPENDED('S');
    
    private char value;
    
    private PartnerState(char value) {
        this.value = value;
    }
    public char getValue() {
        return this.value;
    }

}
