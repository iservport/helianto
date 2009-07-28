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
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.helianto.message.mail.MockJavaMailSender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DecoratedPreparatorTests {

    private DecoratedPreparator decoratedPreparator;
    
    @Test
    public void initialState() {
        assertSame(mailForm, decoratedPreparator.mailForm);
        assertTrue(decoratedPreparator.getBody() instanceof StringBuilder);
        assertEquals("", decoratedPreparator.getBody().toString());
    }
    
    @Test
    public void prepare() throws MessagingException, IOException {
        MockJavaMailSender mockSender = new MockJavaMailSender();
        MimeMessage mimeMessage = mockSender.createMimeMessage();
        mailForm.setSubject("SUBJECT");
        mailForm.setContent("BODY");
        
        decoratedPreparator.prepare(mimeMessage);
        
        assertEquals(1, mimeMessage.getRecipients(Message.RecipientType.TO).length);
        Address toAddress = mimeMessage.getRecipients(Message.RecipientType.TO)[0];
        assertEquals(mailForm.getRecipientIdentities().iterator().next().getPrincipal(), toAddress.toString());
        
        assertEquals(1, mimeMessage.getReplyTo().length);
        Address replyAddress = mimeMessage.getReplyTo()[0];
        assertEquals(mailForm.getOperator().getOperatorSourceMailAddress(), replyAddress.toString());
        
        assertEquals(1, mimeMessage.getFrom().length);
        Address fromAddress = mimeMessage.getFrom()[0];
        assertEquals(mailForm.getOperator().getOperatorSourceMailAddress(), fromAddress.toString());
        
        assertEquals("SUBJECT", mimeMessage.getSubject());
        
        MimeMultipart part = (MimeMultipart) mimeMessage.getContent();
        assertEquals(1, part.getCount());
        MimeMultipart content = (MimeMultipart) part.getBodyPart(0).getContent();
        assertEquals(1, content.getCount());
        assertEquals("BODY", (String) content.getBodyPart(0).getContent());
    }
    
    private MailForm mailForm;
    
    @Before
    protected void setUp() throws Exception {
        mailForm = MailFormTests.createMailForm();
        decoratedPreparator = new DecoratedPreparator(mailForm);
    }

    @After
    protected void tearDown() throws Exception {
    }

}
