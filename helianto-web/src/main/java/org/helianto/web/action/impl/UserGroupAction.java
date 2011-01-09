package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage users and groups.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userGroupAction")
public class UserGroupAction extends AbstractFilterAction<UserGroup> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String getTargetName() { return  "userGroup"; }

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserFilter userFilter = new UserFilter(userDetails.getUser());
		userFilter.setClazz(UserGroup.class);
		logger.debug("Created userGroupFilter for entity='{}' and class='UserGroup'.", userFilter.getEntity());
		return userFilter;
	}

	@Override
	protected List<UserGroup> doFilter(Filter filter) {
		return userMgr.findUsers((UserFilter) filter);
	}

	@Override
	protected UserGroup doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new UserGroup(userDetails.getEntity());
	}

	@Override
	protected UserGroup doStore(UserGroup target) {
		return userMgr.storeUserGroup(target);
	}
	
	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

	private final static Logger logger = LoggerFactory.getLogger(UserGroupAction.class);
}
