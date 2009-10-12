/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.resource;

/**
 * The resource type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum ResourceType {
    
    /**
     * The resource is a sector.
     */
    SECTOR('S'),
    /**
     * The resource is a workstation.
     */
    WORKSTATION('W'),
    /**
     * The resource is a regular equipment.
     */
    EQUIPMENT('E'),
    /**
     * The resource is a vehicle.
     */
    VEHICLE('V'),
    /**
     * The resource is a fixture.
     */
    FIXTURE('F'),
    /**
     * The resource is a tool.
     */
    TOOL('T'),
    /**
     * The resource type is irrelevant.
     */
    ANY('A');
    
    private char value;
    
    private ResourceType(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
