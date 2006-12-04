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

import javax.mail.MessagingException;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.ServerMgr;
import org.helianto.web.view.IdentityForm;
import org.springframework.validation.Errors;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Follows the identity.xml flow.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityFormAction extends FormAction {
    
    private SecurityMgr securityMgr;
    private ServerMgr serverMgr;
    
    public IdentityFormAction() {
        setFormObjectName("identityForm");
        setFormObjectClass(IdentityForm.class);
    }
    
    /**
     * Delegates to <code>SecurityMgr#createCredentialAndIdentity()</code>
     * and populates the form with the new <code>Credential</code>.
     */
    public Event create(RequestContext context) {
        IdentityForm form = doGetForm(context);
        Credential credential = securityMgr.createCredentialAndIdentity();
        form.setCredential(credential);
        if (logger.isDebugEnabled()) {
            logger.debug("Set property credential to "+credential+" on form " +form);
        }
        return success();
    }
    
    /**
     * Simply delegates to <code>securityMgr.persistIdentity(Identity)</code>.
     */
    public Event persistIdentity(RequestContext context) {
        Identity identity = doGetForm(context).getCredential().getIdentity();
        securityMgr.persistIdentity(identity);
        if (logger.isDebugEnabled()) {
            logger.debug("Persisted identity "+identity);
        }
        return success();
    }
    
    public Event verify(RequestContext context) {
        Credential credential = doGetForm(context).getCredential();
        if (securityMgr.verifyPassword(credential)) {
            return success();
        }
        return error();
    }
    
    public Event send(RequestContext context) {
        PasswordConfirmationMailForm mailForm = createMailForm(context);
        if (mailForm!=null) {
            try {
                serverMgr.sendPasswordConfirmation(mailForm);
                if (logger.isDebugEnabled()) {
                    logger.debug("Sent mailForm "+mailForm);
                }
                return success();
            } catch (MessagingException e) {
                return error();
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Null MailForm, can't send");
        }
        return error();
    }
    
    public Event generatePassword(RequestContext context) {
        
        return success();
    }
    
    public Event nonUnique(RequestContext context) {
        IdentityForm form = doGetForm(context);
//        Errors errors = (Errors) context.getFlashScope().get("errors");
//        errors.rejectValue("credential.identity.principal", 
//                "principal.error.duplicate", 
//                "Duplicate principal, please choose another.");
        //TODO non unique identity
        return error();
    }
    
    public Event persistFinal(RequestContext context) {
        IdentityForm form = (IdentityForm) context.getFlowScope().get("identityForm");
        securityMgr.persistCredential(form.getCredential());
        return success();
    }
    
    //~ utilities
    protected IdentityForm doGetForm(RequestContext context) {
        AttributeMap flowScope = context.getFlowScope();
        IdentityForm identityForm = 
            (IdentityForm) flowScope.getRequired(getFormObjectName()); 
        if (logger.isDebugEnabled()) {
            logger.debug("Retrive "+identityForm+"from scope "+flowScope);
        }
        return identityForm;
    }
    
    protected Operator getOperator(RequestContext context) {
        AttributeMap flowScope = context.getFlowScope();
        Operator operator = 
            (Operator) flowScope.get("operator");
        if (logger.isDebugEnabled()) {
            logger.debug("Retrive "+operator+"from scope "+flowScope);
        }
        return operator;
    }
    
    /**
     * Method to extract <code>MailForm</code> properties from
     * the context.
     */
    protected PasswordConfirmationMailForm createMailForm(RequestContext context) {
        Operator operator = getOperator(context);
        if (operator!=null) {
            PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm(operator);
            Credential credential = doGetForm(context).getCredential();
            mailForm.setCredential(credential);
            mailForm.setRecipientIdentity(credential.getIdentity());
            return mailForm;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Null operator, can't create MailForm");
        }
        return null;
    }
    
    //- utilities
    
    public void init() {
        if (securityMgr==null) {
            throw new IllegalArgumentException("Required securityMgr");
        }
        if (serverMgr==null) {
            throw new IllegalArgumentException("Required serverMgr");
        }
    }
    
    //~ collaborators

    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr =  securityMgr;
    }

    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }

}
