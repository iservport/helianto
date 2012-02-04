package org.helianto.message.service;

import org.helianto.core.UserGroup;

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
