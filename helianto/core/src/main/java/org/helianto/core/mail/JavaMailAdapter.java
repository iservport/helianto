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

package org.helianto.core.mail;

import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.helianto.core.Supervisor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * An adapter to provide initialization data to the 
 * <code>JavaMailSender</code> through an <code>Supervisor</code>
 * instance.
 *  
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface JavaMailAdapter extends JavaMailSender {

    /**
     * @return A <code>MailComposer</code>.
     */
    public MailComposer getMailComposer();

    /**
     * @param mailComposer The <code>MailComposer</code> to set.
     */
    public void setMailComposer(MailComposer mailComposer);
    
    /**
     * To avoid error 553, the service must first connect to
     * a store and then send the desired message.
     */
    public Store popStoreConnect(Supervisor supervisor);
    
    /**
     * Method to send a message using the provided <code>Supervisor</code>  
     * configuration.
     */
    public void send(Supervisor supervisor, MimeMessage message) throws MailException;

}