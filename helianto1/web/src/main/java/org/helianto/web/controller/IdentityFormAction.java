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
import org.helianto.core.service.ServerMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.view.IdentityForm;
import org.springframework.beans.factory.annotation.Required;
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
public class IdentityFormAction extends FormAction {
    
    private UserMgr userMgr;
    private ServerMgr serverMgr;
    
    public IdentityFormAction() {
        setFormObjectName("identityForm");
        setFormObjectClass(IdentityForm.class);
    }
    
    /**
     * Create <code>Credential</code> if the corresponding
     * form property is null.
     */
    public Event createIfNecessary(RequestContext context) {
        IdentityForm form = doGetForm(context);
        if (form.getCredential()==null) {
            Credential credential = Credential.credentialFactory();
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
     * Write <code>Identity</code> to the datastore.
     */
    public Event writeIdentity(RequestContext context) {
        Identity identity = doGetForm(context).getCredential().getIdentity();
        userMgr.writeIdentity(identity);
        if (logger.isDebugEnabled()) {
            logger.debug("Persisted identity "+identity);
        }
        return success();
    }
    
    /**
     * Update errors object if non unique.
     */
    public Event nonUnique(RequestContext context) {
        Errors errors = (Errors) context.getFlashScope().get("errors");
        errors.rejectValue("credential.identity.principal", 
                "principal.error.duplicate", 
                "Duplicate principal, please choose another.");
        return error();
    }
    
    /**
     * Generate a random password.
     */
    public Event generatePassword(RequestContext context) {
        Credential credential = doGetForm(context).getCredential();
        credential.generatePassword();
        return success();
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
    
    public Event verify(RequestContext context) {
        Credential credential = doGetForm(context).getCredential();
        if (Credential.verifyPassword(credential)) {
            return success();
        }
        return error();
    }
    
    public Event persistFinal(RequestContext context) {
        IdentityForm form = (IdentityForm) context.getFlowScope().get("identityForm");
        userMgr.writeCredential(form.getCredential());
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

    @Required
    public void setUserMgr(UserMgr userMgr) {
        this.userMgr =  userMgr;
    }

    @Required
    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }

}
