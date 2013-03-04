package org.helianto.user.form;

import org.helianto.core.domain.Identity;
import org.helianto.core.form.ExclusionForm;
import org.helianto.core.form.PersonalForm;

/**
 * User group form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserGroupForm 
	extends 
	  UserForm
	, PersonalForm
	, ExclusionForm<Identity> {
	
	/**
     * User group type.
     */
    char getUserGroupType();
	
	/**
     * User group type setter.
     * 
     * @param userGroupType
     */
    void setUserGroupType(char userGroupType);
	
	/**
     * User type.
     */
    char getUserType();
	
	/**
	 * Parent user key.
	 */
	String getParentUserKey();

}
