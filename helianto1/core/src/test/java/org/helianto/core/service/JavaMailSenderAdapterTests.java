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

import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

import org.helianto.core.ActivityState;
import org.helianto.core.Encription;
import org.helianto.core.Server;
import org.helianto.core.ServerType;
import org.helianto.core.mail.JavaMailSenderAdapter;
import org.helianto.core.mail.MockJavaMailSender;
import org.helianto.core.mail.ServerUtilsTemplate;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.ServerTestSupport;

public class JavaMailSenderAdapterTests extends TestCase {
    
    private JavaMailSenderAdapter javaMailSenderAdapter;
    
    public void testSetServerList() {
        serverList = ServerTestSupport.createServerList(2, 1);
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
    
    public void testSendMimeMessage() throws AddressException, MessagingException {
        MockJavaMailSender mockSender = new MockJavaMailSender();
        
        serverList = ServerTestSupport.createServerList(2, 1);
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
    
    public void testServerUtilsTemplate() {
        ServerUtilsTemplate utils = javaMailSenderAdapter.new DefaultServerUtils();
        serverList = ServerTestSupport.createServerList(2, 1);
        Server transportServer = serverList.get(0);
        transportServer.setServerType(ServerType.SMTP_SERVER.getValue());
        transportServer.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        Server accessServer = serverList.get(1);
        accessServer.setServerType(ServerType.POP3_SERVER.getValue());
        accessServer.setRequiredEncription(Encription.PLAIN_PASSWORD.getValue());
        
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
    
    private static class MockStore extends Store {
        
        boolean connected = false;
        
        public MockStore() {
            super(Session.getDefaultInstance(new Properties()), new URLName(""));
        }
        
        @Override
        public void connect(String host, String user, String password) throws MessagingException {
            assertEquals("STORE_ADDRESS", host);
            assertEquals("STORE_USER".toLowerCase(), user);
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
