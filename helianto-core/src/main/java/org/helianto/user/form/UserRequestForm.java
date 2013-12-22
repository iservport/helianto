package org.helianto.user.form;

import org.helianto.core.filter.DateForm;
import org.helianto.core.form.EventControlInternalForm;
import org.helianto.core.form.IdentityForm;
import org.helianto.user.domain.UserGroup;

/**
 * User request form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRequestForm 
	extends EventControlInternalForm
	, IdentityForm
	, DateForm 
{
	
	/**
	 * User group.
	 */
	UserGroup getUserGroup();

	/**
	 * Temporary password.
	 */
	String getTempPassword();
}
