package org.helianto.core;



/** 
 * Appellations.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum Appellation {
    
    /**
     * Not supplied.
     */
    NOT_SUPPLIED('0'),
    /**
     * Miss.
     */
    MISS('1'),
    /**
     * Mister or Mistress. Further distinction depends on 
     * the gender.
     */
    MR_OR_MRS('2'),
    /**
     * Miss or Mistress
     */
    MS('3');
    
    private char value;
    
    private Appellation(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }

}
