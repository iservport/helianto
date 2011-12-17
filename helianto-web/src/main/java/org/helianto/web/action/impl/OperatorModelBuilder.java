package org.helianto.web.action.impl;

import org.helianto.core.filter.form.CompositeOperatorForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.model.AbstractPageModelBuilder;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Create selection model to operator related use cases.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("operatorModelBuilder")
public class OperatorModelBuilder extends AbstractPageModelBuilder<CompositeOperatorForm> {

	@Override
	protected CompositeOperatorForm doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new CompositeOperatorForm(userDetails.getOperator());
	}

}
