package org.helianto.core.filter.form;

import org.helianto.core.Identity;
import org.helianto.core.Resettable;
import org.helianto.core.TrunkEntity;
import org.helianto.core.UserGroup;

/**
 * User group form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserGroupForm 

	extends 
	  TrunkEntity
	, ParentListForm<UserGroup>
	, PersonalForm
	, ExclusionForm<Identity>
	, Resettable 

{
	
	/**
	 * User key.
	 */
	public String getUserKey();
	
	/**
	 * User state.
	 */
	public char getUserState();
	
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
