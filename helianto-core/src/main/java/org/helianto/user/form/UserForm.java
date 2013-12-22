package org.helianto.user.form;

import org.helianto.core.form.EntityIdForm;
import org.helianto.core.form.SearchForm;

/**
 * User form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserForm 
	extends EntityIdForm
	, UserGroupParentIdForm
	, SearchForm 
{

	/**
	 * User key.
	 */
	String getUserKey();
	
	/**
	 * User state.
	 */
	char getUserState();
	
	/**
	 * User entity activity state.
	 */
	char getEntityActivityState();
	
	/**
	 * User id array.
	 */
	int[] getUserIdArray();
	
	/**
	 * User order filter.
	 */
	char getUserOrderBy();
	
}
