/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.def;

/**
 * The specification type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum SpecificationType {
    
    ATTRIBUTE(0), 
    VARIABLE_BILATERAL(1),
    VARIABLE_UNILATERAL_MAX(2),
    VARIABLE_UNILATERAL_MIN(3);
    
    private int value;
    
    private SpecificationType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
