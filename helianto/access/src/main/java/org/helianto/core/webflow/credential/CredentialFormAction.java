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

/**
 * Interface to define the actions of the credential flow.
 * 
 * <p>
 * The credential flow is responsible for storing a new credential 
 * in the datastore. Additionally, this flow handles the confirmation
 * e-mail sent either to the credential holder, or to the credential
 * indicated by the <code>parent</code> field, if not null. 
 * 
 * The {@link CredentialType}  
 * <code>NOT_ADDRESSABLE</code> does not require confirmation data 
 * to be sent by email to the credential owner.
 * @author Mauricio Fernandes de Castro
 */
public interface CredentialFormAction {
    
    public Event resolveSupervisor(RequestContext context);

    public Event checkCredential(RequestContext context);

    public Event finishCredential(RequestContext context);

    public Event sendCredential(RequestContext context);

}