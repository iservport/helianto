package org.helianto.core.filter.form;

import org.helianto.core.Resettable;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;

/**
 * User role form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRoleForm extends ParentListForm<UserGroup>, Resettable {

	/**
	 * User group.
	 */
	public UserGroup getUserGroup();
	
	/**
	 * Service.
	 */
	public Service getService();
	
	/**
	 * Service extension.
	 */
	public String getServiceExtension();
	
	/**
	 * Activity state.
	 */
	public char getActivityState();
}
