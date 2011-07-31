package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.Phone;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.core.collection.ParameterMap;

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
	public String remove(MutableAttributeMap attributes, ParameterMap parameters) {
		Identity identity = (Identity) attributes.get("identity");
		if (identity==null) {
			throw new IllegalArgumentException("Unable to remove phone: missing identity.");
		}
		String[] removePhoneList = parameters.getArray("removePhoneList");
		for (String removePhone: removePhoneList) {
			logger.info("Removing {}...", identity.getPhones().get(Integer.parseInt(removePhone)));
			identity.getPhones().remove(Integer.parseInt(removePhone));
		}
		return "success";
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
	
	private static final Logger logger = LoggerFactory.getLogger(IdentityPhoneActionImpl.class);
	
}
