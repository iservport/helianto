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
