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

package org.helianto.core.mail;

import org.helianto.core.Credential;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Default implementatio of the MailComposer interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public class MailComposerImpl implements MailComposer {

    private ResourceBundleMessageSource messageSource;
    
    public ResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String composeNotificationHeader() {
        return "";
    }

    public String composeNotificationFooter() {
        return getMessageSource().getMessage("registrationNotification.coda", 
                null, null);
    }

    public String composeRegistrationNotificationSubject(String prefix) {
        return getMessageSource().getMessage("registrationNotification.subject", 
                new String[] { prefix }, null);
    }

    public String composeRegistrationNotification(Credential cred, String confirmationAddress) {
        String[] params = new String[] {
            confirmationAddress,
            confirmationAddress,
            cred.getIdentity().getPrincipal(),
            cred.getPassword(),
            cred.getExpired().toString()
        };
        String body = getMessageSource().getMessage("registrationNotification.intro", 
                params, null); 
        body += composeNotificationFooter();
        return body;
    }

}
