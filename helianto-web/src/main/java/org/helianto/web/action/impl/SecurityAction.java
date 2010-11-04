package org.helianto.web.action.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserAssociationFilter;
import org.helianto.core.UserRole;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.service.SecurityMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic used exclusively to switch users sharing the same identity.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("securityAction")
public class SecurityAction extends UserGroupAssociationAction {

	private static final long serialVersionUID = 1L;

	/**
	 * The admin action is used to create a new top level group.
	 */
	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		throw new IllegalArgumentException("Forbidden: do not use the security action to create a new top level user.");
	}
	
	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserAssociationFilter userAssociationFilter = new UserAssociationFilter();
		userAssociationFilter.setChildIdentity(userDetails.getUser().getIdentity());
		userAssociationFilter.setParentKey("USER");
		logger.debug("Created userAssociationFilter with parentKey='USER' and childIdentity='{}'.", userAssociationFilter.getChildIdentity());
		userAssociationFilter.setOrderByString("child.entity.alias");
		return userAssociationFilter;
	}
	
	public String filter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
//		UserAssociationFilter filter = (UserAssociationFilter) getFilter(attributes, userDetails);
//		ListFilter parentFilter = filter.getParentFilter();
//		if (parentFilter!=null) {
//			List<UserGroup> groups = userMgr.findUsers((UserFilter) parentFilter);
//			parentFilter.setList(groups);
//		}
		return super.filter(attributes, userDetails);
	}

	public String authenticate(MutableAttributeMap attributes) {
		UserAssociation ua = getTarget(attributes);
		if (ua==null) {
			throw new IllegalArgumentException("Unable to authenticate user: select a top level user association first.");
		}
		User user = (User) ua.getChild();
		Set<UserRole> roles = securityMgr.findRoles(user, true);
		UserDetails userDetails = new UserDetailsAdapter(user, null, roles);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "any", userDetails.getAuthorities());   //   PreAuthenticatedAuthenticationToken(aPrincipal, aCredentials, anAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "success";
	}
	
	// collabs
	
	private SecurityMgr securityMgr;
	
	@Resource
	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(SecurityAction.class);

}
