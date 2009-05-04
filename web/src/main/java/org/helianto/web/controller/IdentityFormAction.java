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

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Operator;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.ServerMgr;
import org.helianto.message.mail.compose.PasswordConfirmationMailForm;
import org.helianto.message.service.MessageMgr;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Web flow mediator to the the identity flow.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("identityAction")
public class IdentityFormAction extends FormAction {
    
    public IdentityFormAction() {
        setFormObjectName("identityForm");
        setFormObjectClass(IdentityForm.class);
    }
    
    /**
     * Create <code>Credential</code> if the corresponding
     * form property is null.
     */
    public Event createIfNecessary(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        IdentityForm form = doGetForm(context);
        if (form.getCredential()==null) {
            Credential credential = Credential.credentialFactory("");
            form.setCredential(credential);
            if (logger.isDebugEnabled()) {
                logger.debug("New credential set to "+credential+" set to form " +form);
            }
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Credential "+form.getCredential()+" already loaded ");
            }
        }
        return success();
    }
    
    /**
     * Store <code>Identity</code> to the datastore using a suspendend credential.
     * @throws Exception 
     */
    public Event storeSuspendedCredential(RequestContext context) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        return storeCredential(context, ActivityState.SUSPENDED);
    }
    
    /**
     * Store <code>Credential</code> to the datastore using a given state.
     * @throws Exception 
     */
    protected Event storeCredential(RequestContext context, ActivityState credentialState) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        IdentityForm identityForm = doGetForm(context);
        Credential credential = identityForm.getCredential();
        credential.setCredentialState(credentialState.getValue());
        try {
        	identityForm.setCredential(securityMgr.storeCredential(credential));
        } catch (Exception e) {
            Errors errors = getFormErrors(context);
            errors.rejectValue("credential.identity.principal", 
                    "principal.error.duplicate", 
                    "Duplicate principal, please choose another.");
            return error();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Persisted suspendend credential "+credential);
        }
        return success();
    }
    
    /**
     * @deprecated in favor of storeSuspendedCredential
     */
    public Event writeIdentity(RequestContext context) throws Exception {
        return storeCredential(context, ActivityState.SUSPENDED);
    }
    
    /**
     * Store active <code>Credential</code> to the datastore.
     * @throws Exception 
     */
    public Event storeActiveCredential(RequestContext context) throws Exception {
        return storeCredential(context, ActivityState.ACTIVE);
    }
    
    /**
     * Generate a random password.
     */
    public Event generatePassword(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        Credential credential = doGetForm(context).getCredential();
        credential.generatePassword();
        return success();
    }
    
    public Event send(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        PasswordConfirmationMailForm mailForm = createMailForm(context);
        if (mailForm!=null) {
            try {
            	messageMgr.sendPasswordConfirmation(mailForm);
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
    
    public Event verify(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        Credential credential = doGetForm(context).getCredential();
        if (credential.isPasswordVerified()) {
            return success();
        }
        return error();
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
            mailForm.getRecipientIdentities().add(credential.getIdentity());
            return mailForm;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Null operator, can't create MailForm");
        }
        return null;
    }
    
    //- utilities
    
    public void init() {
    }
    
    //~ collaborators 

    private MessageMgr messageMgr;
    private SecurityMgr securityMgr;
    private ServerMgr serverMgr;
    
    @javax.annotation.Resource
    public void setMessageMgr(MessageMgr messageMgr) {
        this.messageMgr =  messageMgr;
    }

    @javax.annotation.Resource
    public void setSecurityMgr(SecurityMgr securityMgr) {
        this.securityMgr =  securityMgr;
    }

    @javax.annotation.Resource
    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }

}
