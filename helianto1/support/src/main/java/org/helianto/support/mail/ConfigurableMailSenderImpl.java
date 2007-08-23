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

package org.helianto.support.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.ActivityState;
import org.helianto.support.Server;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Subclass of <code>JavaMailSenderImpl</code> implementing
 * <code>ConfigurableMailSender</code> interface to allow
 * configuration via the <code>Server</code> abstraction.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ConfigurableMailSenderImpl extends JavaMailSenderImpl implements
        ConfigurableMailSender, JavaMailSender {
    
    private Server transportServer;
    private Server accessServer;

    /**
     * Cares for proper <code>JavaMailSenderImpl</code> initialization.
     */
    public void init() throws IllegalStateException {
        setHost(transportServer.getServerHostAddress());
        logger.info("Host set to "+transportServer.getServerHostAddress());
        setUsername(transportServer.getCredential().getIdentity()
                .getPrincipal());
        logger.info("Username set to "+transportServer.getCredential().getIdentity()
                .getPrincipal());
        setPassword(transportServer.getCredential().getPassword());
        logger.info("Password set to [protected]");
        setPort(transportServer.getServerPort());
        logger.info("Port set to "+transportServer.getServerPort());
        setDefaultEncoding(transportServer.getOperator().getDefaultEncoding());
        logger.info("DefaultEncoding set to "+transportServer.getOperator().getDefaultEncoding());
    }

    /**
     * A valid <code>Server</code> has a <code>Credential</code> 
     * that is <code>ActivityState.ACTIVE</code> and has the same 
     * <code>Encription</code> as required by the server.
     */
    protected boolean validate(Server server) {
        boolean valid = (server!=null 
                && server.getCredential().getCredentialState() == ActivityState.ACTIVE.getValue()
                && server.getCredential().getEncription() == server.getRequiredEncription());
        if (logger.isDebugEnabled()) {
            String notValid = valid ? "" :" not";
            logger.debug("Server "+server+" is "+notValid+"valid.");
        }
        return valid;
    }
    
    
    @Override
    public void send(MimeMessagePreparator[] mimeMessagePreparator) throws MailException {
        if (logger.isDebugEnabled()) {
            logger.debug("Sending ...");
        }
        super.send(mimeMessagePreparator);
        if (logger.isDebugEnabled()) {
            logger.debug("Sent!");
        }
    }

    public void setAccessServer(Server accessServer) {
        this.accessServer = accessServer;
    }

    public void setTransportServer(Server transportServer) {
        if (!validate(transportServer)) {
            throw new IllegalArgumentException("Server is not valid.");
        }
        this.transportServer = transportServer;
    }

    public Server getAccessServer() {
        return accessServer;
    }

    public static final Log logger = LogFactory.getLog(ConfigurableMailSenderImpl.class);

}
