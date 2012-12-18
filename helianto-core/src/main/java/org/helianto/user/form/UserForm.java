package org.helianto.user.form;

import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.form.SearchForm;

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
	
	/**
	 * User id array.
	 */
	public String[] getUserIdArray();
	
}
