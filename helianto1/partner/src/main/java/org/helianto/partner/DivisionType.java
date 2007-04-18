package org.helianto.partner;


/**
 * Enumeration to represent types for 
 * a <code>Division</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum DivisionType {
    
    HEADQUARTER('H');
    
    private char value;
    
    private DivisionType(char value) {
        this.value = value;
    }
    public char getValue() {
        return this.value;
    }

}
