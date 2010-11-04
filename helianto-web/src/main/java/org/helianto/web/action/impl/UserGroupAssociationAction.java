package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserAssociationFilter;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userGroupAssociationAction")
public class UserGroupAssociationAction extends AbstractFilterAction<UserAssociation> {
	
	private static final long serialVersionUID = 1L;

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
	
	/**
	 * When the user association filter is created, a parent user filter must first be created.
	 */
	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes,	PublicUserDetails userDetails) {
		UserGroup parent = getParent(attributes);
		UserFilter parentFilter = new UserFilter(parent.getEntity());
		parentFilter.setClazz(UserGroup.class);
		UserAssociationFilter userAssociationFilter = new UserAssociationFilter(parentFilter);
		userAssociationFilter.setParent(parent);
		UserGroup child = getChild(attributes);
		userAssociationFilter.setChild(child);
		logger.debug("Created userAssociationFilter with parent='{}' and child='{}' and also parent UserFilter.", parent, child);
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
	
	protected UserGroup getParent(MutableAttributeMap attributes) {
		if (attributes.contains("parent")) {
			return (UserGroup) attributes.get("parent");
		}
		return null;
	}

	protected UserGroup getChild(MutableAttributeMap attributes) {
		if (attributes.contains("child")) {
			return (UserGroup) attributes.get("child");
		}
		return null;
	}
	
	// custom actions
	
	public String preFilter(MutableAttributeMap attributes,	PublicUserDetails userDetails) {
		UserAssociationFilter filter = (UserAssociationFilter) getFilter(attributes, userDetails);
		ListFilter parentFilter = filter.getParentFilter();
		List<UserGroup> groups = userMgr.findUsers((UserFilter) parentFilter);
		parentFilter.setList(groups);
		return "success";
	}

	// collabs
	
	protected UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(UserGroupAssociationAction.class);

}
