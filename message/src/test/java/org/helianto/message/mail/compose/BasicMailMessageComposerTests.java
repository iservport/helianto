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

import junit.framework.TestCase;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Operator;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.message.mail.compose.BasicMailMessageComposer;
import org.helianto.message.mail.compose.DefaultMailForm;
import org.helianto.message.mail.compose.PasswordConfirmationMailForm;
import org.helianto.message.mail.compose.PasswordConfirmationMailMessageDecorator;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class BasicMailMessageComposerTests extends TestCase {
    
    BasicMailMessageComposer mailMessageComposer;

    public void testComposeMessageUnsupportedKey() {
        try {
            mailMessageComposer.composeMessage("UNSUPPORTED_KEY", new DefaultMailForm());
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Unsupported key: UNSUPPORTED_KEY", e.getMessage());
        }
        catch (Exception e) {
            fail();
        }
    }
    
    public void testComposePasswordMessage() {
        PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm();
        Operator operator = OperatorTestSupport.createOperator();
        mailForm.setOperator(operator);
        Credential credential = CredentialTestSupport.createCredential();
        credential.getIdentity().setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        Set<Identity> identities = new HashSet<Identity>();
        identities.add(credential.getIdentity());
        mailForm.setRecipientIdentities(identities);
        mailForm.setCredential(credential);
        //test also mail form
        assertSame(operator, mailForm.getOperator());
        String expected = "Welcome to "
            + operator.getOperatorName()
            + ",\n\n"
            + "For reference, your identification information is:\n\n"
            + "Email    : "
            + credential.getIdentity().getPrincipal()
            + "\n"
            + "Password : "
            + credential.getPassword()
            + "\n\n"
            + "In order to be able to login in the site, you have to activate your \n"
            + "account. Please visit:\n\n"
            + "    http://www.helianto.org/admin\n\n"
            + "Thank you for your interest in " + operator.getOperatorName() + ".\n\n"
            + "Regards,\n" + "The " + operator.getOperatorName() + " operator.\n";
        PasswordConfirmationMailMessageDecorator result = 
            (PasswordConfirmationMailMessageDecorator) mailMessageComposer.composeMessage("PASSWORD", mailForm);
        result.compose();
        assertEquals(expected, result.getBody().toString());
    }
    
//    public void testComposePasswordMessageFailureWrongArgs() {
//        try {
//            mailMessageComposer.composeMessage("PASSWORD", new Object(), new Object());
//            fail();
//        }
//        catch (IllegalArgumentException e) {
//            assertEquals("Required parameter types are (Operator, Credential) to compose message with key: PASSWORD", e.getMessage());
//        }
//        catch (Exception e) {
//            fail();
//        }
//    }
    
    @Override
    public void setUp() {
        mailMessageComposer = new BasicMailMessageComposer();
    }

}
