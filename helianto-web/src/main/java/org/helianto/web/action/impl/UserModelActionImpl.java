package org.helianto.web.action.impl;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.web.action.AbstractModelAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * User model action.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userModelAction")
public class UserModelActionImpl extends AbstractModelAction<User> {

	private static final long serialVersionUID = 1L;

	@Override
	protected User doCreateFilter(MutableAttributeMap attributes, User user) {
		return new User(user.getEntity(), (Identity) null);
	}

}
