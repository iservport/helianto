package org.helianto.core.security;

import java.util.List;

import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation for <code>UserSelectionStrategy</code> interface.
 * 
 * @author mauriciofernandesdecastro
 */
public class DefaultUserSelectionStrategy implements UserSelectorStrategy {

	/**
	 * Selects the first user on the list.
	 */
	public User selectUser(List<UserGroup> userList) throws IllegalArgumentException {
		if (userList!=null && userList.size()>0) {
			UserGroup user = userList.get(0);
			if (user instanceof User) {
				logger.info("Selected {}.", user);
				return (User) user;
			}
			throw new IllegalArgumentException("Not an instance of org.helianto.core.User.");
		}
		throw new IllegalArgumentException("Unable to extract valid user from a list.");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultUserSelectionStrategy.class);

}
