package org.helianto.message.service;

import java.util.HashSet;
import java.util.Set;

import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <code>RecipientResolutionStrategy</code> default implementation.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("recipientResolutionStrategy")
public class RecipientResolutionStrategyImpl implements RecipientResolutionStrategy {

	public String[] resolveRecipients(UserGroup notificationGroup) {
    	Set<String> recipientList = new HashSet<String>();
		for (UserAssociation userAssociation: notificationGroup.getChildAssociations()) {
			if (userAssociation.getChild() instanceof User) {
				User recipient = (User) userAssociation.getChild();
				if (recipient.getIdentity().getIdentityType()=='E' ||
						recipient.getIdentity().getIdentityType()=='P' ||
						recipient.getIdentity().getIdentityType()=='O') {
					logger.debug("Recipient: {}", recipient.getIdentity().getPrincipal());
					recipientList.add(recipient.getIdentity().getPrincipal());
				}
			}
		}
		return recipientList.toArray(new String[recipientList.size()]);
	}

	private static final Logger logger = LoggerFactory.getLogger(RecipientResolutionStrategyImpl.class);

}
