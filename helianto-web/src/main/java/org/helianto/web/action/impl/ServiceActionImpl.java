package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.ServiceFormFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
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
		return userModelBuilder.getModelName();
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new ServiceFormFilterAdapter(userModelBuilder.getForm(attributes));
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
	protected UserModelBuilder userModelBuilder;
	
	@Resource
	public void setUserModelBuilder(UserModelBuilder userModelBuilder) {
		this.userModelBuilder = userModelBuilder;
	}
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
}
