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
     * Association has no role.
     * 
     */
    NONE('N', false),
    /**
     * Association is the entry point.
     * 
     */
    ENTRY_POINT('E', true),
    /**
     * Association requires a control before start.
     * 
     */
    REQUIRE_CONTROL_BEFORE_START('B', true),
    /**
     * Association requires a control after end.
     * 
     */
    REQUIRE_CONTROL_AFTER_END('A', true);
    
    
    private char value;
    private boolean controlled;
    
	private AssociationRole(char value, boolean controlled) {
        this.value = value;
        this.controlled = controlled;
    }
    
    public char getValue() {
        return value;
    }

    public boolean isControlled() {
        return controlled;
    }
    
}
