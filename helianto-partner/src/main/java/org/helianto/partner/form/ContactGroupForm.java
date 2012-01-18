package org.helianto.partner.form;

import org.helianto.core.filter.form.UserForm;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Contact group form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContactGroupForm 

	extends UserForm 
	
{
	/**
	 * Private entity.
	 */
	PrivateEntity getParent();

}
