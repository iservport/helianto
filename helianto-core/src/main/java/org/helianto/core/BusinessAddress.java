package org.helianto.core;



/**
 * General business unit interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface BusinessAddress extends Address {
	
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
