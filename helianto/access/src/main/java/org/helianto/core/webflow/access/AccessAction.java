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

package org.helianto.core.webflow.access;

import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;

/**
 * Interface to the access flow.
 * 
 * <p>
 * Entity resolution is essential to the Helianto architecture. I uses the
 * <code>User</code> object, designed to represent both the user
 * identity and the <code>Entity</code> it belongs to. The <code>Entity</code>
 * resolution issue is actually an <code>User</code> resolution issue,
 * as soon as the user identity is established by the security system.
 * </p> 
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface AccessAction {

    /**
     * Try to find an <code>Entity</code> after the
     * <code>Credential</code> has been established.  
     */
    public Event findEntity(RequestContext context);

    public Event findService(RequestContext context);

}