package org.helianto.web.action.impl;

import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.model.AbstractPageModelBuilder;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Create selection model to entity related use cases.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("entityModelBuilder")
public class EntityModelBuilder extends AbstractPageModelBuilder<CompositeEntityForm> {

	@Override
	protected CompositeEntityForm doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		CompositeEntityForm form = new CompositeEntityForm(userDetails.getOperator());
		return form;
	}

}
