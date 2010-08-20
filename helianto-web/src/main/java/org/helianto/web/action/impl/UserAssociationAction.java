package org.helianto.web.action.impl;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage user associations.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userAssociationAction")
public class UserAssociationAction extends UserGroupAssociationAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = getParent(attributes);
		if (parent!=null) {
			User child = new User(parent.getEntity(), new Credential("", "default"));
			logger.debug("Association has {} and {}.", parent, child);
		    return new UserAssociation(parent, child);
		}
		throw new IllegalArgumentException("An user group named parent is required in scope before association creation.");
	}
	
	
	private final static Logger logger = LoggerFactory.getLogger(UserAssociationAction.class);

}
