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

import org.helianto.core.Credential;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * <code>Credential</code> command.
 *  
 * @author Mauricio Fernandes de Castro
 */
@Component("credentialAction")
public class CredentialFormAction extends FormAction {

    private SecurityMgr securityMgr;

    /**
     * Standard form constructor
     */
    public CredentialFormAction() {
        setFormObjectName("credentialForm");
        setFormObjectClass(CredentialForm.class);
    }

    /**
     * Convenience to retrieve <code>CredentialForm</code>.
     */
    public CredentialForm doGetTaskForm(RequestContext context) {
        return (CredentialForm) context.getFlowScope().get(getFormObjectName());
    }
    
    /**
     * Delegate to <code>UserMgr</code> to write <code>Credential</code>.
     */
    public Event verifyPassword(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        CredentialForm form = doGetTaskForm(context);
        Credential credential = securityMgr.findCredentialByPrincipal(form.getCredential().getIdentity().getPrincipal());
        
        return success();
    }

    /**
     * Delegate to <code>UserMgr</code> to write <code>Credential</code>.
     */
    public Event storeCredential(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        CredentialForm credentialForm = doGetTaskForm(context);
        credentialForm.setCredential(securityMgr.storeCredential(credentialForm.getCredential()));
        return success();
    }
    
    /**
     * @deprecated in favor of storeCredential
     */
    public Event writeCredential(RequestContext context) {
        return storeCredential(context);
    }
    
    @javax.annotation.Resource
	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}
    
}
