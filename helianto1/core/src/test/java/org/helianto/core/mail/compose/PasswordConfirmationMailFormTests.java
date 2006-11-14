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

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Operator;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;

public class PasswordConfirmationMailFormTests extends TestCase {

    public void testMailForm() {
        Credential credential = AuthenticationTestSupport.createCredential();
        PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm();
        mailForm.setCredential(credential);
        assertSame(credential, mailForm.getCredential());

    }
    
    public void testMailFormOperatorConstructor() {
        Operator operator = OperatorTestSupport.createOperator();
        PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm(operator);
        assertSame(operator, mailForm.getOperator());

    }
    
}
