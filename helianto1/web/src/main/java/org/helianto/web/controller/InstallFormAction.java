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

import org.helianto.core.User;
import org.helianto.core.service.ServerMgr;
import org.helianto.core.service.UserMgr;
import org.helianto.web.view.IdentityForm;
import org.helianto.web.view.UserForm;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Follows the configuration.xml flow.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InstallFormAction extends FormAction {
    
    private ServerMgr serverMgr;
    
    private UserMgr userMgr;
    
    
    
    public InstallFormAction() {
        setFormObjectName("userForm");
        setFormObjectClass(UserForm.class);
    }

    /**
     * Assure required collaborators at runtime.
     */
    public void init() {
        if (serverMgr==null) throw new IllegalArgumentException("Required serverMgr property is null");
        if (userMgr==null) throw new IllegalArgumentException("Required userMgr property is null");
    }
    
    /**
     * Test if there is already at least one operator.
     */
    public Event ifNew(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        if (serverMgr.findOperator().size()==0) {
        	if (logger.isDebugEnabled()) {
        		logger.debug("No operator present: new installation!");
        	}
            return yes();
        }
        return no();
    }
    
    /**
     * Create the manager <code>User</code>.
     */
    public Event createManager(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        IdentityForm identityForm = (IdentityForm) getFromRequestScope(context, "identityForm");
        if (logger.isDebugEnabled()) {
            logger.debug("Retrieved "+identityForm);
        }
        User user = serverMgr.writeManager(identityForm.getCredential().getIdentity());
        UserForm form = doGetForm(context);
        form.setUser(user);
        return success();
    }
    
    /**
     * Write manager <code>User</code>.
     */
    public Event writeManager(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        UserForm form = doGetForm(context);
        userMgr.writeUser(form.getUser());
        return success();
    }
        
    /**
     * Save password.
     */
    public Event writePassword(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("!---- STARTED");
        }
        UserForm form = doGetForm(context);
        // TODO
        userMgr.writeCredential(null);
        return success();
    }
        
    //~ utilities
    protected UserForm doGetForm(RequestContext context) {
        AttributeMap flowScope = context.getFlowScope();
        return (UserForm) flowScope.getRequired(getFormObjectName());
    }
    
    protected Object getFromRequestScope(RequestContext context, String name) {
        AttributeMap requestScope = context.getRequestScope();
        return requestScope.get(name);
    }


    //~ collaborators
    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }

    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }
    
}
