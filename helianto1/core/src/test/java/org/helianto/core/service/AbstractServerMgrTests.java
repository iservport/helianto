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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.IdentityType;
import org.helianto.core.OperationMode;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.dao.IdentityDao;
import org.helianto.core.dao.UserGroupDao;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.test.UserGroupTestSupport;

public class AbstractServerMgrTests extends TestCase {

	// class under test
	private ServerMgrImpl serverMgr;

	public void testCreateSystemConfiguration() {
        Identity managerIdentity = new Identity();
        Identity managerGroupIdentity = new Identity();
        Identity userGroupIdentity = new Identity();
		Entity entity = new Entity();
        UserGroup managerGroup = UserGroupTestSupport.createUserGroup();
        UserGroup userGroup = UserGroupTestSupport.createUserGroup();
        managerGroup.setEntity(entity);
        userGroup.setEntity(entity);

        expect(identityDao.findIdentityByNaturalId("ADMIN")).andReturn(
                managerGroupIdentity);
        expect(identityDao.findIdentityByNaturalId("USER")).andReturn(
                userGroupIdentity);
		replay(identityDao);

        expect(userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
                isA(Identity.class))).andReturn(managerGroup);
        expect(userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
                isA(Identity.class))).andReturn(userGroup);
		replay(userGroupDao);

		User manager = serverMgr.prepareSystemConfiguration(managerIdentity);
		
		assertSame(managerIdentity, manager.getIdentity());
		assertSame(entity, manager.getEntity());
		assertEquals(2, manager.getParentAssociations().size());
        Set<UserGroup> userSet = new HashSet<UserGroup>();
        for (UserAssociation a: manager.getParentAssociations()) {
            userSet.add(a.getParent());
        }
        assertTrue(userSet.contains(managerGroup));
        assertTrue(userSet.contains(userGroup));
	}

	public void testCreateDefaultEntity() {
		Entity entity = serverMgr.createDefaultEntity();
		assertSame("DEFAULT", entity.getAlias());
		assertSame("DEFAULT", entity.getOperator().getOperatorName());
		assertEquals(OperationMode.LOCAL.getValue(), entity.getOperator()
				.getOperationMode());
		assertEquals(Locale.getDefault(), entity.getOperator().getLocale());
	}

	public void testFindOrCreateUserGroupNoName() {
		Entity entity = new Entity();
		

		expect(identityDao.findIdentityByNaturalId("NAME")).andReturn(
				null);
		identityDao.persistIdentity(isA(Identity.class));
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(null);
		userGroupDao.persistUserGroup(isA(UserGroup.class));
		replay(userGroupDao);

		UserGroup userGroup = serverMgr.findOrCreateUserGroup(entity, "NAME");
		verify(identityDao);

		assertSame(entity, userGroup.getEntity());
		assertEquals("NAME", userGroup.getIdentity().getPrincipal());
		assertEquals("NAME", userGroup.getIdentity().getOptionalAlias());
		assertEquals(IdentityType.GROUP.getValue(), userGroup.getIdentity()
				.getIdentityType());
	}

	public void testFindOrCreateUserGroupOnlyName() {
		Identity groupIdentity = new Identity();
		Entity entity = new Entity();

		expect(identityDao.findIdentityByNaturalId("NAME")).andReturn(
				groupIdentity);
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

		expect(identityDao.findIdentityByNaturalId("NAME")).andReturn(
				groupIdentity);
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(userGroup);
		replay(userGroupDao);

		assertSame(userGroup, serverMgr.findOrCreateUserGroup(entity, "NAME"));
		verify(identityDao);

	}

	public void testFindOrCreateUserGroupWithOneExtension() {
		Identity groupIdentity = new Identity();
		Entity entity = new Entity();
		Operator defaultOperator = new Operator();
		entity.setOperator(defaultOperator);
		UserGroup userGroup = new UserGroup();

		expect(identityDao.findIdentityByNaturalId("ADMIN")).andReturn(
				groupIdentity);
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(userGroup);
		replay(userGroupDao);

		assertSame(userGroup, serverMgr.findOrCreateUserGroup(entity, "ADMIN", new String[] {"MANAGER"}));
		verify(identityDao);
		
		assertEquals(1, userGroup.getRoles().size());
		UserRole admin = userGroup.getRoles().iterator().next();
		assertSame(userGroup, admin.getUserGroup());
		assertSame(defaultOperator, admin.getService().getOperator());
		assertEquals("ADMIN", admin.getService().getServiceName());
		assertEquals("MANAGER", admin.getServiceExtension());

	}

	public void testFindOrCreateUserGroupWithExtensions() {
		Identity groupIdentity = new Identity();
		Entity entity = new Entity();
		Operator defaultOperator = new Operator();
		entity.setOperator(defaultOperator);
		UserGroup userGroup = new UserGroup();

		expect(identityDao.findIdentityByNaturalId("ADMIN")).andReturn(
				groupIdentity);
		replay(identityDao);

		expect(
				userGroupDao.findUserGroupByNaturalId(isA(Entity.class),
						isA(Identity.class))).andReturn(userGroup);
		replay(userGroupDao);

		String[] extensions = new String[] {"MANAGER", "ALL"};
		assertSame(userGroup, serverMgr.findOrCreateUserGroup(entity, "ADMIN", extensions));
		verify(identityDao);
		
		assertEquals(2, userGroup.getRoles().size());
		Set<UserRole> verifyRoles = new HashSet<UserRole>();
		for (UserRole role: userGroup.getRoles()) {
			assertSame(userGroup, role.getUserGroup());
			assertSame(defaultOperator, role.getService().getOperator());
			assertEquals("ADMIN", role.getService().getServiceName());
			if (Arrays.asList(extensions).contains(role.getServiceExtension())) {
				verifyRoles.add(role);
			}
		}
		assertEquals(2, verifyRoles.size());
		assertTrue(verifyRoles.containsAll(userGroup.getRoles()));
	}

	// collabs

	private OperatorDao operatorDao;

	private IdentityDao identityDao;

	private UserGroupDao userGroupDao;

	@Override
	public void setUp() {
		operatorDao = createMock(OperatorDao.class);
		identityDao = createMock(IdentityDao.class);
		userGroupDao = createMock(UserGroupDao.class);

		serverMgr = new ServerMgrImpl();
		serverMgr.setOperatorDao(operatorDao);
		serverMgr.setIdentityDao(identityDao);
		serverMgr.setUserGroupDao(userGroupDao);
	}

	@Override
	public void tearDown() {
		reset(operatorDao);
		reset(identityDao);
		reset(userGroupDao);
	}

}
