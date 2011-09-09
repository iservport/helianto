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
public interface UserGroupForm extends TrunkEntity, DiscriminatorForm<UserGroup>, ParentForm<UserGroup>, ExclusionForm<Identity>, Resettable {
	
	/**
	 * User key.
	 */
	public String getUserKey();
	
	/**
	 * User state.
	 */
	public char getUserState();
	
	/**
     * User type.
     */
    public char getUserType();
	
	/**
	 * Parent user key.
	 */
	public String getParentUserKey();
	
	/**
	 * Identity.
	 */
	public Identity getIdentity();

}
