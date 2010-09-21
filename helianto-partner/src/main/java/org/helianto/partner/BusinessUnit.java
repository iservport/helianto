package org.helianto.partner;

/**
 * General business unit interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface BusinessUnit extends Addressee {
	
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
	public AbstractPhone getMainPhone();

	/**
	 * Business main email.
	 */
	public String getMainEmail();

}
