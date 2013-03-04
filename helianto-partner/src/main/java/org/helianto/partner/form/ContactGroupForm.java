package org.helianto.partner.form;

import org.helianto.partner.domain.PrivateEntity2;
import org.helianto.user.form.UserForm;

/**
 * Contact group form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContactGroupForm 
	extends UserForm {
	
	/**
	 * Private entity.
	 */
	PrivateEntity2 getParent();

}
