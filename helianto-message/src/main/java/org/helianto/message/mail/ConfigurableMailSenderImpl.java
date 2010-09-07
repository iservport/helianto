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

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.ActivityState;
import org.helianto.core.Server;
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
 * @deprecated
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
            logger.debug("Try to connect to store");
        }
        tryToConnectToPopBeforeSmtp();
        if (logger.isDebugEnabled()) {
            logger.debug("Sending ...");
        }
        super.send(mimeMessagePreparator);
        if (logger.isDebugEnabled()) {
            logger.debug("Sent!");
        }
    }
    
    /**
     * A hook to provide sending pre-process. Default implementation
     * tries to connect to a POP store. This is a required procedure 
     * whith some MTA to prevent unauthorized mail relay.
     *
     */
    protected void tryToConnectToPopBeforeSmtp() {
    	Store store = null;
        try {
                store = getSession().getStore("pop3");
        } catch (NoSuchProviderException e) {
            logger.warn("Unable to create a store within this mail session!", e);
        }
        try {
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
    
    protected void tryToClosePop() {
		try {
			Store store = getSession().getStore("POP3");
			store.close();
		} catch (NoSuchProviderException e) {
			logger.warn("Unable to retrieve a POP3 store within this mail session!", e);
		} catch (MessagingException e) {
			logger.warn("Unable to close the POP3 store within this mail session!", e);
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

    public static final Logger logger = LoggerFactory.getLogger(ConfigurableMailSenderImpl.class);

}
