package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userGroupAssociationAction")
public class UserGroupAssociationActionImpl extends AbstractAction<UserAssociation> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return "userModel";
	}

	@Override
	protected String getTargetName() { return "userAssociation"; }

	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = getParent(attributes);
		if (parent!=null) {
			UserGroup child = new UserGroup(parent.getEntity());
			logger.debug("Association has {} and {}.", parent, child);
		    return new UserAssociation(parent, child);
		}
		throw new IllegalArgumentException("An user group named parent is required in scope before association creation.");
	}
	
	@Override
	@Transactional(readOnly=false)
	protected UserAssociation doStore(UserAssociation target) {
		return userMgr.storeUserAssociation(target);
	}
	
	protected UserGroup getParent(MutableAttributeMap attributes) {
		if (attributes.contains("userGroup")) {
			return (UserGroup) attributes.get("userGroup");
		}
		return null;
	}

	protected UserGroup getChild(MutableAttributeMap attributes) {
		if (attributes.contains("child")) {
			return (UserGroup) attributes.get("child");
		}
		return null;
	}
	
	// collabs
	
	protected UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(UserGroupAssociationActionImpl.class);

}
