/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.process.creation;

/**
 * The product type enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: ProductType.java,v 1.1 2006/03/02 22:44:24 iserv Exp $
 */
public enum ProductType {
    
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
    
    private ProductType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
