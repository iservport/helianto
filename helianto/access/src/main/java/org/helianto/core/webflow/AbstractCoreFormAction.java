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

package org.helianto.core.webflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.service.CoreMgr;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.action.FormAction;


/**
 * A base class for all form actions using 
 * <code>CoreMgr</code>.
 *  
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class AbstractCoreFormAction extends FormAction implements CoreFormAction {
    
    /**
     * Logger for this class.
     */
    protected final Log logger = LogFactory.getLog(AbstractCoreFormAction.class);
    
    private CoreMgr coreMgr;

    public CoreMgr getCoreMgr() {
        return coreMgr;
    }

    public void setCoreMgr(CoreMgr coreMgr) {
        this.coreMgr = coreMgr;
    }

    public Object retriveFormFromContext(RequestContext context) {
        return context.getFlowScope().getAttribute(getFormObjectName());
    }
    
    public void replaceFormInContext(RequestContext context, Object form) {
        context.getFlowScope().setAttribute(getFormObjectName(), form);
    }
    
}
