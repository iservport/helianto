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

import static org.junit.Assert.assertSame;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Operator;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Test;

public class PasswordConfirmationMailFormTests {

    @Test
    public void mailForm() {
        Credential credential = new Credential();
        PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm();
        mailForm.setCredential(credential);
        assertSame(credential, mailForm.getCredential());

    }
    
    @Test
    public void mailFormOperatorConstructor() {
        Operator operator = OperatorTestSupport.createOperator();
        PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm(operator);
        assertSame(operator, mailForm.getOperator());

    }
    
}
