package org.helianto.core.type;


import org.helianto.core.PersonalData;

/** 
 * An enumeration to supply int types for 
 * {@link PersonalData#appellation}.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: Appellation.java,v 1.2 2006/03/17 23:36:38 iserv Exp $
 */
public enum Appellation {
    
    /**
     * Not supplied.
     */
    NOT_SUPPLIED(0),
    /**
     * Miss.
     */
    MISS(1),
    /**
     * Mister or Mistress. Further distinction depends on 
     * the gender.
     */
    MR_OR_MRS(2),
    /**
     * Miss or Mistress
     */
    MS(3);
    
    private int value;
    
    private Appellation(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
