package org.helianto.inventory.form;

import java.io.Serializable;


/**
 * Card form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CardForm extends Serializable {

    /**
     * Card set id.
     */
	int getCardSetId();

    /**
     * Card label.
     */
    String getCardLabel();
    
	/**
	 * Card state
	 */
	char getCardState();
	
}
