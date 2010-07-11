package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserAssociationFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userAssociationAction")
public class UserAssociationAction extends AbstractFilterAction<UserAssociation> {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected String getTargetName() { return "userAssociation"; }

	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = getParent(attributes);
	    return new UserAssociation(parent);
	}
	
	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes,	PublicUserDetails userDetails) {
		UserGroup parent = getParent(attributes);
		UserAssociationFilter userAssociationFilter = new UserAssociationFilter(userDetails.getUser());
		userAssociationFilter.setParent(parent);
		return userAssociationFilter;
	}
	
	@Override
	protected List<UserAssociation> doFilter(ListFilter filter) {
		List<UserAssociation> associations = userMgr.findUserAssociations((UserAssociationFilter) filter);
		return associations;
	}
	
	@Override
	protected UserAssociation doStore(UserAssociation target) {
		return userMgr.storeUserAssociation(target);
	}
	
	private UserGroup getParent(MutableAttributeMap attributes) {
		if (!attributes.contains("parent")) {
			throw new IllegalArgumentException("An user group named parent is required in scope.");
		}
		UserGroup parent = (UserGroup) attributes.get("parent");
		return parent;
	}
	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
