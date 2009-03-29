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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.dao.ServerDao;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.filter.IdentityFilter;
import org.helianto.core.mail.ConfigurableMailSender;
import org.helianto.core.mail.ConfigurableMailSenderFactory;
import org.helianto.core.mail.compose.DecoratedPreparator;
import org.helianto.core.mail.compose.MailMessageComposer;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.core.mail.compose.PasswordConfirmationMailMessageDecorator;

public class ServerMgrImplTests extends TestCase {
    
    // class under test
    private ServerMgrImpl serverMgr;
    
    //TODO pending
    public void testsendPasswordConfirmation() throws MessagingException {
    	PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm();
    	Operator operator = new Operator();
    	mailForm.setOperator(operator);
    	List<Server> serverList = new ArrayList<Server>();
        PasswordConfirmationMailMessageDecorator decoratedPreparator =
            new PasswordConfirmationMailMessageDecorator(
                    new DecoratedPreparator(mailForm));
    	
        expect(serverDao.findServerActive(mailForm.getOperator())).andReturn(serverList);
        replay(serverDao);

        expect(configurableMailSenderFactory.create(serverList)).andReturn(sender);
        replay(configurableMailSenderFactory);

        expect(mailMessageComposer.composeMessage("PASSWORD", mailForm)).andReturn(decoratedPreparator);
        replay(mailMessageComposer);

        sender.send(decoratedPreparator);
        replay(sender);

        serverMgr.sendPasswordConfirmation(mailForm);
    }

    // collabs
    
	private FilterDao<Identity, IdentityFilter> identityDao;
	private FilterDao<UserGroup, UserFilter> userGroupDao;
    private ServerDao serverDao;
    private ServiceDao serviceDao;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;
    private ConfigurableMailSender sender;
    
    @SuppressWarnings("unchecked")
	@Override
    public void setUp() {
		identityDao = createMock(FilterDao.class);
		userGroupDao = createMock(FilterDao.class);
        serverDao = createMock(ServerDao.class);
        serviceDao = createMock(ServiceDao.class);
        configurableMailSenderFactory = createMock(ConfigurableMailSenderFactory.class);
        mailMessageComposer = createMock(MailMessageComposer.class);
        
        sender = createMock(ConfigurableMailSender.class);
        
        serverMgr = new ServerMgrImpl();
		serverMgr.setIdentityDao(identityDao);
		serverMgr.setUserGroupDao(userGroupDao);
        serverMgr.setServerDao(serverDao);
        serverMgr.setServiceDao(serviceDao);
        serverMgr.setConfigurableMailSenderFactory(configurableMailSenderFactory);
        serverMgr.setMailMessageComposer(mailMessageComposer);
    }
    
    @Override
    public void tearDown() {
		reset(identityDao);
		reset(userGroupDao);
        reset(serverDao);
        reset(serviceDao);
        reset(configurableMailSenderFactory);
        reset(mailMessageComposer);
        reset(sender);
    }
    
}
