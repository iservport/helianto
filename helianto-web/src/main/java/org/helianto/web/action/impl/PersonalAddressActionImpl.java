package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.PersonalAddress;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.PersonalAddressFormFilterAdapter;
import org.helianto.core.filter.form.CompositeUserForm;
import org.helianto.core.filter.form.PersonalAddressForm;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.AbstractFilterAction;
import org.helianto.web.model.impl.UserModelBuilder;
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
		return userModelBuilder.getModelName();
	}
	
	protected CompositeUserForm getForm(MutableAttributeMap attributes) {
		return userModelBuilder.getForm(attributes);
	}

	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new PersonalAddressFormFilterAdapter(getForm(attributes));
	}

	@Override
	protected List<PersonalAddress> doFilter(MutableAttributeMap attributes, Filter filter, PublicUserDetails userDetails) {
		CompositeUserForm form = getForm(attributes);
		form.setIdentity(userDetails.getUser().getIdentity());
		return identityMgr.findPersonalAddresses(form);
	}

	@Override
	protected List<PersonalAddress> doFilter(Filter filter) {
		return identityMgr.findPersonalAddresses(filter);
	}

	protected List<PersonalAddress> doFilter(PersonalAddressForm form) {
		return identityMgr.findPersonalAddresses(form);
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
	private UserModelBuilder userModelBuilder;
	
	@Resource
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
	
	@Resource
	public void setUserModelBuilder(UserModelBuilder userModelBuilder) {
		this.userModelBuilder = userModelBuilder;
	}


}
