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
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Operator;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;

public class MailFormTests extends TestCase {
    
    public void testMailForm() {
        Operator operator = OperatorTestSupport.createOperator();
        Identity identity = AuthenticationTestSupport.createIdentity();
        identity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        MailForm mailForm = createMailForm(operator, identity);
        assertSame(operator, mailForm.getOperator());
        assertSame(identity, mailForm.getRecipientIdentities().iterator().next());
        assertEquals("SUBJECT", mailForm.getSubject());

    }
    
    public void testMailFormOperatorConstructor() {
        Operator operator = OperatorTestSupport.createOperator();
        MailForm mailForm = new DefaultMailForm(operator);
        assertSame(operator, mailForm.getOperator());

    }
    
    public void testValidate() throws MessagingException, IOException {
        //create with organizational email
        MailForm mailForm = createMailForm();
        
        Identity recipient = AuthenticationTestSupport.createIdentity();
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
        
        try {
        	Set<Identity> identities = new HashSet<Identity>();
            recipient.setIdentityType(IdentityType.GROUP.getValue());
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

        //but the getter is also validated
        try {
            recipient.setIdentityType(IdentityType.GROUP.getValue());
            mailForm.getRecipientIdentities();
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
    	DefaultMailForm mailForm = new DefaultMailForm();
        mailForm.setOperator(operator);
        Set<Identity> identities = new HashSet<Identity>();
        identities.add(identity);
        mailForm.setRecipientIdentities(identities);
        mailForm.setSubject("SUBJECT");
        return mailForm;
    }
    
}
