package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserRoleFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to user roles.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userGroupRoleAction")
public class UserGroupRoleActionImpl extends AbstractFilterAction<UserRole> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return "userModel";
	}
	
	@Override
	protected String getTargetName() {
		return "userRole";
	}
	
	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new UserRoleFilterAdapter(new UserRole());
	}
	
	@Override
	protected List<UserRole> doFilter(MutableAttributeMap attributes, Filter filter) {
		UserGroup userGroup = getOwner(attributes);
		List<UserGroup> parentList = userMgr.findParentChain(userGroup);
		attributes.put("parentList", parentList);
		((UserRoleFilterAdapter) filter).setParentList(parentList);
		return doFilter(filter);
	}
	
	/**
	 * Subclasses must override this to retrieve an owner other than "userGroup".
	 * 
	 * @param attributes
	 */
	protected UserGroup getOwner(MutableAttributeMap attributes) {
		return (UserGroup) attributes.get("userGroup");
	}
	
	@Override
	protected List<UserRole> doFilter(Filter filter) {
		return userMgr.findUserRoles(filter);
	}
	
	@Override
	protected UserRole doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup userGroup = getOwner(attributes);
		Service service = (Service) attributes.get("service");
		return new UserRole(userGroup, service, "");
	}
	
	@Override
	protected UserRole doStore(UserRole target) {
		return userMgr.storeUserRole(target);
	}
	
	@Override
	protected String doRemove(UserRole target) {
		userMgr.removeUserRole(target);
		return "success";
	}
	
	// collabs
	
	private UserMgr userMgr;
	
	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
