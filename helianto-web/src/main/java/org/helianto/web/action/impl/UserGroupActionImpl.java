package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserGroup;
import org.helianto.core.def.UserState;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.UserFormFilterAdapter;
import org.helianto.core.filter.form.AbstractUserForm;
import org.helianto.core.filter.form.CompositeUserForm;
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
	
	protected CompositeUserForm getForm(MutableAttributeMap attributes) {
		return userModelBuilder.getForm(attributes);
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserFormFilterAdapter filter = new UserFormFilterAdapter(getForm(attributes));
		return filter;
	}
	
	@Override
	protected List<UserGroup> doFilter(MutableAttributeMap attributes, Filter filter) {
		CompositeUserForm form = getForm(attributes).clone(null);
		((AbstractUserForm) form).setUserGroupType('G');
		((AbstractUserForm) form).setUserState(UserState.ACTIVE.getValue());
		return doFilter(form);
	}
	
	/**
	 * @deprecated
	 * @see #doFilter(UserGroupForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<UserGroup> doFilter(Filter filter) {
		return (List<UserGroup>) userMgr.findUsers(filter);
	}

	@SuppressWarnings("unchecked")
	protected List<UserGroup> doFilter(UserGroupForm form) {
		return (List<UserGroup>) userMgr.findUsers(form);
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
