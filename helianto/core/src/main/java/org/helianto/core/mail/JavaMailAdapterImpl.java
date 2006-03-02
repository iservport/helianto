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

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.helianto.core.Supervisor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailAdapterImpl extends JavaMailSenderImpl implements JavaMailAdapter {
    
    private MailComposer mailComposer;
    
    public MailComposer getMailComposer() {
        return mailComposer;
    }

    public void setMailComposer(MailComposer mailComposer) {
        this.mailComposer = mailComposer;
    }

    public Store popStoreConnect(Supervisor supervisor) {
        if (supervisor==null) {
            throw new IllegalStateException("Can't create a Store from a null Owner");
        }
        Store store = null;
        try {
            store = this.getSession().getStore(supervisor.getStoreType());
            store.connect(supervisor.getStoreHost(), supervisor.getStoreUser(), supervisor.getStorePassword());
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return store;
    }

    public void send(Supervisor supervisor, MimeMessage message) throws MailException {
        Store store = popStoreConnect(supervisor);
        setHost(supervisor.getMailHost());
        setUsername(supervisor.getMailUser());
        setPassword(supervisor.getMailPassword());
        super.send(new MimeMessage[] { message });
        try {
            store.close();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void send(MimeMessage[] arg0) throws MailException {
        throw new IllegalAccessError("Use adapter method to provide also an owner");
    }


    @Override
    public void send(SimpleMailMessage[] arg0) throws MailException {
        throw new IllegalAccessError("Use adapter method to provide also an owner");
    }

}
