package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.ContactInfo;
import org.helianto.core.Identity;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.IdentityMgr;
import org.helianto.web.action.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.core.collection.ParameterMap;

/**
 * Presentation logic to create a contact info.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("identityContactInfoAction")
public class IdentityContactInfoActionImpl extends AbstractAction<ContactInfo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected ContactInfo doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new ContactInfo();
	}

	@Override
	protected ContactInfo doStore(MutableAttributeMap attributes, ContactInfo target) {
		Identity identity = (Identity) attributes.get("identity");
		if (identity==null) {
			throw new IllegalArgumentException("Unable to store contact info: missing identity.");
		}
		identityMgr.storeIdentityContactInfo(target, identity);
		return null;
	}
	
	@Override
	public String remove(MutableAttributeMap attributes, ParameterMap parameters) {
		Identity identity = (Identity) attributes.get("identity");
		if (identity==null) {
			throw new IllegalArgumentException("Unable to remove contact info: missing identity.");
		}
		String[] contactInfoList = parameters.getArray("contactInfoList");
		for (String contactInfo: contactInfoList) {
			logger.info("Removing {}...", identity.getPhones().get(Integer.parseInt(contactInfo)));
			identity.getPhones().remove(Integer.parseInt(contactInfo));
		}
		return "success";
	}

	@Override
	protected ContactInfo doStore(ContactInfo target) {
		throw new IllegalArgumentException("Forbidden!");
	}

	// collabs
	
	private IdentityMgr identityMgr;
	
	@Resource
	public void setIdentityMgr(IdentityMgr identityMgr) {
		this.identityMgr = identityMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(IdentityContactInfoActionImpl.class);
	
}
