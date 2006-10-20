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

import org.helianto.core.service.ServerMgr;
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
    
    public Event ifNew(RequestContext context) {
        if (serverMgr.findOperator().size()==0) {
            return yes();
        }
        return no();
    }

    //~ collaborators
    public void setServerMgr(ServerMgr serverMgr) {
        this.serverMgr = serverMgr;
    }
    
}