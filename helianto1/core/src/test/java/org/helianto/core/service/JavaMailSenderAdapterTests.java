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

package org.helianto.core.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.Server;
import org.helianto.core.mail.JavaMailSenderAdapter;
import org.helianto.core.mail.ServerUtilsTemplate;
import org.helianto.core.test.AuthenticationTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.Encription;
import org.helianto.core.type.IdentityType;
import org.helianto.core.type.ServerType;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSenderAdapterTests extends TestCase {
    
    private JavaMailSenderAdapter javaMailSenderAdapter;
    
    public void testSetServerList() {
        serverList = OperatorTestSupport.createServerList(2, 1);
        Server transportServer = serverList.get(0);
        transportServer.setServerHostAddress("HOST_ADDRESS");
        
        expect(serverUtilsTemplate.selectFirstAvailableMailTransportServer(serverList))
            .andReturn(transportServer);
        replay(serverUtilsTemplate);
        
        javaMailSenderAdapter.setServerList(serverList);
        verify(serverUtilsTemplate);
        assertEquals("HOST_ADDRESS", javaMailSenderAdapter.getJavaMailSender().getHost());
        assertEquals(transportServer.getCredential().getIdentity()
                .getPrincipal(), javaMailSenderAdapter.getJavaMailSender().getUsername());
        assertEquals(transportServer.getCredential().getPassword(), 
                javaMailSenderAdapter.getJavaMailSender().getPassword());
        
    }
    
    public void testSendPlain() throws AddressException, MessagingException {
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
        
        MimeMessage mimeMessage = mockSender.createMimeMessage();
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("you@mail.org"));
        Store store = new MockStore();
        javaMailSenderAdapter.setStore(store);
        javaMailSenderAdapter.send(mimeMessage);
        
        assertFalse(store.isConnected());
        
    }
    
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
    
    public void testServerUtilsTemplate() {
        ServerUtilsTemplate utils = javaMailSenderAdapter.new DefaultServerUtils();
        serverList = OperatorTestSupport.createServerList(2, 1);
        Server transportServer = serverList.get(0);
        transportServer.setServerType(ServerType.SMTP_SERVER.getValue());
        transportServer.setRequiredEncription((byte) 64);
        Server accessServer = serverList.get(1);
        accessServer.setServerType(ServerType.POP3_SERVER.getValue());
        accessServer.setRequiredEncription((byte) 64);
        
        try {
            utils.selectFirstAvailableMailTransportServer(serverList);
        } catch (IllegalStateException e) {
            //expected, invalid server; fix it and continue
            assertNotNull(e);
            transportServer.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
            transportServer.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        } catch (Exception e) {
            fail();
        }
        assertSame(transportServer, utils.selectFirstAvailableMailTransportServer(serverList));
        try {
            utils.selectFirstAvailableMailAccessServer(serverList);
        } catch (IllegalStateException e) {
            //expected, invalid server; fix it and continue
            assertNotNull(e);
            accessServer.getCredential().setCredentialState(ActivityState.ACTIVE.getValue());
            accessServer.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        } catch (Exception e) {
            fail();
        }
        assertSame(accessServer, utils.selectFirstAvailableMailAccessServer(serverList));
    }
    
    private ServerUtilsTemplate serverUtilsTemplate;
    private List<Server> serverList;

    @Override
    public void setUp() {
        serverUtilsTemplate = createMock(ServerUtilsTemplate.class);
        javaMailSenderAdapter = new JavaMailSenderAdapter();
        javaMailSenderAdapter.setServerUtilsTemplate(serverUtilsTemplate);
    }
    
    public void tearDown() {
        reset(serverUtilsTemplate);
    }
    
    /*
     * Thanks to Juergen Hoeller to provide this mocks 
     * inside Spring code (org.springframework.mail.javamail.JavaMailSenderTests)
     */
    private static class MockJavaMailSender extends JavaMailSenderImpl {

        private MockTransport transport;
        
        protected Transport getTransport(Session session) throws NoSuchProviderException {
            this.transport = new MockTransport(session, null);
            return transport;
        }

    }

    private static class MockTransport extends Transport {

        private String connectedHost = null;
        private int connectedPort = -2;
        private String connectedUsername = null;
        private String connectedPassword = null;
        private boolean closeCalled = false;
        private List sentMessages = new ArrayList();

        public MockTransport(Session session, URLName urlName) {
            super(session, urlName);
        }

        public String getConnectedHost() {
            return connectedHost;
        }

        public int getConnectedPort() {
            return connectedPort;
        }

        public String getConnectedUsername() {
            return connectedUsername;
        }

        public String getConnectedPassword() {
            return connectedPassword;
        }

        public boolean isCloseCalled() {
            return closeCalled;
        }

        public List getSentMessages() {
            return sentMessages;
        }

        public MimeMessage getSentMessage(int index) {
            return (MimeMessage) this.sentMessages.get(index);
        }

        public void connect(String host, int port, String username, String password) throws MessagingException {
            if (host == null) {
                throw new MessagingException("no host");
            }
            this.connectedHost = host;
            this.connectedPort = port;
            this.connectedUsername = username;
            this.connectedPassword = password;
        }

        public synchronized void close() throws MessagingException {
            this.closeCalled = true;
        }
        
        @SuppressWarnings({ "deprecation", "unchecked" })
        public void sendMessage(Message message, Address[] addresses) throws MessagingException {
            if ("fail".equals(message.getSubject())) {
                throw new MessagingException("failed");
            }
            List addr1 = Arrays.asList(message.getAllRecipients());
            List addr2 = Arrays.asList(addresses);
            if (!addr1.equals(addr2)) {
                throw new MessagingException("addresses not correct");
            }
            if (message.getSentDate() == null) {
                throw new MessagingException("No sentDate specified");
            }
            if (message.getSubject() != null && message.getSubject().indexOf("custom") != -1) {
                assertEquals(new Date(2005, 3, 1), message.getSentDate());
            }
            this.sentMessages.add(message);
        }
    }
    
    private static class MockStore extends Store {
        
        boolean connected = false;
        
        public MockStore() {
            super(Session.getDefaultInstance(new Properties()), new URLName(""));
        }
        
        @Override
        public void connect(String host, String user, String password) throws MessagingException {
            assertEquals("STORE_ADDRESS", host);
            assertEquals("STORE_USER", user);
            assertEquals("STORE_PASSWORD", password);
            connected = true;
        }
        
        @Override
        public void close() {
            connected = false;
        }

        @Override
        public Folder getDefaultFolder() throws MessagingException {
            return null;
        }

        @Override
        public Folder getFolder(String arg0) throws MessagingException {
            return null;
        }

        @Override
        public Folder getFolder(URLName arg0) throws MessagingException {
            return null;
        }
        
    }


}
