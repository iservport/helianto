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


package org.helianto.message.service;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.ActivityState;
import org.helianto.core.Server;
import org.helianto.core.ServerFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.message.mail.ConfigurableMailSenderFactory;
import org.helianto.message.mail.compose.MailMessageComposer;
import org.helianto.message.mail.compose.PasswordConfirmationMailForm;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default <code>MessageMgr</code> interface implementation.
 *  
 * @author Mauricio Fernandes de Castro
 */
@Service("messageMgr")
public class MessageMgrImpl implements MessageMgr {

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm)
	    throws MessagingException {
		ServerFilter filter = new ServerFilter(ActivityState.ACTIVE.getValue());
		filter.setOperator(mailForm.getOperator());
		List<Server> serverList = (List<Server>) serverDao.find(filter);
		JavaMailSender sender = configurableMailSenderFactory.create(serverList);
		sender.send(mailMessageComposer.composeMessage("PASSWORD", mailForm));
	}
    
    // collabs

    private FilterDao<Server, ServerFilter> serverDao;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;
    
    @Resource(name="serverDao")
    public void setServerDao(FilterDao<Server, ServerFilter> serverDao) {
        this.serverDao = serverDao;
    }

    @Resource
    public void setConfigurableMailSenderFactory(
            ConfigurableMailSenderFactory configurableMailSenderFactory) {
        this.configurableMailSenderFactory = configurableMailSenderFactory;
    }

    @Resource(name="basicMailMessageComposer")
    public void setMailMessageComposer(MailMessageComposer mailMessageComposer) {
        this.mailMessageComposer = mailMessageComposer;
    }

    private final Logger logger = LoggerFactory.getLogger(MessageMgrImpl.class);

}
