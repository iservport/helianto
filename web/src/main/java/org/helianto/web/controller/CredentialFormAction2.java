/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.helianto.web.controller;

import java.beans.PropertyEditor;

import javax.annotation.Resource;

import org.helianto.controller.AbstractEditTargetFormAction;
import org.helianto.core.Credential;
import org.helianto.core.PasswordNotVerifiedException;
import org.helianto.core.service.SecurityMgr;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store credentials.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("credentialAction2")
public class CredentialFormAction2 extends AbstractEditTargetFormAction<Credential> {

	@Override
	protected Credential doCreateTarget(RequestContext context) throws Exception {
		throw new IllegalArgumentException("Credentials are created along with user creation");
	}

	@Override
	protected Credential doPrepareTarget(RequestContext context, Credential target) throws Exception {
		return target;
	}

	@Override
	protected Credential doStoreTarget(Credential detachedTarget) throws Exception {
		return securityMgr.storeCredential(detachedTarget);
	}

	@Override
	protected void doHandleError(Errors errors, Exception e) {
		if (e instanceof PasswordNotVerifiedException) {
			errors.rejectValue("target.verifyPassword", "unverifiedPassword", "Unable to verify password");
		}
	}

	@Override
	public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {
		internalTargetPropertyEditorSetter(targetPropertyEditor, Credential.class);
	}

	@Override
	public String getTargetAttributeName() {
		return "credential";
	}
	
    private SecurityMgr securityMgr;

    @Resource
	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}
    
}
