package org.helianto.web.action.impl;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userAssociationAction")
public class UserAssociationActionImpl extends UserGroupAssociationActionImpl {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = getParent(attributes);
		if (parent!=null) {
			UserGroup child = new User(parent, new Credential(new Identity(""), "123456"));
			logger.debug("Association has {} and {}.", parent, child);
		    return new UserAssociation(parent, child);
		}
		throw new IllegalArgumentException("An user group named parent is required in scope before association creation.");
	}
	
	
	private final static Logger logger = LoggerFactory.getLogger(UserAssociationActionImpl.class);

}
