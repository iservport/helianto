package org.helianto.web.controller;

import org.helianto.core.service.UserMgr;
import org.helianto.web.view.CredentialForm;
import org.springframework.beans.factory.annotation.Required;
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

    /**
     * Standard form constructor
     */
    public CredentialFormAction() {
        setFormObjectName("credentialForm");
        setFormObjectClass(CredentialForm.class);
    }

    /**
     * Delegate to <code>UserMgr</code> to write <code>Credential</code>.
     */
    public Event verifyPassword(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        CredentialForm form = (CredentialForm) context.getFlowScope().get("credentialForm");
        userMgr.writeCredential(form.getCredential());
        return success();
    }

    /**
     * Delegate to <code>UserMgr</code> to write <code>Credential</code>.
     */
    public Event writeCredential(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        CredentialForm form = (CredentialForm) context.getFlowScope().get("credentialForm");
        userMgr.writeCredential(form.getCredential());
        return success();
    }

	@Required
    public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
    
}
