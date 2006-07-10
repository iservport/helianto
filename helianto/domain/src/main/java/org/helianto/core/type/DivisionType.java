package org.helianto.core.type;


/**
 * Enumeration to represent types for 
 * a <code>Division</code>. 
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: DivisionType.java,v 1.1 2006/03/02 22:44:27 iserv Exp $
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
