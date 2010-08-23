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
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.InternalEnumeratorTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
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

	@Resource BasicDao<KeyType> keyTypeDao;
	@Resource FilterDao<Country, CountryFilter> countryDao;
	@Resource FilterDao<Service, ServiceFilter> serviceDao;
	@Resource FilterDao<Province, ProvinceFilter> provinceDao;
	@Resource FilterDao<Category, CategoryFilter> categoryDao;
	@Resource BasicDao<InternalEnumerator> internalEnumeratorDao;
	@Resource BasicDao<PublicEnumerator> publicEnumeratorDao;
	@Resource FilterDao<Unit, UnitFilter> unitDao;
	@Resource FilterDao<UserAssociation, UserAssociationFilter> userAssociationDao;
	@Resource FilterDao<UserGroup, UserFilter> userGroupDao;
	@Resource BasicDao<Credential> credentialDao;
	@Resource FilterDao<UserLog, UserLogFilter> userLogDao;
	@Resource FilterDao<Identity, IdentityFilter> identityDao;
	@Resource FilterDao<Server, ServerFilter> serverDao;
	@Resource BasicDao<UserRole> userRoleDao;
	@Resource BasicDao<EntityPreference> entityPreferenceDao;	
	
	@Test
	public void operator() {
		Operator operator = entity.getOperator();

		assertEquals(operator, getOperatorDao().findUnique(operator.getOperatorName()));
		
		KeyType keyType = KeyTypeTestSupport.createKeyType(operator);
		assertEquals(keyTypeDao.merge(keyType), keyTypeDao.findUnique(keyType.getOperator(), keyType.getKeyCode()));

		Country target = CountryTestSupport.createCountry(operator);
		assertEquals(countryDao.merge(target), countryDao.findUnique(target.getOperator(), target.getCountryCode()));

		Service service = ServiceTestSupport.createService(operator);
		assertEquals(serviceDao.merge(service), serviceDao.findUnique(operator, service.getServiceName()));
		
		Province province = ProvinceTestSupport.createProvince(operator);
		assertEquals(provinceDao.merge(province), provinceDao.findUnique(province.getOperator(), province.getProvinceCode()));

		Category category = CategoryTestSupport.createCategory(entity);
		assertEquals(categoryDao.merge(category), categoryDao.findUnique(category.getEntity(), category.getCategoryGroup(), category.getCategoryCode()));

		InternalEnumerator internalEnumerator = InternalEnumeratorTestSupport.createInternalEnumerator(entity);
		assertEquals(internalEnumeratorDao.merge(internalEnumerator), internalEnumeratorDao.findUnique(internalEnumerator.getEntity(), internalEnumerator.getTypeName()));

		PublicEnumerator publicEnumerator = new PublicEnumerator(operator, "TEST");
		assertEquals(publicEnumeratorDao.merge(publicEnumerator), publicEnumeratorDao.findUnique(operator, "TEST"));

		Unit unit = UnitTestSupport.createUnit(entity);
		assertEquals(unitDao.merge(unit), unitDao.findUnique(unit.getEntity(), unit.getUnitCode()));

		UserGroup parent = userGroupDao.merge(UserGroupTestSupport.createUserGroup(entity));
		UserAssociation userAssociation = UserAssociationTestSupport.createUserAssociation(parent);
		userAssociationDao.saveOrUpdate(userAssociation);
		assertEquals(userAssociation, userAssociationDao.findUnique(userAssociation.getParent(), userAssociation.getChild()));

		UserGroup userGroup = UserGroupTestSupport.createUserGroup(entity);
		assertEquals(userGroupDao.merge(userGroup), userGroupDao.findUnique(userGroup.getEntity(), userGroup.getUserKey()));

		Identity identity = identityDao.merge(IdentityTestSupport.createIdentity());
		assertEquals(identity, identityDao.findUnique(identity.getPrincipal()));
		
		User user = (User) userGroupDao.merge(UserTestSupport.createUser(entity, identity));
		UserLog userLog = UserLogTestSupport.createUserLog(user);
		assertEquals(userLogDao.merge(userLog), userLogDao.findUnique(userLog.getUser(), userLog.getLastEvent()));

		Credential credential = credentialDao.merge(CredentialTestSupport.createCredential(identity));
		assertEquals(credential, credentialDao.findUnique(credential.getIdentity()));

		Server server = ServerTestSupport.createServer(operator);
		server.setCredential(credential);
		assertEquals(serverDao.merge(server), serverDao.findUnique(server.getOperator(), server.getServerName()));

		UserRole userRole = UserRoleTestSupport.createUserRole(user, service);
		user.getRoles().add(userRole);
		userGroupDao.persist(user);
		assertEquals(userRoleDao.merge(userRole), userRoleDao.findUnique(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));

		EntityPreference entityPreference = entityPreferenceDao.merge(EntityPreference.entityPreferenceFactory(entity, keyType));
		assertEquals(entityPreference, entityPreferenceDao.findUnique(entityPreference.getEntity(), entityPreference.getKeyType()));

	}
	
}
