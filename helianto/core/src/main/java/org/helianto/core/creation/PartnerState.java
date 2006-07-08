package org.helianto.core.creation;


/**
 * Enumeration to represent a state for 
 * a <code>Partner</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: PartnerState.java,v 1.1 2006/03/02 22:44:27 iserv Exp $
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
