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
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Operator;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Test;

public class MailFormTests {
    
    @Test
    public void mailForm() {
        Operator operator = OperatorTestSupport.createOperator();
        Identity identity = IdentityTestSupport.createIdentity();
        identity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        MailForm mailForm = createMailForm(operator, identity);
        assertSame(operator, mailForm.getOperator());
        assertSame(identity, mailForm.getRecipientIdentities().iterator().next());
        assertEquals("SUBJECT", mailForm.getSubject());

    }
    
    @Test
    public void mailFormOperatorConstructor() {
        Operator operator = OperatorTestSupport.createOperator();
        MailForm mailForm = new DefaultMailForm(operator);
        assertSame(operator, mailForm.getOperator());

    }
    
    @Test
    public void validate() throws MessagingException, IOException {
        //create with organizational email
        MailForm mailForm = createMailForm();
        
        Identity recipient = IdentityTestSupport.createIdentity();
        try {
        	Set<Identity> identities = new HashSet<Identity>();
            recipient.setIdentityType(IdentityType.NOT_ADDRESSABLE.getValue());
            identities.add(recipient);
            mailForm.setRecipientIdentities(identities);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Identity is not addressable.", e.getMessage());
        }
        catch (Exception e) {
            fail();
        }
        
        //personal email is ok
    	Set<Identity> identities = new HashSet<Identity>();
        recipient.setIdentityType(IdentityType.PERSONAL_EMAIL.getValue());
        identities.add(recipient);
        mailForm.setRecipientIdentities(identities);

    }
    
    public static MailForm createMailForm() {
        Operator operator = OperatorTestSupport.createOperator();
        Identity identity = IdentityTestSupport.createIdentity();
        identity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        return createMailForm(operator, identity);
    }
    
    public static MailForm createMailForm(Operator operator, Identity identity) {
    	DefaultMailForm mailForm = new DefaultMailForm();
        mailForm.setOperator(operator);
        Set<Identity> identities = new HashSet<Identity>();
        identities.add(identity);
        mailForm.setRecipientIdentities(identities);
        mailForm.setSubject("SUBJECT");
        return mailForm;
    }
    
}
