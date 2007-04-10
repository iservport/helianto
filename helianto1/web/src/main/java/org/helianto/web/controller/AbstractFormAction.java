package org.helianto.web.controller;

import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Superclass of form action beans intended to act as a mediator code 
 * between the client and the service layer.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractFormAction extends FormAction {
    
    /**
     * Convenience method to retrieve a object form.
     */
    @SuppressWarnings("unchecked")
    public <T> T doGetObjectForm(RequestContext context) {
        T objectForm = (T) context.getFlowScope().get(getFormObjectName());
        if (logger.isDebugEnabled()) {
            logger.debug("Getting form "+objectForm);
        }
        return objectForm ;
    }
    
    /**
     * Initialization.
     */
    public Event init(RequestContext context) {
        PublicUserDetails secureUser = UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
        context.getConversationScope().put("secureUser", secureUser);
    	return success();
    }

    /**
     * Invalidates the session.
     */
    public Event invalidateSession(RequestContext context) {
        SecurityContextHolder.clearContext();
        if (logger.isDebugEnabled()) {
            logger.debug("Session invalidated");
        }
        return success();
    }
    
    protected static final Log logger = LogFactory.getLog(AbstractFormAction.class);

}
