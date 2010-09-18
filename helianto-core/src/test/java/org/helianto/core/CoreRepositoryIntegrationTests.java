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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	public void prepareOperator() {
		logger.debug("START TEST");
		Operator operator = entity.getOperator();

		assertEquals(operator, getOperatorDao().findUnique(operator.getOperatorName()));
		
		logger.debug("Ready to persist keyType.");
		KeyType keyType = KeyTypeTestSupport.createKeyType(operator);
		keyTypeDao.saveOrUpdate(keyType);
		assertEquals(keyType, keyTypeDao.findUnique(keyType.getOperator(), keyType.getKeyCode()));

		logger.debug("Ready to persist country.");
		Country country = CountryTestSupport.createCountry(operator);
		countryDao.saveOrUpdate(country);
		assertEquals(country, countryDao.findUnique(country.getOperator(), country.getCountryCode()));

		logger.debug("Ready to persist service.");
		Service service = ServiceTestSupport.createService(operator);
		serviceDao.saveOrUpdate(service);
		assertEquals(service, serviceDao.findUnique(operator, service.getServiceName()));
		
		Province province = ProvinceTestSupport.createProvince(operator);
		provinceDao.saveOrUpdate(province);
		assertEquals(province, provinceDao.findUnique(province.getOperator(), province.getProvinceCode()));

		Category category = CategoryTestSupport.createCategory(entity);
		categoryDao.saveOrUpdate(category);
		assertEquals(category, categoryDao.findUnique(category.getEntity(), category.getCategoryGroup(), category.getCategoryCode()));

		InternalEnumerator internalEnumerator = InternalEnumeratorTestSupport.createInternalEnumerator(entity);
		internalEnumeratorDao.saveOrUpdate(internalEnumerator);
		assertEquals(internalEnumerator, internalEnumeratorDao.findUnique(internalEnumerator.getEntity(), internalEnumerator.getTypeName()));

		PublicEnumerator publicEnumerator = new PublicEnumerator(operator, "TEST");
		publicEnumeratorDao.saveOrUpdate(publicEnumerator);
		assertEquals(publicEnumerator, publicEnumeratorDao.findUnique(operator, "TEST"));

		Unit unit = UnitTestSupport.createUnit(entity);
		unitDao.saveOrUpdate(unit);
		assertEquals(unit, unitDao.findUnique(unit.getEntity(), unit.getUnitCode()));

		UserGroup parent = UserGroupTestSupport.createUserGroup(entity);
		userGroupDao.saveOrUpdate(parent);
		UserAssociation userAssociation = UserAssociationTestSupport.createUserAssociation(parent);
		userAssociationDao.saveOrUpdate(userAssociation);
		assertEquals(userAssociation, userAssociationDao.findUnique(userAssociation.getParent(), userAssociation.getChild()));

		UserGroup userGroup = UserGroupTestSupport.createUserGroup(entity);
		userGroupDao.saveOrUpdate(userGroup);
		assertEquals(userGroup, userGroupDao.findUnique(userGroup.getEntity(), userGroup.getUserKey()));

		// test also the secondary table
		Identity identity = new Identity("PRINCIPAL");
		byte[] photo = new byte[] { 1, 2, 3 };
		identity.setPhoto(photo);
		identityDao.saveOrUpdate(identity);
		Identity managedIdentity = identityDao.findUnique("principal");
		assertEquals(identity, managedIdentity);
		assertEquals(photo, managedIdentity.getPhoto());
		
		User user = UserTestSupport.createUser(entity, identity);
		userGroupDao.saveOrUpdate(user);
		UserLog userLog = UserLogTestSupport.createUserLog(user);
		userLogDao.saveOrUpdate(userLog);
		assertEquals(userLog, userLogDao.findUnique(userLog.getUser(), userLog.getLastEvent()));

		Credential credential = CredentialTestSupport.createCredential(identity);
		credentialDao.saveOrUpdate(credential);
		assertEquals(credential, credentialDao.findUnique(credential.getIdentity()));

		Server server = ServerTestSupport.createServer(operator);
		server.setCredential(credential);
		serverDao.saveOrUpdate(server);
		assertEquals(server, serverDao.findUnique(server.getOperator(), server.getServerName()));

		UserRole userRole = UserRoleTestSupport.createUserRole(user, service);
		user.getRoles().add(userRole);
		userGroupDao.saveOrUpdate(user);
		userRoleDao.saveOrUpdate(userRole);
		assertEquals(userRole, userRoleDao.findUnique(userRole.getUserGroup(), userRole.getService(), userRole.getServiceExtension()));

		EntityPreference entityPreference = EntityPreference.entityPreferenceFactory(entity, keyType);
		entityPreferenceDao.saveOrUpdate(entityPreference);
		assertEquals(entityPreference, entityPreferenceDao.findUnique(entityPreference.getEntity(), entityPreference.getKeyType()));

	}
	
	private static final Logger logger = LoggerFactory.getLogger(CoreRepositoryIntegrationTests.class);
	
}
