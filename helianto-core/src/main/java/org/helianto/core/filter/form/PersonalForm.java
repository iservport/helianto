package org.helianto.core.filter.form;

import org.helianto.core.Identity;

/**
 * Personal form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PersonalForm {
	
	/**
	 * Identity.
	 */
	public Identity getIdentity();
	
	/**
	 * Identity as a R/W property.
	 * 
	 * @param identity
	 */
	public void setIdentity(Identity identity);

}
