package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Province;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.filter.classic.ProvinceFilter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to manage provinces.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("provinceAction")
public class ProvinceAction  extends AbstractFilterAction<Province> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String getTargetName() { return  "province"; }

	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new ProvinceFilter(userDetails.getUser().getOperator());
	}

	@Override
	protected List<Province> doFilter(ListFilter filter) {
		return namespaceMgr.findProvinces((ProvinceFilter) filter);
	}

	@Override
	protected Province doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Province(userDetails.getEntity().getOperator(), "");
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
