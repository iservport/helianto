/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.type;

/**
 * The resource state enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: ResourceType.java,v 1.1 2006/03/02 22:44:24 iserv Exp $
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
