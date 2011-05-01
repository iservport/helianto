package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.IdentityFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to find or create an identity.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("identityAction")
public class IdentityActionImpl extends AbstractFilterAction<Identity> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new IdentityFilterAdapter("");
	}

	@Override
	protected List<Identity> doFilter(Filter filter) {
		return userMgr.findIdentities(filter, null);
	}
	
	/**
	 * Overriden to route "create" only if the principal does not exist.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected String internalFilter(MutableAttributeMap attributes, List<Identity> itemList) {
		if (itemList!=null && itemList.size()==0) {
			attributes.put(getTargetName(), itemList.get(0));
			logger.debug("Auto selected: {}.", itemList.get(0));
			return "create";
		}
		attributes.put(getTargetName(), null);
		logger.debug("Filter selection empty, auto selecte cleared the target");
		return "searchAgain";
	}

	@Override
	protected Identity doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Identity("");
	}

	@Override
	protected Identity doStore(Identity target) {
		return userMgr.storeIdentity(target);
	}
	
	// collabs
	
	private UserMgr userMgr;
	
	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(IdentityActionImpl.class);

}
