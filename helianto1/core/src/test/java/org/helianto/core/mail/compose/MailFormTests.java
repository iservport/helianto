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

import java.io.IOException;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.type.IdentityType;

public class MailFormTests extends TestCase {
    
    public void testMailForm() {
        Operator operator = OperatorTestSupport.createOperator();
        Identity identity = AuthenticationTestSupport.createIdentity();
        identity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        MailForm mailForm = createMailForm(operator, identity);
        assertSame(operator, mailForm.getOperator());
        assertSame(identity, mailForm.getRecipientIdentity());
        assertEquals("SUBJECT", mailForm.getSubject());

    }
    
    public void testMailFormOperatorConstructor() {
        Operator operator = OperatorTestSupport.createOperator();
        MailForm mailForm = new MailForm(operator);
        assertSame(operator, mailForm.getOperator());

    }
    
    public void testValidate() throws MessagingException, IOException {
        //create with organizational email
        MailForm mailForm = createMailForm();
        
        try {
            mailForm.setRecipientIdentity(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Identity is not addressable.", e.getMessage());
        }
        catch (Exception e) {
            fail();
        }
        
        Identity recipient = AuthenticationTestSupport.createIdentity();
        try {
            recipient.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
            mailForm.setRecipientIdentity(recipient);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Identity is not addressable.", e.getMessage());
        }
        catch (Exception e) {
            fail();
        }
        
        try {
            recipient.setIdentityType(IdentityType.GROUP.getValue());
            mailForm.setRecipientIdentity(recipient);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Identity is not addressable.", e.getMessage());
        }
        catch (Exception e) {
            fail();
        }
        
        //personal email is ok
        recipient.setIdentityType(IdentityType.PERSONAL_EMAIL.getValue());
        mailForm.setRecipientIdentity(recipient);

        //but the getter is also validated
        try {
            recipient.setIdentityType(IdentityType.GROUP.getValue());
            mailForm.getRecipientIdentity();
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Identity is not addressable.", e.getMessage());
        }
        catch (Exception e) {
            fail();
        }
        
    }
    
    public static MailForm createMailForm() {
        Operator operator = OperatorTestSupport.createOperator();
        Identity identity = AuthenticationTestSupport.createIdentity();
        identity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        return createMailForm(operator, identity);
    }
    
    public static MailForm createMailForm(Operator operator, Identity identity) {
        MailForm mailForm = new MailForm();
        mailForm.setOperator(operator);
        mailForm.setRecipientIdentity(identity);
        mailForm.setSubject("SUBJECT");
        return mailForm;
    }
    
}
