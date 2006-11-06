import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.helianto.core.Identity;
import org.helianto.core.Server;
import org.helianto.core.mail.MockJavaMailSender;
import org.helianto.core.service.JavaMailSenderAdapterTests.MockStore;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.type.IdentityType;

    public void testSendCustom() throws AddressException, MessagingException, IOException {
        MockJavaMailSender mockSender = new MockJavaMailSender();
        
        serverList = OperatorTestSupport.createServerList(2, 1);
        Server transportServer = serverList.get(0);
        transportServer.setServerHostAddress("HOST_ADDRESS");
        
        expect(serverUtilsTemplate.selectFirstAvailableMailTransportServer(serverList))
            .andReturn(transportServer).times(2);
        replay(serverUtilsTemplate);
        
        javaMailSenderAdapter.setServerList(serverList);
        javaMailSenderAdapter.setJavaMailSender(mockSender);
        verify(serverUtilsTemplate);
        
        assertEquals("HOST_ADDRESS", javaMailSenderAdapter.getJavaMailSender().getHost());
        assertEquals(transportServer.getCredential().getIdentity()
                .getPrincipal(), javaMailSenderAdapter.getJavaMailSender().getUsername());
        assertEquals(transportServer.getCredential().getPassword(), 
                javaMailSenderAdapter.getJavaMailSender().getPassword());
        
        Server accessServer = serverList.get(1);
        accessServer.setServerHostAddress("STORE_ADDRESS");
        accessServer.getCredential().getIdentity().setPrincipal("STORE_USER");
        accessServer.getCredential().setPassword("STORE_PASSWORD");
        
        reset(serverUtilsTemplate);
        expect(serverUtilsTemplate.selectFirstAvailableMailAccessServer(serverList))
            .andReturn(accessServer);
        replay(serverUtilsTemplate);
        
        Store store = new MockStore();
        javaMailSenderAdapter.setStore(store);
        Identity senderIdentity = AuthenticationTestSupport.createIdentity();
        Identity recipientIdentity = AuthenticationTestSupport.createIdentity();
        StringBuilder body = new StringBuilder();
        body.append("BODY");
        
        try {
            javaMailSenderAdapter.send(senderIdentity, recipientIdentity, body);
            fail();
        } catch (IllegalArgumentException e) {
            //expected, now fix it to continue bellow
            senderIdentity.setIdentityType(IdentityType.ORGANIZATIONAL_EMAIL.getValue());
        } catch (Exception e) {
            fail();
        }
        try {
            javaMailSenderAdapter.send(senderIdentity, recipientIdentity, body);
            fail();
        } catch (IllegalArgumentException e) {
            //expected, now fix it to continue bellow
            recipientIdentity.setIdentityType(IdentityType.PERSONAL_EMAIL.getValue());
        } catch (Exception e) {
            fail();
        }
        javaMailSenderAdapter.send(senderIdentity, recipientIdentity, body);
        
        MimeMessage mimeMessage = mockSender.transport.getSentMessage(0);//mockSender.getMimeMessage();
        
        
        assertEquals(1, mimeMessage.getRecipients(Message.RecipientType.TO).length);
        Address to = mimeMessage.getRecipients(Message.RecipientType.TO)[0];
        assertEquals(recipientIdentity.getPrincipal(), to.toString());
        
        assertEquals(1, mimeMessage.getReplyTo().length);
        Address reply = mimeMessage.getReplyTo()[0];
        assertEquals(senderIdentity.getPrincipal(), reply.toString());
        
        assertEquals(1, mimeMessage.getFrom().length);
        Address from = mimeMessage.getFrom()[0];
        assertEquals(senderIdentity.getPrincipal(), from.toString());
        
        MimeMultipart part = (MimeMultipart) mimeMessage.getContent();
        assertEquals(1, part.getCount());
        MimeMultipart content = (MimeMultipart) part.getBodyPart(0).getContent();
        assertEquals(1, content.getCount());
        assertEquals("BODY", (String) content.getBodyPart(0).getContent());
        
        assertFalse(store.isConnected());
        
    }
    
