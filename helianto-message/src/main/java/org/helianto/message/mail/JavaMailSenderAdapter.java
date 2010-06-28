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

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.ActivityState;
import org.helianto.core.Server;
import org.helianto.core.ServerType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;


/**
 * A <code>JavaMailSenderImpl</code> adpater that
 * reads configuration from a <code>List&lt;Server&gt; serverList</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated use <code>ConfigurableMailSender</code>
 */
public class JavaMailSenderAdapter {
    
    private JavaMailSenderImpl javaMailSender;
    
    public JavaMailSenderAdapter () {
        javaMailSender = new JavaMailSenderImpl();
    }
    
    private List<Server> serverList;
    
    private Store store = null;
    
    public void setServerList(List<Server> serverList) {
        this.serverList = serverList;
        init();
    }

    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
        init();
    }

    private void init() throws IllegalStateException {
        Server transportServer = serverUtilsTemplate.selectFirstAvailableMailTransportServer(serverList);
        javaMailSender.setHost(transportServer.getServerHostAddress());
        javaMailSender.setUsername(transportServer.getCredential().getIdentity()
                .getPrincipal());
        javaMailSender.setPassword(transportServer.getCredential().getPassword());
        javaMailSender.setDefaultEncoding(transportServer.getOperator().getDefaultEncoding());
    }
    
    /**
     * Wraps javaMailSender.send with pre and post process hooks.
     * 
     * @param preparator
     */
    public void send(MimeMessagePreparator preparator) {
        sendPreProcess();
        try {
            javaMailSender.send(preparator);
        } finally {
            sendPostProcess();
        }
    }
    
    /**
     * Wraps javaMailSender.send with pre and post process hooks.
     * 
     * @param mimeMessage
     */
    public void send(MimeMessage mimeMessage) {
        sendPreProcess();
        try {
            javaMailSender.send(mimeMessage);
        } finally {
            sendPostProcess();
        }
    }
    
    /**
     * A hook to provide sending pre-process. Default implementation
     * tries to connect to a POP store. This is a required procedure 
     * whith some MTA to prevent unauthorized mail relay.
     *
     */
    protected void sendPreProcess() {
        try {
            if (store==null) {
                store = javaMailSender.getSession().getStore("POP3");
            }
        } catch (NoSuchProviderException e) {
            logger.warn("Unable to create a store within this mail session!", e);
        }
        try {
            Server accessServer = serverUtilsTemplate.selectFirstAvailableMailAccessServer(serverList);
            if (accessServer==null) {
                logger.warn("Unable to connect to store before sending a message: access server not available.");
            }
            else {
                String host = accessServer.getServerHostAddress();
                String username = accessServer.getCredential().getIdentity()
                        .getPrincipal();
                String password = accessServer.getCredential().getPassword();
                store.connect(host, username, password);
            }
        } catch (Exception e) {
            logger.warn("Exception ignored: ", e);
        }
    }
    
    /**
     * A hook to provide sending post-process. Default implementation
     * tries to close the connection to a POP store.
     *
     */
    protected void sendPostProcess() {
        if (store==null) return;
        try {
            store.close();
        } catch (MessagingException e) {
            logger.warn("Unable to close store.");
        }
    }
    
    private ServerUtilsTemplate serverUtilsTemplate = new DefaultServerUtils();

    public ServerUtilsTemplate getServerUtilsTemplate() {
        return serverUtilsTemplate;
    }

    public void setServerUtilsTemplate(ServerUtilsTemplate serverUtilsTemplate) {
        this.serverUtilsTemplate = serverUtilsTemplate;
    }
    
    /**
     * Default implementation for <code>ServerUtilsTemplate</code>.
     * 
     * @author Mauricio Fernandes de Castro
     */
    public class DefaultServerUtils implements ServerUtilsTemplate {
        
        public Server selectFirstAvailableMailTransportServer(List<Server> serverList) {
            return getValidServer(serverList, ServerType.SMTP_SERVER);
        }
        
        public Server selectFirstAvailableMailAccessServer(List<Server> serverList) {
            return getValidServer(serverList, ServerType.POP3_SERVER);
        }
        
        protected Server getValidServer(List<Server> serverList, ServerType serverType) {
            for (Server s : serverList) {
                if (validate(s) && s.getServerType() == serverType.getValue()) {
                    return s;
                }
            }
            throw new IllegalStateException(
                    "Not able to find a valid server from list " + serverList);
        }

        /**
         * A valid <code>Server</code> has a <code>Credential</code> that is
         * <code>ActivityState.ACTIVE</code> and has the same <code>Encription</code> as
         * required by the server.
         */
        protected boolean validate(Server server) {
            return (server.getCredential().getCredentialState() == ActivityState.ACTIVE.getValue()
                    && server.getCredential().getEncription() == server.getRequiredEncription());
        }
        
    }
    
    public static final Logger logger = LoggerFactory.getLogger(JavaMailSenderAdapter.class);

    public JavaMailSenderImpl getJavaMailSender() {
        return javaMailSender;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}


