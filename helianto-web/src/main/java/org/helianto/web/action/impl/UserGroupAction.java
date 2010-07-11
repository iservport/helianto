package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
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
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return UserFilter.userFilterFactory(userDetails.getUser().getIdentity());
	}

	@Override
	protected List<UserGroup> doFilter(ListFilter filter) {
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

}
