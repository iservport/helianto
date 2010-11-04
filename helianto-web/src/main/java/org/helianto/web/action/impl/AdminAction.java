package org.helianto.web.action.impl;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserAssociationFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage the current user association and other sibling associations.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated use securityAction
 */
@Component("adminAction")
public class AdminAction extends UserGroupAssociationAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The admin action is used to create a new top level group.
	 */
	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = new UserGroup(userDetails.getEntity());
		logger.debug("Association has {} and {}.", parent, userDetails.getUser());
	    return new UserAssociation(parent, userDetails.getUser());
	}
	
	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserAssociationFilter userAssociationFilter = new UserAssociationFilter();
		userAssociationFilter.setChildIdentity(userDetails.getUser().getIdentity());
		userAssociationFilter.setParentKey("USER");
		logger.debug("Created userAssociationFilter with parentKey='USER' and childIdentity='{}'.", userAssociationFilter.getChildIdentity());
		return userAssociationFilter;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(AdminAction.class);

}
