package org.helianto.user.form;

import org.helianto.core.Controllable;
import org.helianto.core.form.IdentityForm;
import org.helianto.user.domain.UserGroup;

/**
 * User request form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRequestForm extends Controllable, IdentityForm {
	
	/**
	 * User group.
	 */
	UserGroup getUserGroup();

	/**
	 * Temporary password.
	 */
	String getTempPassword();
}
