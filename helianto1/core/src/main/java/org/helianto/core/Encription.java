package org.helianto.core;



/** 
 * Encription types.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum Encription {
    
    /**
     * Plain password.
     */
    PLAIN_PASSWORD((byte) 0);
    
    private byte value;
    
    private Encription(byte value) {
        this.value = value;
    }
    
    public byte getValue() {
        return value;
    }

}
