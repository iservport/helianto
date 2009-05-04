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

package org.helianto.support.service;

import javax.mail.MessagingException;

import org.helianto.core.Operator;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.support.Server;
import org.helianto.support.ServerType;
import org.helianto.support.mail.ConfigurableMailSender;

/**
 * Default service layer interface for the support package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SupportMgr {
    
    /**
     * Creates a new <code>ConfigurableMailSender</code>.
     * 
     * @param serverList
     */
    public ConfigurableMailSender createConfigurableMailSender(Operator operator);
    
    /**
     * Select the first available <code>Server</code>.
     * 
     * @param operator
     * @param serverType
     * @return
     */
    public Server selectFirstAvailableServer(Operator operator, ServerType serverType);
    
    /**
     * Send a <code>Credential</code> confirmation mail using 
     * a <code>PasswordConfirmationMailForm</code>.
     */
    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm)
            throws MessagingException;

}
