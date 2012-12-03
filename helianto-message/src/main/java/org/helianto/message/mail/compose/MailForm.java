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

import java.util.Set;

import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Operator;

/**
 * Common interface to all mail forms.
 * 
 * @author Mauricio Fernandes de Castro
 *
 */
public interface MailForm {
	
    /**
     * Mail content getter.
     */
	public String getContent();
    
    /**
     * Mail content setter.
     */
	public void setContent(String content);

    /**
     * <code>Operator</code> getter.
     */
    public Operator getOperator();

    /**
     * Recipient <code>Identity</code> collection getter.
     */
    public Set<Identity> getRecipientIdentities();

    /**
     * Recipient <code>Identity</code> collection setter.
     */
	public void setRecipientIdentities(Set<Identity> identities);

    /**
     * Subject getter.
     */
    public String getSubject();

    /**
     * Subject setter.
     */
    public void setSubject(String subject);

}