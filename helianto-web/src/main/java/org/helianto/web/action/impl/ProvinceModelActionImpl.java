package org.helianto.web.action.impl;

import org.helianto.core.Province;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.web.action.AbstractModelAction;
import org.helianto.web.action.FormModel;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Create selection model to province use case.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("provinceModelAction")
public class ProvinceModelActionImpl extends AbstractModelAction<Province>{

	private static final long serialVersionUID = 1L;

	@Override
	protected Province doCreateForm(MutableAttributeMap attributes, PublicUserDetails userDetails, FormModel<Province> formModel) {
		return new Province(userDetails.getOperator(), "");
	}

}
