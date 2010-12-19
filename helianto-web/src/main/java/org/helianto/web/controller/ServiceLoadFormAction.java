package org.helianto.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractLoadFormAction;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;

/**
 * Presentation logic to load services.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("serviceLoadAction")
public class ServiceLoadFormAction extends AbstractLoadFormAction<Service, Operator> {

	@Override
	protected List<Service> doLoad(Operator operator) {
		return namespaceMgr.loadServices(operator);
	}

	@Override
	public String getTargetAttributeName() {
		return "service";
	}

	@Override
	public String getParentAttributeName() {
		return "operator";
	}
	
	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

//	private static Logger logger = LoggerFactory.getLogger(ServiceFormAction.class);

}
