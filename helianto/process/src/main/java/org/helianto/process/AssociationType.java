/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

/**
 * The association type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum AssociationType {
    
    /**
     * The association is part to part.
     */
    PART_TO_PART(1),
    /**
     * The association is process to operation.
     */
    PROCESS_TO_OPERATION(2),
    /**
     * The association is required between product and process.
     */
    PRIMARY_PRODUCT_TO_PROCESS(3),
    /**
     * The association is optional between product and process.
     */
    ALTERNATE_PRODUCT_TO_PROCESS(4);
    
    private int value;
    
    private AssociationType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
