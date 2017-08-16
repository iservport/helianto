package org.helianto.core;

import org.helianto.core.Address;
import org.helianto.core.domain.Phone;



/**
 * General entity address interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityAddress 
	extends Address {
	
	/**
	 * Alias to uniquely identify the entity within a context.
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
