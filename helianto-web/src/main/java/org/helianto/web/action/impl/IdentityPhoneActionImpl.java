package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.Phone;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.AbstractAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to create a phone.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("identityPhoneAction")
public class IdentityPhoneActionImpl extends AbstractAction<Phone> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Phone doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new Phone();
	}

	@Override
	protected Phone doStore(MutableAttributeMap attributes, Phone target) {
		Identity identity = (Identity) attributes.get("identity");
		if (identity==null) {
			throw new IllegalArgumentException("Unable to store phone: missing identity.");
		}
		identityMgr.storeIdentityPhone(target, identity);
		return null;
	}

	@Override
	protected Phone doStore(Phone target) {
		throw new IllegalArgumentException("Forbidden!");
	}

	// collabs
	
	private IdentityMgr identityMgr;
	
	@Resource
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
	
}
