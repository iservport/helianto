package org.helianto.web.action.impl;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.security.PublicUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage the current user and other sibling users.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("homeAction")
public class HomeAction extends UserGroupAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserFilter userFilter = (UserFilter) UserFilter.userFilterFactory(userDetails.getUser());
		userFilter.setClazz(User.class);
		return userFilter;
	}
	
	/**
	 * Safely returns an entity from the current item in list, or null.
	 * 
	 * @param attributes
	 */
	public Entity getEntity(MutableAttributeMap attributes) {
		ListFilter filter = getFilter(attributes);
		if (filter.getItem()!=null && filter.getItem() instanceof UserGroup) {
			return ((UserGroup) filter.getItem()).getEntity();
		}
		return null;
	}

}
