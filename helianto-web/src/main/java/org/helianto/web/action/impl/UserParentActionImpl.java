package org.helianto.web.action.impl;

import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to user parent list.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userParentAction")
public class UserParentActionImpl extends UserGroupActionImpl {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<UserGroup> doFilter(MutableAttributeMap attributes, Filter filter) {
		User user = (User) attributes.get("user");
		if (user!=null) {
			logger.debug("Filtering ancestors of user {}.", user);
			return userMgr.findParentChain(user);
		}
		UserGroup userGroup = (UserGroup) attributes.get("userGroup");
		if (userGroup!=null) {
			logger.debug("Filtering ancestors of user group {}.", userGroup);
			return userMgr.findParentChain(userGroup);
		}
		throw new IllegalArgumentException("Unable to find either an user or an UserGroup to generate ancestor's list");
	}

	private final static Logger logger = LoggerFactory.getLogger(UserParentActionImpl.class);
}
