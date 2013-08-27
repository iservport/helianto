package org.helianto.core.security;

import java.util.List;

import org.helianto.core.domain.IdentitySecurity;
import org.helianto.user.domain.UserGroup;

/**
 * A strategy to select users from a list.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserSelectorStrategy {
	
	/**
	 * Create UserDetailsAdapter from the first valid user in a list.
	 * 
	 * @param userList
	 * @param identitySecurity
	 * @param preferences
	 */
	public UserDetailsAdapter selectUser(List<? extends UserGroup> userList, IdentitySecurity identitySecurity, String preferences);

}
