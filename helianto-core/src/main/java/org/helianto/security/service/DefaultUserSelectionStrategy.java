package org.helianto.security.service;

import java.util.List;

import org.helianto.core.domain.IdentitySecurity;
import org.helianto.core.security.UserDetailsAdapter;
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

	public UserDetailsAdapter selectUser(List<? extends UserGroup> userList, IdentitySecurity identitySecurity, String preferences) 
			throws IllegalArgumentException {
		UserDetailsAdapter userDetails = null;
		if (userList!=null && userList.size()>0) {
			for (UserGroup user: userList) {
				userDetails = new UserDetailsAdapter((User) user, identitySecurity);
				if (userDetails.isAccountNonExpired() 
						&& userDetails.isAccountNonLocked()
						&& userDetails.isCredentialsNonExpired()
						&& userDetails.isEnabled()) {
					// allow preferences to determine the user
					if (preferences!=null) {
						if (user.getEntity().getAlias().equalsIgnoreCase(preferences)) {
							logger.info("Selected {}.", user);
							return userDetails;
						}
					}
					else {
						logger.info("Selected {}.", user);
						return userDetails;
					}
				}
			}
		}
		throw new IllegalArgumentException("Unable to extract valid user from a list.");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultUserSelectionStrategy.class);

}
