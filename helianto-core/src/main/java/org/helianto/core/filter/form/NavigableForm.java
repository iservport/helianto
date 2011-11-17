package org.helianto.core.filter.form;

import org.helianto.core.Navigable;


/**
 * Class form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NavigableForm extends Navigable {
	
    /**
     * True if filter does not include descendants.
     */
	public boolean isStrict();
	
	/**
	 * Strict is a R/W property.
	 * 
	 * @param strict
	 */
	public void setStrict(boolean strict);
	

}
