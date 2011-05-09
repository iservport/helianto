package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.action.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage users and groups.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userGroupAction")
public class UserGroupActionImpl extends AbstractFilterAction<UserGroup> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Force the model name for this bean (and subclasses) as "user".
	 * 
	 * <p>
	 * This is usually defined by the default naming convention.
	 * </p>
	 */
	@Override
	protected String getModelName() {
		return "userModel";
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		PageModel<User> model = getModel(attributes);
		UserFilterAdapter filter = new UserFilterAdapter(model.getFilter());
		logger.debug("Created userGroupFilter {}.",filter);
		return filter;
	}
	
	@Override
	protected List<UserGroup> doFilter(MutableAttributeMap attributes, Filter filter) {
		((UserFilterAdapter) filter).setClazz(UserGroup.class);
		((UserFilterAdapter) filter).setParent(null);
		logger.debug("Filter restricted to UserGroup.class");
		return doFilter(filter);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<UserGroup> doFilter(Filter filter) {
		return (List<UserGroup>) userMgr.findUsers(filter);
	}

	@Override
	protected UserGroup doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new UserGroup(userDetails.getEntity(), "");
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

	private final static Logger logger = LoggerFactory.getLogger(UserGroupActionImpl.class);
}
