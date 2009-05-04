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

package org.helianto.support.service;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Operator;
import org.helianto.support.Server;
import org.helianto.support.ServerType;
import org.helianto.support.mail.ConfigurableMailSender;
import org.helianto.support.mail.ConfigurableMailSenderImpl;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.support.dao.ServerDao;
import org.springframework.beans.factory.annotation.Required;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SupportMgrImpl implements SupportMgr {

    private ServerDao serverDao;

    public ConfigurableMailSender createConfigurableMailSender(Operator operator) {
        ConfigurableMailSender configurableMailSender = new ConfigurableMailSenderImpl();
        configurableMailSender.setTransportServer(selectFirstAvailableServer(operator, ServerType.SMTP_SERVER));
        configurableMailSender.setAccessServer(selectFirstAvailableServer(operator, ServerType.POP3_SERVER));
        configurableMailSender.init();
        return configurableMailSender;
    }
    
    public Server selectFirstAvailableServer(Operator operator, ServerType serverType) {
        List<Server> transportServerList = serverDao.findServerByOperatorAndType(operator, serverType);
        if (transportServerList.size() > 0) {
            return transportServerList.get(0);
        }
        throw new IllegalArgumentException("Unable to find a valid " +serverType+" server to operator "+operator);
    }

    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm) throws MessagingException {
        // TODO Auto-generated method stub
        
    }

    @Required
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }
    
    public static final Log logger = LogFactory.getLog(SupportMgrImpl.class);

}
