package org.helianto.web.action.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.action.SimpleModel;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to select authorized users.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("authorizationAction")
public class AuthorizationActionImpl extends AbstractFilterAction<User> {
	
	private static final long serialVersionUID = 1L;

	protected User doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
//		UserGroup parent = userMgr.findUsers(userFilter)
//		User user = new User(parent, childCredential)
		return new User();
	}
	
	protected Filter doCreateFilter(MutableAttributeMap attributes,	PublicUserDetails userDetails) {
		return new UserFilterAdapter("USER");
	}
	
	protected List<User> doFilter(Filter filter) {
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) userMgr.findUsers(filter);
		return userList;
	}
	
	protected User doStore(User target) {
		return (User) userMgr.storeUserGroup(target);
	}
	
	public String authenticate(MutableAttributeMap attributes) {
		SimpleModel<?> model = getModel(attributes);
		User user = (User) model.getItem();
		if (user!=null) {
			Set<UserRole> roles = securityMgr.findRoles(user, true);
			securityMgr.authenticate(user, roles);
		}
		return "success";
	}
	

	// collabs
	
	protected UserMgr userMgr;
	private SecurityMgr securityMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	@Resource
	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}

}