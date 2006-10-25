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
import org.helianto.web.view.IdentityForm;
import org.springframework.webflow.AttributeMap;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.action.FormAction;

/**
 * Follows the identity.xml flow.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFormAction extends FormAction {
    
    private SecurityMgr securityMgr;
    
    public Event create(RequestContext context) {
        IdentityForm form = doGetForm(context);
        form.setCredential(securityMgr.createCredentialAndIdentity());
        return success();
    }
    
    public Event persistIdentity(RequestContext context) {
        securityMgr.persistIdentity(doGetForm(context).getCredential().getIdentity());
        return success();
    }
    
    public Event verify(RequestContext context) {
        Credential credential = doGetForm(context).getCredential();
        if (securityMgr.verifyPassword(credential)) {
            return success();
        }
        return error();
    }
    
    public Event nonUnique(RequestContext context) {
        IdentityForm form = doGetForm(context);
        return success();
    }
    
    public Event persistFinal(RequestContext context) {
        IdentityForm form = (IdentityForm) context.getFlowScope().get("identityForm");
        securityMgr.persistCredential(form.getCredential());
        return success();
    }
    
    //~ utilities
    protected IdentityForm doGetForm(RequestContext context) {
        AttributeMap flowScope = context.getFlowScope();
        return (IdentityForm) flowScope.getRequired(getFormObjectName());
    }
    
    //~ collaborators

    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr =  securityMgr;
    }

}
