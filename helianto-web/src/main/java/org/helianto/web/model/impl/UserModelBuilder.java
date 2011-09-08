package org.helianto.web.model.impl;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.model.AbstractPageModelBuilder;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * User model builder.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("userModelBuilder")
public class UserModelBuilder extends AbstractPageModelBuilder<User> {

	@Override
	protected User doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new User(userDetails.getEntity(), (Identity) null);
	}

}
