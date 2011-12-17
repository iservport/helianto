package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Province;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.CompositeOperatorForm;
import org.helianto.core.filter.form.ProvinceForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to provinces.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("provinceAction")
public class ProvinceActionImpl extends AbstractFilterAction<Province> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return operatorModelBuilder.getModelName();
	}
	
	protected CompositeOperatorForm getForm(MutableAttributeMap attributes) {
		return operatorModelBuilder.getForm(attributes);
	}

	@Override
	protected List<Province> doFilter(MutableAttributeMap attributes, Filter filter) {
		return namespaceMgr.findProvinces(getForm(attributes));
	}

	protected List<Province> doFilter(ProvinceForm form) {
		return namespaceMgr.findProvinces(form);
	}

	@Override
	protected List<Province> doFilter(Filter filter) {
		throw new UnsupportedOperationException("Programmer error: use doFilter(Form)!");
	}

	@Override
	protected Province doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Province(userDetails.getOperator(), "");
	}

	@Override
	protected Province doStore(Province target) {
		return namespaceMgr.storeProvince(target);
	}
	
	// collabs
	
	private NamespaceMgr namespaceMgr;
	private OperatorModelBuilder operatorModelBuilder;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
	@Resource
	public void setOperatorModelBuilder(OperatorModelBuilder operatorModelBuilder) {
		this.operatorModelBuilder = operatorModelBuilder;
	}

}
