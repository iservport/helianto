package org.helianto.web.action.impl;

import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.UserGroupForm;
import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage users.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userAction")
public class UserActionImpl extends UserGroupActionImpl {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<UserGroup> doFilter(MutableAttributeMap attributes, Filter filter) {
		UserGroupForm form = userModelBuilder.getForm(attributes);
		form.setClazz(User.class);
		UserGroup parent = (UserGroup) attributes.get("userGroup", UserGroup.class);
		if (parent!=null) {
			form.setParent(parent);
		}
		logger.debug("Filter restricted to descendants of {}.", parent);
		return doFilter(filter);
	}
	
	@Override
	protected User doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		throw new IllegalArgumentException("Please, run userAssociationAction#create(...) instead.");
	}

	private final static Logger logger = LoggerFactory.getLogger(UserActionImpl.class);
}
