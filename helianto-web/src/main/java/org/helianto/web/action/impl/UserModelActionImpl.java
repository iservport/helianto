package org.helianto.web.action.impl;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.action.AbstractModelAction;
import org.helianto.web.action.FormModel;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * User model action.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 * @see UserModelBuilder
 */
@Component("userModelAction")
public class UserModelActionImpl extends AbstractModelAction<User> {

	private static final long serialVersionUID = 1L;

	@Override
	protected User doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails, FormModel<User> formModel) {
		return new User(userDetails.getEntity(), (Identity) null);
	}

}
