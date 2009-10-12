/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 */

package org.helianto.resource;

/**
 * The resource classification enumeration.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum ResourceClassification {
    
    /**
     * Key resources indicate specific requirements.
     */
    KEY('K'),
    /**
     * The resource classification is irrelevant.
     */
    ANY('A');
    
    private char value;
    
    private ResourceClassification(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
