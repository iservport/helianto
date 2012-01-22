package org.helianto.web.action.impl;

import java.util.List;

import org.helianto.core.Identity;
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
 */
@Component("userIdentityAction")
public class UserIdentityActionImpl extends UserActionImpl {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<UserGroup> doFilter(MutableAttributeMap attributes, Filter filter, PublicUserDetails userDetails) {
		Identity identity = (Identity) attributes.get("identity");
		CompositeUserForm form = getForm(attributes);
		if (identity!=null) {
			form = form.clone(identity);
			form.setEntity(userDetails.getEntity());
			logger.debug("Filter restricted to identity {} and entity {}.", identity, userDetails.getEntity());
		}
		return doFilter(form);
	}
	
	protected String emptyList(MutableAttributeMap attributes, List<UserGroup> itemList) {
		return "empty";
	}
	
	private final static Logger logger = LoggerFactory.getLogger(UserIdentityActionImpl.class);
	
}
