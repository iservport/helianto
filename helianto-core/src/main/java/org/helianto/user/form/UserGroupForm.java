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
    public char getUserGroupType();
	
	/**
     * User group type setter.
     * 
     * @param userGroupType
     */
    public void setUserGroupType(char userGroupType);
	
	/**
     * User type.
     */
    public char getUserType();
	
	/**
	 * Parent user key.
	 */
	public String getParentUserKey();

}
