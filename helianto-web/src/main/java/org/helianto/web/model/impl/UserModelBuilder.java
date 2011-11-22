package org.helianto.web.model.impl;

import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.model.AbstractPageModelBuilder;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * User model builder.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserModelBuilder extends AbstractPageModelBuilder<CompositeUserForm> {

	@Override
	protected CompositeUserForm doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		CompositeUserForm form = new CompositeUserForm(userDetails.getEntity());
		return form;
	}

}
