package org.helianto.core.security;

import java.util.List;

import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;

/**
 * A strategy to select users from a list.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserSelectorStrategy {
	
	/**
	 * Select an user from a list.
	 * 
	 * @param userList
	 * @param preferences
	 */
	public User selectUser(List<? extends UserGroup> userList, String preferences);

}
