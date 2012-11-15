package org.helianto.user.form;

import org.helianto.core.TrunkEntity;
import org.helianto.core.filter.form.SearchForm;

/**
 * User form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserForm 
	extends TrunkEntity
	, UserGroupParentIdForm
	, SearchForm {

	/**
	 * User key.
	 */
	public String getUserKey();
	
	/**
	 * User state.
	 */
	public char getUserState();
			
}
