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

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.helianto.core.Credential;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.dao.AuthenticationDao;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.mail.MailComposer;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.IdentityType;
import org.helianto.core.type.OperationMode;
import org.helianto.core.type.ServerType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class ServerMgrImpl extends AbstractServerMgr {

    protected OperatorDao operatorDao;

    protected AuthenticationDao authenticationDao;

    private MailComposer mailComposer;
    
    public void persistOperator(Operator operator) {
        operatorDao.persistOperator(operator);
    }

    public List<Operator> findOperator() {
        List<Operator> operatorList = operatorDao.findOperatorAll();
        return operatorList;
    }

    public Operator createLocalDefaultOperator() {
        Operator operator = OperatorCreator.operatorFactory("DEFAULT",
                OperationMode.LOCAL, Locale.getDefault());
        return operator;
    }

    public void sendRegistrationNotification(Operator operator, Credential cred)
            throws MessagingException {
        if (cred.getIdentity().getIdentityType() == IdentityType.NOT_ADDRESSABLE
                .getValue()) {
            throw new IllegalStateException("Credential is not addressable.");
        }

        Server transportServer = 
            getValidServer(operator,
                ServerType.SMTP_SERVER);

        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) createSender(transportServer);
        MimeMessageHelper helper = createMimeHelper(javaMailSender, transportServer.getCredential()
                .getIdentity().getPrincipal(), cred.getIdentity().getPrincipal());

        helper.setSubject(mailComposer
                .composeRegistrationNotificationSubject(""));
        helper.setText(mailComposer.composeRegistrationNotification(cred,
                operator.getOperatorHostAddress()), true);
        
        senderStrategy.send(operator, javaMailSender, helper);
        
    }
    
    private SenderStrategy senderStrategy = new DefaultSender(); 
    
    public class DefaultSender implements SenderStrategy {
        
        public void send(Operator operator, JavaMailSenderImpl javaMailSender, MimeMessageHelper helper) throws MessagingException {
            Store store = null;
            try {
                store = connectBeforeSend(javaMailSender.getSession(), getValidServer(operator, 
                        ServerType.POP3_SERVER));
                javaMailSender.send(helper.getMimeMessage());
            } finally {
                if (store != null) {
                    store.close();
                }
            }
        }
        
        public Store connectBeforeSend(Session mailSession, Server accessServer) {
            String storeType = "";
            if (accessServer.getServerType() == ServerType.POP3_SERVER.getValue()) {
                storeType = "POP3";
            }
            Store store = null;
            String host = accessServer.getServerHostAddress();
            String username = accessServer.getCredential().getIdentity()
                    .getPrincipal();
            String password = accessServer.getCredential().getPassword();
            try {
                store = mailSession.getStore(storeType);
                store.connect(host, username, password);
            } catch (Exception e) {
                throw new IllegalStateException("Unable to connect to "
                        + accessServer + " before sending message", e);
            }
            return store;
        }

    }
    
    public MimeMessageHelper createMimeHelper(JavaMailSenderImpl javaMailSender, String from, String to) 
        throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "ISO-8859-1");
        helper.setTo(to);
        helper.setReplyTo(from);
        helper.setFrom(from);
        helper.setSentDate(new Date());
        return helper;
    }

    public JavaMailSender createSender(Server transportServer) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(transportServer.getServerHostAddress());
        javaMailSender.setUsername(transportServer.getCredential()
                .getIdentity().getPrincipal());
        javaMailSender.setPassword(transportServer.getCredential()
                .getPassword());
        return javaMailSender;
    }

    public Server getValidServer(Operator operator, ServerType serverType) {
        List<Server> orderedServerList = operatorDao.findServerActiveByType(
                operator, serverType);
        for (Server s : orderedServerList) {
            Credential credential = s.getCredential();
            if (credential.getCredentialState() == ActivityState.ACTIVE
                    .getValue()
                    && credential.getEncription() == s.getRequiredEncription()) {
                return s;
            }
        }
        throw new IllegalStateException(
                "Not able to find a valid server to operator " + operator);
    }

    //

    public void init() {
        if (operatorDao == null)
            throw new IllegalArgumentException("OperatorDao property required");
    }

    // mutators

    public void setMailComposer(MailComposer mailComposer) {
        this.mailComposer = mailComposer;
    }

    public void setOperatorDao(OperatorDao operatorDao) {
        this.operatorDao = operatorDao;
    }
    
}
