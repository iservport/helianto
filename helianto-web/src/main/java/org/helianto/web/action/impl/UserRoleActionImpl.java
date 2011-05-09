package org.helianto.web.action.impl;

import org.helianto.core.UserGroup;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to user roles.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userRoleAction")
public class UserRoleActionImpl extends UserGroupRoleActionImpl {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Overridden to set the owner as "user".
	 * 
	 * @param attributes
	 */
	protected UserGroup getOwner(MutableAttributeMap attributes) {
		return (UserGroup) attributes.get("user");
	}
	
}
