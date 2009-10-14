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

import java.util.HashSet;
import java.util.Set;

import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Operator;

/**
 * A base class to all forms to be submitted as mail forms.
 * By default, validates the recipient <code>Identity</code>
 * either when setting or getting.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultMailForm implements MailForm {

    private Operator operator;

    private Set<Identity> identities;

    private String subject = "";
    
    private String content = "";

	/**
     * Default constructor for subclasses.
     */
    public DefaultMailForm() { }

    /**
     * <code>Operator</code> driven constructor.
     */
    public DefaultMailForm(Operator operator) { 
        this.operator = operator;
    }

    public String getContent() {
		return content;
	}
    
	public void setContent(String content) {
		this.content = content;
	}

	protected Identity validatePrincipal(Identity identity) {
        if (identity == null
                || identity.getIdentityType() == IdentityType.NOT_ADDRESSABLE
                        .getValue()
                || identity.getIdentityType() == IdentityType.GROUP.getValue()) {
            throw new IllegalArgumentException("Identity is not addressable.");
        }
        return identity;
    }

	protected Set<Identity> validatePrincipal(Set<Identity> identities) {
		for (Identity identity: identities) {
			validatePrincipal(identity);
		}
        return identities;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Set<Identity> getRecipientIdentities() {
    	if (identities==null) {
    		identities = new HashSet<Identity>();
    	}
		return validatePrincipal(identities);
	}

	public void setRecipientIdentities(Set<Identity> identities) {
		this.identities = validatePrincipal(identities);
	}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
