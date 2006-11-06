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

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import junit.framework.TestCase;

import org.helianto.core.mail.MockJavaMailSender;

public class DecoratedPreparatorTests extends TestCase {

    private DecoratedPreparator decoratedPreparator;
    
    public void testInitialState() {
        assertSame(mailForm, decoratedPreparator.mailForm);
        assertTrue(decoratedPreparator.getBody() instanceof StringBuilder);
        assertEquals("", decoratedPreparator.getBody().toString());
    }
    
    public void testPrepare() throws MessagingException, IOException {
        MockJavaMailSender mockSender = new MockJavaMailSender();
        MimeMessage mimeMessage = mockSender.createMimeMessage();
        decoratedPreparator.getBody().append("BODY");
        
        decoratedPreparator.prepare(mimeMessage);
        
        assertEquals(1, mimeMessage.getRecipients(Message.RecipientType.TO).length);
        Address toAddress = mimeMessage.getRecipients(Message.RecipientType.TO)[0];
        assertEquals(mailForm.getRecipientIdentity().getPrincipal(), toAddress.toString());
        
        assertEquals(1, mimeMessage.getReplyTo().length);
        Address replyAddress = mimeMessage.getReplyTo()[0];
        assertEquals(mailForm.getOperator().getOperatorSourceMailAddress(), replyAddress.toString());
        
        assertEquals(1, mimeMessage.getFrom().length);
        Address fromAddress = mimeMessage.getFrom()[0];
        assertEquals(mailForm.getOperator().getOperatorSourceMailAddress(), fromAddress.toString());
        
        MimeMultipart part = (MimeMultipart) mimeMessage.getContent();
        assertEquals(1, part.getCount());
        MimeMultipart content = (MimeMultipart) part.getBodyPart(0).getContent();
        assertEquals(1, content.getCount());
        assertEquals("BODY", (String) content.getBodyPart(0).getContent());
    }
    
    private MailForm mailForm;
    
    @Override
    protected void setUp() throws Exception {
        mailForm = MailFormTests.createMailForm();
        decoratedPreparator = new DecoratedPreparator(mailForm);
    }

    @Override
    protected void tearDown() throws Exception {
    }

}
