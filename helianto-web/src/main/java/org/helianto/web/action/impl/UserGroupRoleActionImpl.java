package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserRoleFormFilterAdapter;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.filter.form.UserGroupForm;
import org.helianto.core.filter.form.UserRoleForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		return userModelBuilder.getModelName();
	}

	@Override
	protected String getTargetName() {
		return "userRole";
	}
	
	protected CompositeUserForm getForm(MutableAttributeMap attributes) {
		return userModelBuilder.getForm(attributes);
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new UserRoleFormFilterAdapter(userModelBuilder.getForm(attributes));
	}
	
	/**
	 * Update form with a parent list where the first element is the owning userGroup (or user).
	 */
	@Override
	protected List<UserRole> doFilter(MutableAttributeMap attributes, Filter filter) {
		UserGroup parent = getOwner(attributes);
		if (parent!=null) {
			UserRoleForm form = getForm(attributes).clone(parent);
			logger.debug("Filter restricted to descendants of {}.", parent);
			return doFilter(form);
		}
		throw new IllegalArgumentException("Parent reuired.");
	}
	
	/**
	 * Subclasses must override this to retrieve an owner other than "userGroup".
	 * 
	 * @param attributes
	 */
	protected UserGroup getOwner(MutableAttributeMap attributes) {
		return (UserGroup) attributes.get("userGroup");
	}
	
	/**
	 * @deprecated
	 * @see #doFilter(UserGroupForm)
	 */
	@Override
	protected List<UserRole> doFilter(Filter filter) {
		return userMgr.findUserRoles(filter);
	}
	
	protected List<UserRole> doFilter(UserRoleForm form) {
		return (List<UserRole>) userMgr.findUserRoles(form);
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
	protected String doRemove(MutableAttributeMap attributes, UserRole target) {
		UserGroup userGroup = getOwner(attributes);
		userMgr.removeUserRole(target, userGroup);
		return "success";
	}
	
	// collabs
	
	protected UserMgr userMgr;
	protected UserModelBuilder userModelBuilder;
	
	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

	@Resource
	public void setUserModelBuilder(UserModelBuilder userModelBuilder) {
		this.userModelBuilder = userModelBuilder;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(UserGroupRoleActionImpl.class);
	
}
