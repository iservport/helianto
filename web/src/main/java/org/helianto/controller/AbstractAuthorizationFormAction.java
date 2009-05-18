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

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle secure user events.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractAuthorizationFormAction<T> extends AbstractModelFormAction<T> {

	/**
     * Default constructor.
     */
    public AbstractAuthorizationFormAction() {
    	super();
    }
    
    /**
     * Authorized user set in security context.
     */
	public User getAuthorizedUser() {
        PublicUserDetails secureUser = UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
        if (secureUser==null) {
        	return null;
        }
        return secureUser.getUser();
	}

    /**
     * Authorized entity set in security context.
     */
	public Entity getAuthorizedEntity() {
		if (getAuthorizedUser()==null) {
			return null;
		}
        return getAuthorizedUser().getEntity();
	}

    /**
     * Create authorization.
     * @throws Exception 
     */
    public Event createAuthorization(RequestContext context) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- createAuthorization\n");
        }
        PublicUserDetails secureUser = UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
        context.getConversationScope().put("secureUser", secureUser);
        return success();
    }
    
    /**
     * Resolve authorization.
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
	public Event resolveAuthorization(RequestContext context) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
            logger.debug("!---- resolveAuthorization\n");
        }
        int localAuthorization = doResolveAuthorization(context, (T) get(context));
        if (localAuthorization>0) {
        	context.getFlashScope().put("localAuthorization", localAuthorization);
            if (logger.isDebugEnabled()) {
                logger.debug("Local authorization is "+localAuthorization);
            }
        	return success();
        }
        return error();
    }
    
	/**
	 * Subclasses may override to do any local authorization resolution.
	 * 
	 * @param context
	 * @throws Exception
	 */
    protected int doResolveAuthorization(RequestContext context, T target) throws Exception {
    	return 1;
    }
    
    
	@Override
	protected void registerPropertyEditors(PropertyEditorRegistry registry) {
		super.registerPropertyEditors(registry);
		
        DateFormat dateFormat = null;
        try {
        	Locale locale = Locale.getDefault();
        	if (getAuthorizedUser()!=null) {
                locale = getAuthorizedUser().getLocale();
        	}
        	// TODO refator using preferences API
//        	dateFormat = new SimpleDateFormat(operator.getPreferredDateFormat()+" "+operator.getPreferredTimeFormat(), operator.getLocale());
        	dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
        } catch (Exception e) {
        	dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        }
        PropertyEditor customDateEditor = new CustomDateEditor(dateFormat, false);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Registering custom editor "+customDateEditor);
        }
        registry.registerCustomEditor(Date.class, customDateEditor);

	}
	
}
