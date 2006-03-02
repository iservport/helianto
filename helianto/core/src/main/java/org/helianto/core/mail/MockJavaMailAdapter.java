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

public class MockJavaMailAdapter extends JavaMailAdapterImpl implements JavaMailAdapter {
    
    @Override
    public Store popStoreConnect(Supervisor supervisor) {
        if (supervisor==null) {
            throw new IllegalStateException("Can't create a Store from a null Owner");
        }
        logger.info("\n         Simulate a store connection with" +
                " storeHost="+supervisor.getStoreHost()+
                ", storeUser="+supervisor.getStoreUser()+
                " and prtected password");
        return null;
    }

    @Override
    public void send(Supervisor supervisor, MimeMessage message) throws MailException {
        popStoreConnect(supervisor);
        logger.info("\n         Simulate sending a message with" +
                " mailHost="+supervisor.getMailHost()+
                ", mailUser="+supervisor.getMailUser()+
                " and prtected password");
        try {
            logger.info("\n         Message subject is " +
                    message.getSubject());
        } catch (Exception e) {
            logger.info("\n         Can't retrieve message subject ");
        }
    }

}
