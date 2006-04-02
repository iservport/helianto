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
     * The resource is a regular equipment.
     */
    EQUIPMENT(0),
    /**
     * The resource is a fixture.
     */
    FIXTURE(1),
    /**
     * The resource is an instrument.
     */
    INSTRUMENT(2),
    /**
     * The resource is key, i.e. can potentially cause
     * interruption in the supply chain.
     */
    KEY_EQUIPMENT(3),
    /**
     * The resource is a tool.
     */
    TOOL(4);
    
    private int value;
    
    private ResourceType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
