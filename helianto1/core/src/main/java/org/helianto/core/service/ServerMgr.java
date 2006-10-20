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

package org.helianto.core.service;

import java.util.List;

import javax.mail.MessagingException;

import org.helianto.core.Credential;
import org.helianto.core.Operator;

/**
 * A base service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ServerMgr {

    public List<Operator> findOperator();

    /**
     * Send a <code>Credential</code> registration using 
     * mail settings from <code>Operator</code>.
     */
    public void sendRegistrationNotification(Operator operator, Credential cred)
            throws MessagingException;

}