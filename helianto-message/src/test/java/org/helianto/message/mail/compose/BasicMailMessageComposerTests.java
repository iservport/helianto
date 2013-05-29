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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.helianto.core.def.IdentityType;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Operator;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class BasicMailMessageComposerTests {
    
    BasicMailMessageComposer mailMessageComposer;

	@Test(expected=IllegalArgumentException.class)
    public void composeMessageUnsupportedKey() {
        mailMessageComposer.composeMessage("UNSUPPORTED_KEY", new DefaultMailForm());
    }
    
	@Test
    public void composePasswordMessage() {
        PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm();
        Operator operator = OperatorTestSupport.createOperator();
        mailForm.setOperator(operator);
        Credential credential = new Credential(new Identity("PRINCIPAL"));
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
    
    @Before
    public void setUp() {
        mailMessageComposer = new BasicMailMessageComposer();
    }

}
