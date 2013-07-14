package org.helianto.core;

import org.helianto.core.domain.Phone;



/**
 * General business unit interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface BusinessAddress 
	extends Address {
	
	/**
	 * Alias to uniquely identify the business unit within a context.
	 */
	String getEntityAlias();
	
	/**
	 * Business unit name.
	 */
	String getEntityName();
	
	/**
	 * Business main phone.
	 */
	Phone getMainPhone();

	/**
	 * Business main email.
	 */
	String getMainEmail();

}
