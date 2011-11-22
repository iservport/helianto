package org.helianto.core.filter.form;

import org.helianto.core.PersonalEntity;
import org.helianto.core.Resettable;

/**
 * Personal address form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PersonalAddressForm extends PersonalEntity, Resettable {
	
	/**
	 * Tipo de endereço.
	 */
	public char getAddressType();

}
