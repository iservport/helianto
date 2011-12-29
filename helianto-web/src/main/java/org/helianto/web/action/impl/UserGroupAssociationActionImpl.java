package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.AssociationForm;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userGroupAssociationAction")
public class UserGroupAssociationActionImpl extends AbstractFilterAction<UserAssociation> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return userModelBuilder.getModelName();
	}

	@Override
	protected String getTargetName() { return "userAssociation"; }
	
	/**
	 * Helper method to retrieve form.
	 * 
	 * @param attributes
	 */
	protected CompositeUserForm getForm(MutableAttributeMap attributes) {
		return userModelBuilder.getForm(attributes);
	}
	
	protected List<UserAssociation> doFilter(MutableAttributeMap attributes, Filter filter) {
		UserGroup parent = (UserGroup) attributes.getRequired("userGroup");
		CompositeUserForm form = getForm(attributes).clone(parent);
		return doFilter(form);
	}

	/**
	 * @deprecated
	 */
	@Override
	protected List<UserAssociation> doFilter(Filter filter) {
		throw new IllegalArgumentException("Programmer error: use doFilter(form).");
	}

	protected List<UserAssociation> doFilter(AssociationForm form) {
		return userMgr.findUserAssociations(form);
	}

	@Override
	protected UserAssociation doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		UserGroup parent = (UserGroup) attributes.get("userGroup");
		if (parent==null) {
			throw new IllegalArgumentException("A parent user group is required in scope before association creation.");
		}
		UserGroup child = (UserGroup) attributes.get("user");
		if (child==null) {
			throw new IllegalArgumentException("A child user is required in scope before association creation.");			
		}
		logger.debug("Association has {} and {}.", parent, child);
	    return new UserAssociation(parent, child);
	}
	
	@Override
	@Transactional(readOnly=false)
	protected UserAssociation doStore(UserAssociation target) {
		return userMgr.storeUserAssociation(target);
	}
	
	protected UserGroup getChild(MutableAttributeMap attributes) {
		if (attributes.contains("child")) {
			return (UserGroup) attributes.get("child");
		}
		return null;
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

	private final static Logger logger = LoggerFactory.getLogger(UserGroupAssociationActionImpl.class);

}
