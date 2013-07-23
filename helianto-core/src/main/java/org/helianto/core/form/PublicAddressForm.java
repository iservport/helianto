package org.helianto.core.form;

import org.helianto.core.domain.type.RootEntity;

/**
 * Public Address form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicAddressForm 
	extends RootEntity
	, StateForm
	, CityForm 
{
	
	/**
	 * Postal code filter.
	 */
	String getPostalCode();

	/**
	 * Province code filter.
	 */
	String getProvinceCode();
	
}
