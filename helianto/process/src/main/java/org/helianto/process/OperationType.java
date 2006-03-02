/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process;

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
    EXECUTION(1),
    /**
     * The operation is inspection.
     */
    INSPECTION(2),
    /**
     * The operation is both execution and inspection.
     */
    MIXED(3);
    
    private int value;
    
    private OperationType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
