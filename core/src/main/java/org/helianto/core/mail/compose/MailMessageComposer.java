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

package org.helianto.core.mail.compose;

import java.util.Map;

import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Base interface to all mail message composers.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface MailMessageComposer {
    
    /**
     * True if the key is supported.
     * @param key
     */
    public boolean supports(String key);
    
    /**
     * Compose a message as string
     * for a key.
     * @param key
     * @param model
     */
    public String composeMessage(String key, Map model);
    
    /**
     * Compose a message and hand it to a <code>MimeMessagePreparator</code>
     * for a key.
     * @param key
     * @param args
     */
    public MimeMessagePreparator composeMessage(String key, MailForm mailForm);

}
