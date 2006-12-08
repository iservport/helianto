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
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.mail.ConfigurableMailSender;
import org.helianto.core.mail.ConfigurableMailSenderFactory;
import org.helianto.core.mail.compose.MailMessageComposer;
import org.helianto.core.type.IdentityType;

import junit.framework.TestCase;

public class AbstractServerMgrTests extends TestCase {

    // class under test
    private ServerMgrImpl serverMgr;
    
    public void testFindOrCreateUserGroupNoName() {
        Entity entity = new Entity();
        
        expect(authenticationDao.findIdentityByPrincipal("NAME"))
            .andReturn(null);
        authenticationDao.persistIdentity(isA(Identity.class));
        replay(authenticationDao);
        
        expect(authorizationDao.findUserGroupByNaturalId(isA(Entity.class), isA(Identity.class)))
            .andReturn(null);
        authorizationDao.persistUserGroup(isA(UserGroup.class));
        replay(authorizationDao);
        
        UserGroup userGroup = userMgr.findOrCreateUserGroup(entity, "NAME");
        verify(authenticationDao);
        
        assertSame(entity, userGroup.getEntity());
        assertEquals("NAME", userGroup.getIdentity().getPrincipal());
        assertEquals("NAME", userGroup.getIdentity().getOptionalAlias());
        assertEquals(IdentityType.GROUP.getValue(), userGroup.getIdentity().getIdentityType());
    }
    
    public void testFindOrCreateUserGroupOnlyName() {
        Identity groupIdentity = new Identity(); 
        Entity entity = new Entity();
        
        expect(authenticationDao.findIdentityByPrincipal("NAME"))
            .andReturn(groupIdentity);
        replay(authenticationDao);
        
        expect(authorizationDao.findUserGroupByNaturalId(isA(Entity.class), isA(Identity.class)))
            .andReturn(null);
        authorizationDao.persistUserGroup(isA(UserGroup.class));
        replay(authorizationDao);
        
        UserGroup userGroup = userMgr.findOrCreateUserGroup(entity, "NAME");
        verify(authenticationDao);
        
        assertSame(entity, userGroup.getEntity());
        assertSame(groupIdentity, userGroup.getIdentity());
    }
    
    public void testFindOrCreateUserGroup() {
        Identity groupIdentity = new Identity(); 
        Entity entity = new Entity();
        UserGroup userGroup = new UserGroup();
        
        expect(authenticationDao.findIdentityByPrincipal("NAME"))
            .andReturn(groupIdentity);
        replay(authenticationDao);
        
        expect(authorizationDao.findUserGroupByNaturalId(isA(Entity.class), isA(Identity.class)))
            .andReturn(userGroup);
        replay(authorizationDao);
        
        assertSame(userGroup, userMgr.findOrCreateUserGroup(entity, "NAME"));
        verify(authenticationDao);
        
    }
    
    // collabs
    
    private OperatorDao operatorDao;
    private ServiceManagementTemplate serviceManagementTemplate;
    private SystemConfigurationTemplate systemConfigurationTemplate;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;
    private ConfigurableMailSender sender;
    
    @Override
    public void setUp() {
        operatorDao = createMock(OperatorDao.class);
        serviceManagementTemplate = createMock(ServiceManagementTemplate.class);
        systemConfigurationTemplate = createMock(SystemConfigurationTemplate.class);
        configurableMailSenderFactory = createMock(ConfigurableMailSenderFactory.class);
        mailMessageComposer = createMock(MailMessageComposer.class);
        
        sender = createMock(ConfigurableMailSender.class);
        
        serverMgr = new ServerMgrImpl();
        serverMgr.setOperatorDao(operatorDao);
//        serverMgr.setServiceManagementTemplate(serviceManagementTemplate);
//        serverMgr.setSystemConfigurationTemplate(systemConfigurationTemplate);
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
        reset(sender);
    }
    
}
