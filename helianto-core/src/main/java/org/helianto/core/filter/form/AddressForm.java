package org.helianto.core.filter.form;

import org.helianto.core.AddressLocation;
import org.helianto.core.Resettable;


/**
 * Classes implementing this interface represent an address.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AddressForm extends AddressLocation, Resettable {

	/**
	 * Address type.
	 */
	public char getAddressType();

}
