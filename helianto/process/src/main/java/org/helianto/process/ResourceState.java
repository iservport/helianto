/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

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
    ACTIVE(0),
    /**
     * The resource is inactive.
     */
    INACTIVE(1);
    
    private int value;
    
    private ResourceState(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
