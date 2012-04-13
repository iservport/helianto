package org.helianto.web.action.impl;

import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage users.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public class UserActionImpl extends UserGroupActionImpl {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<UserGroup> doFilter(MutableAttributeMap attributes, Filter filter) {
		UserGroup parent = (UserGroup) attributes.get("userGroup", UserGroup.class);
		CompositeUserForm form = getForm(attributes);
		form.setUserGroupType('U');
		if (parent!=null) {
			form = form.clone(parent);
			logger.debug("Filter restricted to descendants of {}.", parent);
		}
		return doFilter(form);
	}
	
	protected String emptyList(MutableAttributeMap attributes, List<UserGroup> itemList) {
		return "empty";
	}
	
	@Override
	protected User doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Identity identity = (Identity) attributes.get("identity");
		if (identity!=null) {
			User user = new User(userDetails.getEntity(), identity);
			return user;
		}
		throw new IllegalArgumentException("An identity is required to create an user.");
	}

	private final static Logger logger = LoggerFactory.getLogger(UserActionImpl.class);
	
}
