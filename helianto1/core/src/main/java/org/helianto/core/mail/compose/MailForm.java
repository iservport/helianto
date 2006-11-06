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

import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.type.IdentityType;

/**
 * A base class to all forms to be submitted as mail forms.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MailForm {

    private Operator operator;

    private Identity recipientIdentity;

    private String subject;

    private Identity validatePrincipal(Identity identity) {
        if (identity == null
                || identity.getIdentityType() == IdentityType.NOT_ADDRESSABLE
                        .getValue()
                || identity.getIdentityType() == IdentityType.GROUP.getValue()) {
            throw new IllegalArgumentException("Identity is not addressable.");
        }
        return identity;
    }

    public Operator getOperator() {
        return operator;
    }

    public Identity getRecipientIdentity() {
        return validatePrincipal(recipientIdentity);
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setRecipientIdentity(Identity recipientIdentity) {
        this.recipientIdentity = validatePrincipal(recipientIdentity);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
