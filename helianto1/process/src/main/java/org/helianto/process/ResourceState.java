/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

/**
 * The resource state enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum ResourceState {
    
    /**
     * The resource is active.
     */
    ACTIVE('A'),
    /**
     * The resource is inactive.
     */
    INACTIVE('I');
    
    private char value;
    
    private ResourceState(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
