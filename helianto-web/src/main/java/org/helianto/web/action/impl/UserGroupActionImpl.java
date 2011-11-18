package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserGroup;
import org.helianto.core.def.UserState;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserFormFilterAdapter;
import org.helianto.core.filter.form.AbstractUserForm;
import org.helianto.core.filter.form.UserGroupForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
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
	
	@Override
	protected String getModelName() {
		return userModelBuilder.getModelName();
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroupForm form = userModelBuilder.getForm(attributes);
		UserFormFilterAdapter filter = new UserFormFilterAdapter(form);
		form.setClazz(UserGroup.class);
		form.setParent(null);
		((AbstractUserForm) form).setUserState(UserState.ACTIVE.getValue());
		logger.debug("Created userGroupFilter {}.",filter);
		return filter;
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

	private final static Logger logger = LoggerFactory.getLogger(UserGroupActionImpl.class);
}
