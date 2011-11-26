package org.helianto.core.filter.form;

import org.helianto.core.Controllable;
import org.helianto.core.UserGroup;

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
