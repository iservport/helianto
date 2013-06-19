package org.helianto.security.service;

import java.util.List;

import org.helianto.core.security.UserSelectorStrategy;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Default implementation for <code>UserSelectionStrategy</code> interface.
 * 
 * <p>
 * This implementation assumes preferences as the entityKey and returns the first matching user.
 * If entityKey is null, it simply returns the first user in the list.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userSelectionStrategy")
public class DefaultUserSelectionStrategy 
	implements UserSelectorStrategy {

	public User selectUser(List<? extends UserGroup> userList, String preferences) throws IllegalArgumentException {
		if (userList!=null && userList.size()>0) {
			if (preferences!=null) {
				for (UserGroup user: userList) {
					if (user.getEntity().getAlias().equalsIgnoreCase(preferences)) {
						logger.info("Selected {}.", user);
						return (User) user;
					}
				}
			}
			else {
				logger.info("Selected {}.", userList.get(0));
				return (User) userList.get(0);
			}
		}
		throw new IllegalArgumentException("Unable to extract valid user from a list.");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultUserSelectionStrategy.class);

}
