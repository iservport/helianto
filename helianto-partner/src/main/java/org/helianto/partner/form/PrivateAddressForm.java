package org.helianto.partner.form;

import org.helianto.core.form.AddressForm;
import org.helianto.core.form.ParentForm;
import org.helianto.core.form.SequenceForm;
import org.helianto.partner.domain.PrivateEntity2;

/**
 * Classes implementing this interface represent a private address.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateAddressForm 

	extends 
	  ParentForm<PrivateEntity2>
	, AddressForm
	, SequenceForm

{

}
