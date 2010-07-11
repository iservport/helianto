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

package org.helianto.core;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.AbstractDaoIntegrationTest;
import org.helianto.core.test.CategoryTestSupport;
import org.helianto.core.test.CountryTestSupport;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.InternalEnumeratorTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.ProvinceTestSupport;
import org.helianto.core.test.ServerTestSupport;
import org.helianto.core.test.ServiceTestSupport;
import org.helianto.core.test.UnitTestSupport;
import org.helianto.core.test.UserAssociationTestSupport;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.core.test.UserLogTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class CoreRepositoryIntegrationTests extends AbstractDaoIntegrationTest {

	@Resource FilterDao<Category, CategoryFilter> categoryDao;
	@Test
	public void category() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		Category target = CategoryTestSupport.createCategory(entity);
		assertEquals(categoryDao.merge(target), categoryDao.findUnique(target.getEntity(), target.getCategoryGroup(), target.getCategoryCode()));
	}
	
	@Resource FilterDao<Country, CountryFilter> countryDao;
	@Test
	public void country() {
		Country target = CountryTestSupport.createCountry();
		assertEquals(countryDao.merge(target), countryDao.findUnique(target.getOperator(), target.getCountryCode()));
	}
	
	@Resource BasicDao<Credential> credentialDao;
	@Resource FilterDao<Identity, IdentityFilter> identityDao;
	@Resource FilterDao<Server, ServerFilter> serverDao;
	@Test
	public void credential() {
		Identity identity = identityDao.merge(IdentityTestSupport.createIdentity());
		assertEquals(identity, identityDao.findUnique(identity.getPrincipal()));
		
		Credential credential = credentialDao.merge(CredentialTestSupport.createCredential(identity));
		assertEquals(credential, credentialDao.findUnique(credential.getIdentity()));

		Server server = ServerTestSupport.createServer();
		server.setCredential(credential);
		assertEquals(serverDao.merge(server), serverDao.findUnique(server.getOperator(), server.getServerName()));
	}
	
	@Resource BasicDao<InternalEnumerator> internalEnumeratorDao;
	@Test
	public void internalEnumerator() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		InternalEnumerator target = InternalEnumeratorTestSupport.createInternalEnumerator(entity);
		assertEquals(internalEnumeratorDao.merge(target), internalEnumeratorDao.findUnique(target.getEntity(), target.getTypeName()));
	}

	@Resource FilterDao<Operator, OperatorFilter> operatorDao;
	@Resource BasicDao<KeyType> keyTypeDao;
	@Test
	public void operator() {
		Operator operator = operatorDao.merge(OperatorTestSupport.createOperator());
		assertEquals(operator, operatorDao.findUnique(operator.getOperatorName()));
		
		KeyType keyType = KeyTypeTestSupport.createKeyType(operator);
		assertEquals(keyTypeDao.merge(keyType), keyTypeDao.findUnique(keyType.getOperator(), keyType.getKeyCode()));
	}
	
	@Resource FilterDao<Province, ProvinceFilter> provinceDao;
	@Test
	public void province() {
		Province target = ProvinceTestSupport.createProvince();
		assertEquals(provinceDao.merge(target), provinceDao.findUnique(target.getOperator(), target.getProvinceCode()));
	}
	
	@Resource FilterDao<Service, ServiceFilter> serviceDao;
	@Test
	public void service() {
		Operator operator = operatorDao.merge(OperatorTestSupport.createOperator());
		Service service = serviceDao.merge(ServiceTestSupport.createService(operator));
		assertEquals(service, serviceDao.findUnique(service.getOperator(), service.getServiceName()));
	}
	
	@Resource FilterDao<Unit, UnitFilter> unitDao;
	@Test
	public void unit() {
		Unit target = UnitTestSupport.createUnit();
		assertEquals(unitDao.merge(target), unitDao.findUnique(target.getEntity(), target.getUnitCode()));
	}
	
	@Resource FilterDao<UserAssociation, UserAssociationFilter> userAssociationDao;
	@Test
	public void userAssociation() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		UserGroup userGroup = userGroupDao.merge(UserGroupTestSupport.createUserGroup(entity));
		UserAssociation target = UserAssociationTestSupport.createUserAssociation(userGroup);
		assertEquals(userAssociationDao.merge(target), userAssociationDao.findUnique(target.getParent(), target.getChild()));
	}

	@Resource FilterDao<UserGroup, UserFilter> userGroupDao;
	@Test
	public void userGroup() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		UserGroup target = UserGroupTestSupport.createUserGroup(entity);
		assertEquals(userGroupDao.merge(target), userGroupDao.findUnique(target.getEntity(), target.getUserKey()));
	}
	
	@Resource FilterDao<UserLog, UserLogFilter> userLogDao;
	@Test
	public void userLogGroup() {
		Identity identity = identityDao.merge(IdentityTestSupport.createIdentity());
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		User user = (User) userGroupDao.merge(UserTestSupport.createUser(entity, identity));
		UserLog target = UserLogTestSupport.createUserLog(user);
		assertEquals(userLogDao.merge(target), userLogDao.findUnique(target.getUser(), target.getLastEvent()));
	}
	
	@Resource BasicDao<UserRole> userRoleDao;
	@Test
	public void userRole() {
		Operator operator = operatorDao.merge(OperatorTestSupport.createOperator());
		Identity identity = identityDao.merge(IdentityTestSupport.createIdentity());
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		Service service = serviceDao.merge(ServiceTestSupport.createService(operator));
		User user = UserTestSupport.createUser(entity, identity);
		UserRole target = UserRoleTestSupport.createUserRole(user, service);
		user.getRoles().add(target);
		userGroupDao.persist(user);
		assertEquals(userRoleDao.merge(target), userRoleDao.findUnique(target.getUserGroup(), target.getService(), target.getServiceExtension()));
	}
	
	@Resource BasicDao<EntityPreference> entityPreferenceDao;
	@Test
	public void entityPreference() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		KeyType keyType = keyTypeDao.merge(KeyTypeTestSupport.createKeyType(entity.getOperator()));
		EntityPreference entityPreference = entityPreferenceDao.merge(EntityPreference.entityPreferenceFactory(entity, keyType));
		assertEquals(entityPreference, entityPreferenceDao.findUnique(entityPreference.getEntity(), entityPreference.getKeyType()));
	}
	
}
