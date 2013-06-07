package org.helianto.core.form;

import org.helianto.core.domain.type.LocationEntity;


/**
 * Classes implementing this interface represent an address.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AddressForm extends LocationEntity {

	/**
	 * Address type.
	 */
	public char getAddressType();

}