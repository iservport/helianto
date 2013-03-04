package org.helianto.core.domain.type;

import org.helianto.core.domain.Phone;



/**
 * General business unit interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface BusinessEntity extends AddressEntity {
	
	/**
	 * Alias to uniquely identify the business unit within a context.
	 */
	public String getEntityAlias();
	
	/**
	 * Business unit name.
	 */
	public String getEntityName();
	
	/**
	 * Business main phone.
	 */
	public Phone getMainPhone();

	/**
	 * Business main email.
	 */
	public String getMainEmail();

}
