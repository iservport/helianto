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

package org.helianto.message.mail.compose;

import java.util.Map;

import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * Default implementation of <code>MailMessageComposer</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("basicMailMessageComposer")
public class BasicMailMessageComposer extends AbstractMailMessageComposer {
    
    /**
     * Constructor.
     */
    public BasicMailMessageComposer() {
        super();
        getSupportedKeys().add("PASSWORD");
    }
    
    public MimeMessagePreparator composeMessage(String key, MailForm mailForm) {
        if (!supports(key)) {
            throw new IllegalArgumentException("Unsupported key: "+key);
        } 
        else if (key == "PASSWORD") {
            PasswordConfirmationMailMessageDecorator decoratedPreparator =
                new PasswordConfirmationMailMessageDecorator(
                        new DecoratedPreparator(mailForm));
            return decoratedPreparator;
        }
        throw new IllegalArgumentException("Unhandled key: "+key);
    }

	public String composeMessage(String key, Map model) {
		// not used
		return null;
	}

}