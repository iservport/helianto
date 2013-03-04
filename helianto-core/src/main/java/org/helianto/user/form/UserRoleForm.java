package org.helianto.user.form;


/**
 * User role form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserRoleForm 
	extends UserGroupParentIdForm {
	
	/**
	 * User group parent id array.
	 */
	int[] getUserGroupParentIdArray();

	/**
	 * Service.
	 */
	int getServiceId();
	
	/**
	 * Service extension.
	 */
	String getServiceExtension();
	
	/**
	 * Activity state.
	 */
	char getActivityState();
}
