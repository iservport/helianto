package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.PersonalAddress;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.PersonalAddressFilterAdapter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to find personal addresses.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("personalAddressAction")
public class PersonalAddressActionImpl extends AbstractFilterAction<PersonalAddress> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getModelName() {
		return "authorizationModel";
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new PersonalAddressFilterAdapter(new PersonalAddress(userDetails.getUser().getIdentity(), null));
	}

	@Override
	protected List<PersonalAddress> doFilter(Filter filter) {
		return identityMgr.findPersonalAddresses(filter);
	}

	@Override
	protected PersonalAddress doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		// must use flow scope identity
		Identity identity = (Identity) attributes.get("identity");
		if (identity==null) {
			throw new IllegalArgumentException("Unable to create personal address: missing identity.");
		}
		return new PersonalAddress(identity, null);
	}

	@Override
	protected PersonalAddress doStore(PersonalAddress target) {
		return identityMgr.storePersonalAddress(target);
	}
	
	// collabs
	
	private IdentityMgr identityMgr;
	
	@Resource
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}


}
