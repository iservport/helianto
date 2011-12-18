package org.helianto.core.filter.form;

import org.helianto.core.Province;
import org.helianto.core.RootEntity;

/**
 * Public Address form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PublicAddressForm extends RootEntity {
	
	/**
	 * Province code filter.
	 */
	Province getProvince();
	
	/**
	 * Postal code filter.
	 */
	String getPostalCode();

	/**
	 * Province code filter.
	 */
	String getProvinceCode();
	
}