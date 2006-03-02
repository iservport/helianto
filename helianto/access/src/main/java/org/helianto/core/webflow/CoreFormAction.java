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

import org.helianto.core.service.CoreMgr;
import org.springframework.webflow.RequestContext;


/**
 * An interface to form actions using 
 * <code>CoreMgr</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface CoreFormAction {

    /**
     * @return Returns the coreMgr.
     */
    public CoreMgr getCoreMgr();

    /**
     * @param coreMgr The coreMgr to set.
     */
    public void setCoreMgr(CoreMgr coreMgr);

    /**
     * Convenience method to retrieve form from the current
     * context flow scope.
     */
    public Object retriveFormFromContext(RequestContext context);

    /**
     * Convenience method to replace form back in the current
     * context flow scope.
     */
    public void replaceFormInContext(RequestContext context, Object form);

}