package org.helianto.web.controller;

import org.helianto.core.Credential;
import org.helianto.core.service.SecurityMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.view.CredentialForm;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * <code>Credential</code> command.
 *  
 * @author Mauricio Fernandes de Castro
 */
public class CredentialFormAction extends FormAction {

    private UserMgr userMgr;
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
    public Event writeCredential(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        CredentialForm form = doGetTaskForm(context);
        userMgr.writeCredential(form.getCredential());
        return success();
    }

    @javax.annotation.Resource
    public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

    @javax.annotation.Resource
	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}
    
}
