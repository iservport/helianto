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

package org.helianto.core.webflow.credential;

import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.ScopeType;

import org.helianto.core.Credential;
import org.helianto.core.Supervisor;
import org.helianto.core.webflow.AbstractCoreFormAction;

/**
 * Form action class implementation to execute the credential flow.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CredentialFormActionImpl extends AbstractCoreFormAction implements CredentialFormAction {
    
    /**
     * Constructor.
     */
    public CredentialFormActionImpl() {
        setFormObjectName("credential");
        setFormObjectClass(Credential.class);
        setFormObjectScope(ScopeType.FLOW);
        if (logger.isDebugEnabled()) {
            logger.debug("Created form action with " +
                    "name "+getFormObjectName()+" " +
                    "for class "+getFormObjectClass()+" " +
                    " and scope "+getFormObjectScope());
        }
    }

    @Override
    protected Object loadFormObject(RequestContext context) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("\n        Loading credential as form object");
        }
        Credential cred = getCoreMgr().credentialFactory();
        return cred;
    }

    public Event resolveSupervisor(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         ENTERING STATE");
        }
        Object param = context.getLastEvent().getParameter("supervisorId");
        try {
            Supervisor supervisor = getCoreMgr().findRequiredSupervisor(param);
            preserveSupervisor(context, supervisor);
            return success();
        } catch (Exception e) {
            logger.error("resolveSupervisor raised: "+e.toString());
            return error();
        }
    }

    public Event checkCredential(RequestContext context) {
        Credential cred = (Credential) retriveFormFromContext(context);
        Credential check = getCoreMgr().findCredentialByPrincipal(cred.getPrincipal());
        if (check!=null) {
            logger.error("checkCredential already in use: "+check.toString());
            return error();
        }
        return success();
    }

    public Event finishCredential(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         ENTERING STATE");
        }
        Credential cred = (Credential) retriveFormFromContext(context);
        try {
            getCoreMgr().persistCredential(cred);
            replaceFormInContext(context, cred);
            return success();
        } catch (Exception e) {
            logger.error("finishCredential raised: "+e.toString());
            return error();
        }
    }

    public Event sendCredential(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         ENTERING STATE");
        }
        Credential cred = (Credential) retriveFormFromContext(context);
        Supervisor supervisor = retrieveSupervisor(context);
        try {
            getCoreMgr().sendRegistrationNotification(supervisor, cred);
            replaceFormInContext(context, cred);
            return success();
        } catch (Exception e) {
            logger.error("sendCredential raised: "+e.toString());
            return error();
        }
    }

    /**
     * Retrieve <code>Supervisor</code> from flow scope.
     */
    protected Supervisor retrieveSupervisor(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Retrieve supervisor from "+context.getFlowScope());
        }
        return (Supervisor) context.getFlowScope().getAttribute("supervisor");
    }
    
    /**
     * Preserve <code>Supervisor</code> in flow scope.
     */
    protected void preserveSupervisor(RequestContext context, Supervisor supervisor) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Preserve "+supervisor+" as supervisor in "+context.getFlowScope());
        }
        context.getFlowScope().setAttribute("supervisor", supervisor);
    }

}
