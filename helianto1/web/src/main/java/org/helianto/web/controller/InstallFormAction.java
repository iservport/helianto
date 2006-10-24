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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.service.ServerMgr;
import org.helianto.web.view.UserForm;
import org.springframework.webflow.AttributeMap;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.action.FormAction;

/**
 * 
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InstallFormAction extends FormAction {
    
    private ServerMgr serverMgr;
    
    /**
     * Test if there is already at least one operator.
     */
    public Event ifNew(RequestContext context) {
        if (serverMgr.findOperator().size()==0) {
            return yes();
        }
        return no();
    }
    
    /**
     * Create the manager <code>User</code>.
     */
    public Event createManager(RequestContext context) {
        Identity managerIdentity = (Identity) getFromRequestScope(context, "identity");
        User user = serverMgr.createSystemConfiguration(managerIdentity);
        UserForm form = doGetForm(context);
        form.setUser(user);
        return success();
    }
    
    /**
     * Create the manager <code>User</code>.
     */
    public Event persistManager(RequestContext context) {
        UserForm form = doGetForm(context);
//        form.setUser(user);
        return success();
    }
    
    //TODO review operator creation flow states bellow
    
    /**
     * Invoke service layer to create a default operator and
     * make it available to the form flow;
     */
    public Event createOperator(RequestContext context) {
//        Operator operator = serverMgr.createLocalDefaultOperator();
//        OperatorForm form = doGetForm(context);
//        form.setOperator(operator);
        return success();
    }
    
    /**
     * Load available locales in request scope.
     */
    public Event loadAvailableLocalesInRequestScope(RequestContext context) {
        Locale[] locales = Locale.getAvailableLocales();
        Map<String, String> localesMap = new HashMap<String, String>();
        for (Locale locale: locales) {
            localesMap.put(locale.toString(), locale.getDisplayName());
        }
        context.getRequestScope().put("locales", localesMap);
        return success();
    }
    
    /**
     * Persist the operator retrieved from the flow.
     */
    public Event persistOperator(RequestContext context) {
//        Operator operator = doGetForm(context).getOperator();
//        serverMgr.persistOperator(operator);
        return success();
    }
    
    /**
     * List all operators in request scope.
     */
    public Event listOperatorInRequestScope(RequestContext context) {
        context.getRequestScope().put("operatorList", serverMgr.findOperator());
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
    
}
