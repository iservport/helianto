/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.type;

/**
 * The operation type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum OperationType {
    
    /**
     * The operation is execution.
     */
    OPERATION(1),
    /**
     * The operation is inspection.
     */
    INSPECTION(2),
    /**
     * The operation is both execution and inspection.
     */
    OPERATON_AND_INSPECTION(3),
    /**
     * The operation is transport.
     */
    TRANSPORT(4),
    /**
     * The operation is validation.
     */
    VALIDATION(8);
    
    private int value;
    
    private OperationType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
