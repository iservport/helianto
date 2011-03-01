package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Province;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.ProvinceFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.action.PageModel;
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
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		PageModel<Province> model = getModel(attributes);
		return new ProvinceFilterAdapter(model.getFilter());
	}

	@Override
	protected List<Province> doFilter(Filter filter) {
		return namespaceMgr.findProvinces(filter);
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
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

}
