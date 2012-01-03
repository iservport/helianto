package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
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
	protected String getModelName() {
		return userModelBuilder.getModelName();
	}
	
	protected CompositeUserForm getForm(MutableAttributeMap attributes) {
		return userModelBuilder.getForm(attributes);
	}

	protected List<Identity> doFilter(CompositeUserForm form) {
		return identityMgr.findIdentities(form);
	}
	
	@Override
	protected List<Identity> doFilter(MutableAttributeMap attributes, Filter filter) {
		CompositeUserForm form = getForm(attributes);
		return doFilter(form);
	}
	
	@Override
	protected List<Identity> doFilter(Filter filter) {
		throw new IllegalArgumentException("Use doFilter(form)!");
	}
	
	/**
	 * Overriden to route "create" only if the principal does not exist.
	 * 
	 * @param attributes
	 * @param itemList
	 */
	protected String internalFilter(MutableAttributeMap attributes, List<Identity> itemList) {
		if (itemList!=null && itemList.size()==0) {
			return "create";
		}
		logger.debug("Selected: {}.", itemList.get(0));
		return "use";
	}

	@Override
	protected Identity doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Identity("");
	}

	@Override
	protected Identity doStore(Identity target) {
		return identityMgr.storeIdentity(target);
	}
	
	// collabs
	
	private IdentityMgr identityMgr;
	protected UserModelBuilder userModelBuilder;
	
	@Resource
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
	
	@Resource
	public void setUserModelBuilder(UserModelBuilder userModelBuilder) {
		this.userModelBuilder = userModelBuilder;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(IdentityActionImpl.class);

}
