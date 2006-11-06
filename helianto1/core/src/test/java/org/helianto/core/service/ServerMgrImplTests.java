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

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.User;
import org.helianto.core.UserRole;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.mail.MailComposer;
import org.helianto.core.test.OperatorTestSupport;
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
    
    //TODO pending
    public void testSendRegistrationNotification() throws MessagingException {
        Server transportServer = OperatorTestSupport.createServer();
        Operator operator = transportServer.getOperator();
        Server accessServer = OperatorTestSupport.createServer(operator);
        Credential cred = transportServer.getCredential();
        
//        expect(serverUtilsTemplate.selectFirstAvailableMailTransportServer(operator))
//            .andReturn(transportServer);
//        expect(mailComposer.composeRegistrationNotificationSubject(""))
//            .andReturn("SUBJECT");
//        expect(mailComposer.composeRegistrationNotification(cred,
//                operator.getOperatorHostAddress()))
//            .andReturn("SUBJECT");
//        expect(serverUtilsTemplate.selectFirstAvailableMailAccessServer(operator))
//            .andReturn(accessServer);
//        senderStrategy.send(accessServer, javaMailSender, helper);
        
//        serverMgr.sendRegistrationNotification(operator, cred);
    }

    // collabs
    
    private OperatorDao operatorDao;
    private ServiceManagementTemplate serviceManagementTemplate;
    private SystemConfigurationTemplate systemConfigurationTemplate;
    
    @Override
    public void setUp() {
        operatorDao = createMock(OperatorDao.class);
        serviceManagementTemplate = createMock(ServiceManagementTemplate.class);
        systemConfigurationTemplate = createMock(SystemConfigurationTemplate.class);
        
        serverMgr = new ServerMgrImpl();
        serverMgr.setOperatorDao(operatorDao);
        serverMgr.setServiceManagementTemplate(serviceManagementTemplate);
        serverMgr.setSystemConfigurationTemplate(systemConfigurationTemplate);
    }
    
    @Override
    public void tearDown() {
        reset(operatorDao);
        reset(serviceManagementTemplate);
        reset(systemConfigurationTemplate);
    }
    
}
