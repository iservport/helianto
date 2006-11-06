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
import org.helianto.core.mail.ConfigurableMailSenderImpl;
import org.helianto.core.mail.MailMessageComposer;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.core.type.OperationMode;

public class ServerMgrImplTests extends TestCase {
    
    // class under test
    private ServerMgrImpl serverMgr;
    
    //
    
    public void testCreateSystemConfiguration() {
        Identity managerIdentity = new Identity();
        Entity defaultEntity = new Entity();
        UserRole adminManagerRole = new UserRole();
        User manager = new User();
        
        expect(systemConfigurationTemplate
                .createDefaultEntity()).andReturn(defaultEntity);
        expect(serviceManagementTemplate
                .createManagerRole(defaultEntity, "ADMIN")).andReturn(adminManagerRole);
        expect(systemConfigurationTemplate.createManager(
                adminManagerRole, managerIdentity)).andReturn(manager);
        replay(systemConfigurationTemplate);
        replay(serviceManagementTemplate);
        
        assertSame(manager, serverMgr.createSystemConfiguration(managerIdentity));
        verify(systemConfigurationTemplate);
        verify(serviceManagementTemplate);
    }

    
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
    	
//        Server transportServer = OperatorTestSupport.createServer();
//        Server accessServer = OperatorTestSupport.createServer(operator);
        
        ConfigurableMailSender sender = new ConfigurableMailSenderImpl();
        
        expect(operatorDao.findServerActive(mailForm.getOperator())).andReturn(serverList);
        replay(operatorDao);

        expect(configurableMailSenderFactory.create(serverList)).andReturn(sender);
        replay(operatorDao);

        serverMgr.sendPasswordConfirmation(mailForm);
    }

    // collabs
    
    private OperatorDao operatorDao;
    private ServiceManagementTemplate serviceManagementTemplate;
    private SystemConfigurationTemplate systemConfigurationTemplate;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;
    
    @Override
    public void setUp() {
        operatorDao = createMock(OperatorDao.class);
        serviceManagementTemplate = createMock(ServiceManagementTemplate.class);
        systemConfigurationTemplate = createMock(SystemConfigurationTemplate.class);
        configurableMailSenderFactory = createMock(ConfigurableMailSenderFactory.class);
        mailMessageComposer = createMock(MailMessageComposer.class);
        
        serverMgr = new ServerMgrImpl();
        serverMgr.setOperatorDao(operatorDao);
        serverMgr.setServiceManagementTemplate(serviceManagementTemplate);
        serverMgr.setSystemConfigurationTemplate(systemConfigurationTemplate);
        serverMgr.setConfigurableMailSenderFactory(configurableMailSenderFactory);
        serverMgr.setMailMessageComposer(mailMessageComposer);
    }
    
    @Override
    public void tearDown() {
        reset(operatorDao);
        reset(serviceManagementTemplate);
        reset(systemConfigurationTemplate);
        reset(configurableMailSenderFactory);
        reset(mailMessageComposer);
    }
    
}
