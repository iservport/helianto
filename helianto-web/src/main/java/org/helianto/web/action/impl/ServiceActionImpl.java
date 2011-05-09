package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.ServiceFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to services.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("serviceAction")
public class ServiceActionImpl extends AbstractFilterAction<Service> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return "userModel";
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new ServiceFilterAdapter(userDetails.getOperator(), "");
	}
	
	@Override
	protected List<Service> doFilter(Filter filter) {
		return namespaceMgr.findServices(filter);
	}
	
	@Override
	protected Service doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Service(userDetails.getOperator(), "");
	}
	
	@Override
	protected Service doStore(Service target) {
		return namespaceMgr.storeService(target);
	}
	
	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
}
