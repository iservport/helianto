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
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userAssociationAction")
public class UserAssociationActionImpl extends UserGroupAssociationActionImpl {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Requires a parent and an identity to create an user assocition.
	 * 
	 * @param attributes
	 * @param userDetails
	 */
	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = (UserGroup) attributes.get("userGroup");
		if (parent==null) {
			throw new IllegalArgumentException("An user group is required in scope before association creation.");			
		}
		Credential credential = (Credential) attributes.get("credential");
		if (credential==null) {
			throw new IllegalArgumentException("A credential is required in scope before association creation.");			
		}
		UserGroup child = new User(parent, credential);
		child.setAccountNonExpired(true);
		logger.debug("Association has {} and {}.", parent, child);
	    return new UserAssociation(parent, child);
	}
	
	
	private final static Logger logger = LoggerFactory.getLogger(UserAssociationActionImpl.class);

}
