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

import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.helianto.core.Credential;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.mail.JavaMailSenderAdapter;
import org.helianto.core.mail.MailComposer;
import org.helianto.core.type.OperationMode;

public class ServerMgrImpl extends AbstractServerMgr {

    protected OperatorDao operatorDao;

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

    public void sendRegistrationNotification(Operator operator, Credential credential)
            throws MessagingException {

        List<Server> serverList = operatorDao.findServerActive(operator);

        JavaMailSenderAdapter sender = new JavaMailSenderAdapter();
        StringBuilder body = new StringBuilder();
        body.append("test");
//        helper.setSubject(mailComposer
//                .composeRegistrationNotificationSubject(""));
//        helper.setText(mailComposer.composeRegistrationNotification(cred,
//                operator.getOperatorHostAddress()), true);
//        sender.send(null, credential.getIdentity(), null, body);
        
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
