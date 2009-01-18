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
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.ServerDao;
import org.helianto.core.dao.ServiceDao;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.mail.ConfigurableMailSender;
import org.helianto.core.mail.ConfigurableMailSenderFactory;
import org.helianto.core.mail.compose.DecoratedPreparator;
import org.helianto.core.mail.compose.MailMessageComposer;
import org.helianto.core.mail.compose.PasswordConfirmationMailForm;
import org.helianto.core.mail.compose.PasswordConfirmationMailMessageDecorator;

public class ServerMgrImplTests extends TestCase {
    
    // class under test
    private ServerMgrImpl serverMgr;
    
	public void testFindOrCreateUserGroupNoName() {
		Entity entity = new Entity();
		

		expect(identityDao.findIdentityByNaturalId("NAME")).andReturn(
				null);
		identityDao.persistIdentity(isA(Identity.class));
		expectLastCall().anyTimes();
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(null);
		userGroupDao.persistUserGroup(isA(UserGroup.class));
		replay(userGroupDao);

		UserGroup userGroup = serverMgr.findOrCreateUserGroup(entity, "NAME");
		verify(identityDao);

		assertSame(entity, userGroup.getEntity());
		assertEquals("name", userGroup.getIdentity().getPrincipal());
		assertEquals("NAME", userGroup.getIdentity().getOptionalAlias());
		assertEquals(IdentityType.GROUP.getValue(), userGroup.getIdentity()
				.getIdentityType());
	}

	public void testFindOrCreateUserGroupOnlyName() {
		Identity groupIdentity = new Identity();
		Entity entity = new Entity();

		expect(identityDao.findIdentityByNaturalId("NAME")).andReturn(
				groupIdentity);
		identityDao.persistIdentity(groupIdentity);
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(null);
		userGroupDao.persistUserGroup(isA(UserGroup.class));
		replay(userGroupDao);

		UserGroup userGroup = serverMgr.findOrCreateUserGroup(entity, "NAME");
		verify(identityDao);

		assertSame(entity, userGroup.getEntity());
		assertSame(groupIdentity, userGroup.getIdentity());
	}

	public void testFindOrCreateUserGroup() {
		Identity groupIdentity = new Identity();
		Entity entity = new Entity();
		UserGroup userGroup = new UserGroup();
		userGroup.setEntity(entity);
		userGroup.setIdentity(groupIdentity);

		expect(identityDao.findIdentityByNaturalId("NAME")).andReturn(
				groupIdentity);
		identityDao.persistIdentity(groupIdentity);
		replay(identityDao);

		expect(userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(userGroup);
		replay(userGroupDao);

		assertEquals(userGroup, serverMgr.findOrCreateUserGroup(entity, "NAME"));
		verify(identityDao);

	}

	public void testFindOrCreateUserGroupWithOneExtension() {
		Identity groupIdentity = new Identity();
		Entity entity = new Entity();
		Operator defaultOperator = new Operator();
		entity.setOperator(defaultOperator);
		UserGroup userGroup = new UserGroup();
		userGroup.setEntity(entity);
		userGroup.setIdentity(groupIdentity);

		expect(identityDao.findIdentityByNaturalId("ADMIN")).andReturn(
				groupIdentity);
		identityDao.persistIdentity(groupIdentity);
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(userGroup);
		replay(userGroupDao);

		assertEquals(userGroup, serverMgr.findOrCreateUserGroup(entity, "ADMIN", new String[] {"MANAGER"}));
		verify(identityDao);
		
//		assertEquals(1, userGroup.getRoles().size());
//		UserRole admin = userGroup.getRoles().iterator().next();
//		assertSame(userGroup, admin.getUserGroup());
//		assertSame(defaultOperator, admin.getService().getOperator());
//		assertEquals("ADMIN", admin.getService().getServiceName());
//		assertEquals("MANAGER", admin.getServiceExtension());

	}

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
    
	private IdentityDao identityDao;
	private UserGroupDao userGroupDao;
    private ServerDao serverDao;
    private ServiceDao serviceDao;
    private ConfigurableMailSenderFactory configurableMailSenderFactory;
    private MailMessageComposer mailMessageComposer;
    private ConfigurableMailSender sender;
    
    @Override
    public void setUp() {
		identityDao = createMock(IdentityDao.class);
		userGroupDao = createMock(UserGroupDao.class);
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
