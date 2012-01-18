package org.helianto.core.filter.form;

import org.helianto.core.TrunkEntity;

/**
 * User form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserForm 

	extends 
	  TrunkEntity
  
{

	/**
	 * User key.
	 */
	public String getUserKey();
	
	/**
	 * User state.
	 */
	public char getUserState();
			
}
