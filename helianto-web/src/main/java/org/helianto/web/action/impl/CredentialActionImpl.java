package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.SecurityMgr;
import org.helianto.web.action.AbstractAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to find or create a credential.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("credentialAction")
public class CredentialActionImpl extends AbstractAction<Credential> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected Credential doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		Identity identity = (Identity) attributes.get("identity");
		if (identity==null) {
			throw new IllegalArgumentException("Unable to create credential: missing identity.");
		}
		return new Credential(identity, "123456");
	}

	@Override
	protected Credential doStore(Credential target) {
		return securityMgr.storeCredential(target);
	}

	// collabs
	
	private SecurityMgr securityMgr;
	
	@Resource
	public void setSecurityMgr(SecurityMgr securitMgr) {
		this.securityMgr = securitMgr;
	}
	
}
