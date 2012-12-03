package org.helianto.message;

import org.helianto.user.domain.UserGroup;

/**
 * A strategy to resolve recipients.
 * 
 * @author mauriciofernandesdecastro
 */
public interface RecipientResolutionStrategy {
	
	/**
	 * Resolve recipients from a group.
	 * 
	 * @param notificationGroup
]	 */
	String[] resolveRecipients(UserGroup notificationGroup);

}
