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


package org.helianto.message.service;

import javax.mail.MessagingException;

import org.helianto.message.mail.compose.PasswordConfirmationMailForm;

/**
 * Message service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface MessageMgr {

    /**
     * Send a <code>Credential</code> confirmation mail using 
     * a <code>PasswordConfirmationMailForm</code>.
     * @deprecated
     */
    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm)
            throws MessagingException;

    /**
     * Send a confirmation mail.
     * 
     * @param recipient
     * @param sender
     * @param subject
     * @param htmlMessageBody
     */
    public void send(String recipient, String sender, String subject, String htmlMessageBody)
            throws MessagingException;

}
