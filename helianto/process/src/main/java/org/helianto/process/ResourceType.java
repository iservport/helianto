/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

/**
 * The resource type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum ResourceType {
    
    /**
     * The resource is a group.
     */
    GROUP(0),
    /**
     * The resource is not a key equipment.
     */
    NORMAL(1),
    /**
     * The resource is key, i.e. can potentially cause
     * interruption in the supply chain.
     */
    KEY(2),
    /**
     * The resource is inactive.
     */
    INACTIVE(-1);
    
    private int value;
    
    private ResourceType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
