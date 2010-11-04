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

package org.helianto.controller;

import org.helianto.core.security.PublicUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
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
        T objectForm = (T) getFormObjectScope().getScope(context).get(getFormObjectName());
        if (logger.isDebugEnabled()) {
            logger.debug("Getting form "+objectForm);
        }
        return objectForm ;
    }
    
    /**
     * Initialization.
     */
    public Event init(RequestContext context) {
        PublicUserDetails secureUser = (PublicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        context.getConversationScope().put("secureUser", secureUser);
    	return success();
    }

    /**
     * Invalidates the session.
     */
    public Event invalidateSession(RequestContext context) {
        SecurityContextHolder.clearContext();
        logger.debug("Session invalidated");
        return success();
    }
    
    protected static final Logger logger = LoggerFactory.getLogger(AbstractFormAction.class);

}
