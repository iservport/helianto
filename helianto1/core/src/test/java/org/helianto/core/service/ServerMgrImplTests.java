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
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.mail.ConfigurableMailSender;
import org.helianto.core.mail.ConfigurableMailSenderFactory;
import org.helianto.core.mail.compose.DecoratedPreparator;
import org.helianto.core.mail.compose.MailMessageComposer;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.core.mail.compose.PasswordConfirmationMailMessageDecorator;
import org.helianto.core.type.OperationMode;

public class ServerMgrImplTests extends TestCase {
    
    // class under test
    private ServerMgrImpl serverMgr;
    
    public void testPersistOperator() {
        Operator operator = new Operator();
        operatorDao.persistOperator(operator);
        replay(operatorDao);
        
        serverMgr.persistOperator(operator);
        verify(operatorDao);
    }
    
    public void testFindOperatorAll() {
        List<Operator> operatorList = new ArrayList<Operator>();
        expect(operatorDao.findOperatorAll()).andReturn(operatorList);
        replay(operatorDao);
        
        assertSame(operatorList, serverMgr.findOperator());
        verify(operatorDao);
    }

    public void testCreateLocalDefaultOperator() {
        Operator operator = serverMgr.createLocalDefaultOperator();
        assertEquals("DEFAULT", operator.getOperatorName());
        assertEquals(OperationMode.LOCAL.getValue(), operator.getOperationMode());
        assertEquals(Locale.getDefault(), operator.getLocale());
    }

    /*
    public void sendPasswordConfirmation(PasswordConfirmationMailForm mailForm)
            throws MessagingException {
        List<Server> serverList = operatorDao.findServerActive(mailForm.getOperator());
        JavaMailSender sender = configurableMailSenderFactory.create(serverList);
        sender.send(mailMessageComposer.composeMessage("PASSWORD", mailForm));
    }

     */
    //TODO pending
    public void testsendPasswordConfirmation() throws MessagingException {
    	PasswordConfirmationMailForm mailForm = new PasswordConfirmationMailForm();
    	Operator operator = new Operator();
    	mailForm.setOperator(operator);
    	List<Server> serverList = new ArrayList<Server>();
        PasswordConfirmationMailMessageDecorator decoratedPreparator =
            new PasswordConfirmationMailMessageDecorator(
                    new DecoratedPreparator(mailForm));
    	
        expect(operatorDao.findServerActive(mailForm.getOperator())).andReturn(serverList);
        replay(operatorDao);

        expect(configurableMailSenderFactory.create(serverList)).andReturn(sender);
        replay(configurableMailSenderFactory);

        expect(mailMessageComposer.composeMessage("PASSWORD", mailForm)).andReturn(decoratedPreparator);
        replay(mailMessageComposer);

        sender.send(decoratedPreparator);
        replay(sender);

        serverMgr.sendPasswordConfirmation(mailForm);
    }

    // collabs
    
    private OperatorDao operatorDao;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;
    private ConfigurableMailSender sender;
    
    @Override
    public void setUp() {
        operatorDao = createMock(OperatorDao.class);
        configurableMailSenderFactory = createMock(ConfigurableMailSenderFactory.class);
        mailMessageComposer = createMock(MailMessageComposer.class);
        
        sender = createMock(ConfigurableMailSender.class);
        
        serverMgr = new ServerMgrImpl();
        serverMgr.setOperatorDao(operatorDao);
        serverMgr.setConfigurableMailSenderFactory(configurableMailSenderFactory);
        serverMgr.setMailMessageComposer(mailMessageComposer);
    }
    
    @Override
    public void tearDown() {
        reset(operatorDao);
//        reset(serviceManagementTemplate);
//        reset(systemConfigurationTemplate);
        reset(configurableMailSenderFactory);
        reset(mailMessageComposer);
        reset(sender);
    }
    
}
