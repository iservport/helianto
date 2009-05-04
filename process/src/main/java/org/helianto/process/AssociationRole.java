/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

/**
 * The association role enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum AssociationRole {
    
    /**
     * Association has no control requirement.
     * 
     */
    NONE('N', false),
    /**
     * Association requires a start time control.
     * 
     */
    CONTROL_START_TIME('S', true),
    /**
     * Association requires an end time control.
     * 
     */
    CONROL_END_TIME('E', true);
    
    
    private char value;
    private boolean controlled;
    
	private AssociationRole(char value, boolean controlled) {
        this.value = value;
        this.controlled = controlled;
    }
    
	/**
	 * Char value assigned to the enumeration.
	 */
    public char getValue() {
        return value;
    }

    /**
     * True if control is required.
     */
    public boolean isControlled() {
        return controlled;
    }
    
    /**
     * Retrieve an <tt>AssociationRole</tt> instance given the char value.
     */
    public static AssociationRole getAssociationRole(char value) {
    	for (AssociationRole associationRole: AssociationRole.values()) {
    		if (associationRole.getValue()==value) {
    			return associationRole;
    		}
    	}
    	throw new IllegalArgumentException(value+" is not a valid value for AssociationRole");
    }
    
    /**
     * True if control is required.
     */
    public static boolean isControlled(char value) {
    	AssociationRole associationRole = getAssociationRole(value);
        return associationRole.isControlled();
    }
    
}
