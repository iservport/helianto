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

package org.helianto.message.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Thanks to Juergen Hoeller to provide this mocks 
 * inside Spring code.
 * 
 * @see org.springframework.mail.javamail.JavaMailSenderTests
 */
public class MockJavaMailSender extends JavaMailSenderImpl {

    public MockTransport transport;
    
    protected Transport getTransport(Session session) throws NoSuchProviderException {
        this.transport = new MockTransport(session, null);
        return transport;
    }

    public static class MockTransport extends Transport {

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
//            if (message.getSubject() != null && message.getSubject().indexOf("custom") != -1) {
//                assertEquals(new Date(2005, 3, 1), message.getSentDate());
//            }
            this.sentMessages.add(message);
        }
    }

}
