/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.type;

/**
 * The product type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public enum ActivityCode {
    
    /**
     * The product is for the local market.
     */
    DOMESTIC(1),
    /**
     * The product is for the export market.
     */
    EXPORT(2),
    /**
     * The product is for both markets.
     */
    MIXED(3);
    
    private int value;
    
    private ActivityCode(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
