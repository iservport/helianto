/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.type;

/**
 * Defines how a <code>Part</code> is distinguished..
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum PartType {
    
    /**
     * Default type.
     */
    PART(0),
    /**
     * Created to name a <code>Product</code>.
     */
    AUTO(-1),
    /**
     * Material, like steel, plastic, etc.
     */
    MATERIAL(1),
    /**
     * Component.
     */
    COMPONENT(2),
    /**
     * Group.
     */
    GROUP(3),
    /**
     * Assembly group, can be disassembled.
     */
    ASSEMBLY_GROUP(4),
    /**
     * Aggregated group, welded glued, etc.
     */
    AGGREGATE_GROUP(5);
    
    private int value;
    
    private PartType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public boolean isGroup() {
        return value >= GROUP.value ? true : false;
    }

    public boolean isMaterial() {
        return value == MATERIAL.value ? true : false;
    }

}
