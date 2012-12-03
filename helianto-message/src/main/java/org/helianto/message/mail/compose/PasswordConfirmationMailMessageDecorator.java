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

import org.helianto.core.domain.Credential;

/**
 * Decorates the mail message preparator with a password
 * confirmation message.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PasswordConfirmationMailMessageDecorator extends DecoratedPreparator {

    public PasswordConfirmationMailMessageDecorator(DecoratedPreparator parent) {
        super(parent.mailForm);
    }

    /**
     * Composes a plain password confirmation message and
     * append it to the message body.
     * 
     * @param templateName
     */
    protected void compose() {
        StringBuilder body = getBody();
        Credential credential = ((PasswordConfirmationMailForm) mailForm).getCredential();
        body.append("Welcome to ");
        body.append(mailForm.getOperator().getOperatorName());
        body.append(",\n\n");
        body.append("For reference, your identification information is:\n\n");
        body.append("Email    : ");
        body.append(credential.getIdentity().getPrincipal());
        body.append("\n");
        body.append("Password : ");
        body.append(credential.getPassword());
        body.append("\n\n");
        body.append("In order to be able to login in the site, you have to activate your \n");
        body.append("account. Please visit:\n\n");
        body.append("    http://www.helianto.org/admin\n\n");
        body.append("Thank you for your interest in ");
        body.append(mailForm.getOperator().getOperatorName());
        body.append(".\n\n");
        body.append("Regards,\n" + "The ");
        body.append(mailForm.getOperator().getOperatorName());
        body.append(" operator.\n");
    }

}
