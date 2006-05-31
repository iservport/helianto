/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.creation;

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
    EQUIPMENT('E'),
    /**
     * The resource is a fixture.
     */
    FIXTURE('F'),
    /**
     * The resource is an instrument.
     */
    INSTRUMENT('I'),
    /**
     * The resource is a tool.
     */
    TOOL('T');
    
    private char value;
    
    private ResourceType(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
