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

package org.helianto.core.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.helianto.core.EntityPreference;
import org.helianto.core.Server;
import org.helianto.core.def.AddressType;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Country;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PersonalAddress;
import org.helianto.core.domain.PrivateSequence;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.PublicAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.PublicSequence;
import org.helianto.core.domain.Service;
import org.helianto.core.domain.Unit;
import org.helianto.core.test.AbstractDaoIntegrationTest;
import org.helianto.core.test.CategoryTestSupport;
import org.helianto.core.test.CountryTestSupport;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.InternalEnumeratorTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.core.test.ServerTestSupport;
import org.helianto.core.test.ServiceTestSupport;
import org.helianto.core.test.UnitTestSupport;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.helianto.user.domain.User;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.domain.UserLog;
import org.helianto.user.domain.UserRequest;
import org.helianto.user.domain.UserRole;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class CoreRepositoryIntegrationTests 
	extends AbstractDaoIntegrationTest {

	private FilterDao<KeyType> keyTypeDao;
	private FilterDao<Country> countryDao;
	private FilterDao<Service> serviceDao;
	private FilterDao<Province> provinceDao;
	private FilterDao<Category> categoryDao;
	private FilterDao<PrivateSequence> internalEnumeratorDao;
	private FilterDao<PublicSequence> publicEnumeratorDao;
	private FilterDao<Unit> unitDao;
	private FilterDao<UserAssociation> userAssociationDao;
	private FilterDao<UserGroup> userGroupDao;
	private FilterDao<Credential> credentialDao;
	private FilterDao<UserLog> userLogDao;
	private FilterDao<Identity> identityDao;
	private FilterDao<PublicAddress> publicAddressDao;
	private FilterDao<PersonalAddress> personalAddressDao;
	private FilterDao<Server> serverDao;
	private FilterDao<UserRole> userRoleDao;
	private FilterDao<EntityPreference> entityPreferenceDao;	
	private FilterDao<UserRequest> userRequestDao;
	private FilterDao<PublicEntity> publicEntityDao;
	
	@Test
	public void core() {
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
		
		Province province = new Province(operator, "CODE");
		provinceDao.saveOrUpdate(province);
		assertEquals(province, provinceDao.findUnique(operator, "CODE"));

		Category category = CategoryTestSupport.createCategory(entity);
		categoryDao.saveOrUpdate(category);
		assertEquals(category, categoryDao.findUnique(category.getEntity(), category.getCategoryGroup(), category.getCategoryCode()));

		PrivateSequence internalEnumerator = InternalEnumeratorTestSupport.createInternalEnumerator(entity);
		internalEnumeratorDao.saveOrUpdate(internalEnumerator);
		assertEquals(internalEnumerator, internalEnumeratorDao.findUnique(internalEnumerator.getEntity(), internalEnumerator.getTypeName()));

		PublicSequence publicEnumerator = new PublicSequence(operator, "TEST");
		publicEnumeratorDao.saveOrUpdate(publicEnumerator);
		assertEquals(publicEnumerator, publicEnumeratorDao.findUnique(operator, "TEST"));

		Unit unit = UnitTestSupport.createUnit(entity);
		unitDao.saveOrUpdate(unit);
		assertEquals(unit, unitDao.findUnique(unit.getEntity(), unit.getUnitCode()));

		UserGroup userGroup = UserGroupTestSupport.createUserGroup(entity);
		userGroupDao.saveOrUpdate(userGroup);
		assertEquals(userGroup, userGroupDao.findUnique(userGroup.getEntity(), userGroup.getUserKey()));

		UserGroup parent = UserGroupTestSupport.createUserGroup(entity);
		userGroupDao.saveOrUpdate(parent);
		UserAssociation userAssociation = new UserAssociation(parent, userGroup);
		userAssociationDao.saveOrUpdate(userAssociation);
		assertEquals(userAssociation, userAssociationDao.findUnique(parent, userGroup));

		// test also the secondary table
		Identity identity = new Identity("PRINCIPAL");
		byte[] photo = new byte[] { 1, 2, 3 };
		identity.setPhoto(photo);
		identityDao.saveOrUpdate(identity);
		Identity managedIdentity = identityDao.findUnique("principal");
		assertEquals(identity, managedIdentity);
		assertEquals(photo, managedIdentity.getPhoto());
		
		PublicAddress publicAddress = new PublicAddress(entity.getOperator(), "POSTALCODE");
		publicAddressDao.saveOrUpdate(publicAddress);
		assertEquals(publicAddress, publicAddressDao.findUnique(entity.getOperator(), "POSTALCODE"));
		
		PersonalAddress personalAddress = new PersonalAddress(identity, AddressType.PERSONAL);
		personalAddressDao.saveOrUpdate(personalAddress);
		assertEquals(personalAddress, personalAddressDao.findUnique(identity, AddressType.PERSONAL.getValue()));
		
		User user = UserTestSupport.createUser(entity, identity);
		userGroupDao.saveOrUpdate(user);
		Date lastEvent = new Date();
		UserLog userLog = new UserLog(user, lastEvent);
		userLogDao.saveOrUpdate(userLog);
		assertEquals(userLog, userLogDao.findUnique(user, lastEvent));

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

		UserRequest loginRequest = new UserRequest(userGroup, Long.MAX_VALUE);
		userRequestDao.saveOrUpdate(loginRequest);
		assertEquals(loginRequest, userRequestDao.findUnique(userGroup, Long.MAX_VALUE));
		
		PublicEntity publicEntity = new PublicEntity(entity);
		publicEntityDao.saveOrUpdate(publicEntity);
		assertEquals(publicEntity, publicEntityDao.findUnique(entity, entity.getAlias(), "P"));

	}
	
	@Resource public void setKeyTypeDao(FilterDao<KeyType> keyTypeDao) {
		this.keyTypeDao = keyTypeDao;
	}
	
	@Resource public void setCountryDao(FilterDao<Country> countryDao) {
		this.countryDao = countryDao;
	}
	
	@Resource public void setServiceDao(FilterDao<Service> serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	@Resource public void setProvinceDao(FilterDao<Province> provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	@Resource public void setCategoryDao(FilterDao<Category> categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Resource public void setInternalEnumeratorDao(
			FilterDao<PrivateSequence> internalEnumeratorDao) {
		this.internalEnumeratorDao = internalEnumeratorDao;
	}
	
	@Resource public void setPublicEnumeratorDao(
			FilterDao<PublicSequence> publicEnumeratorDao) {
		this.publicEnumeratorDao = publicEnumeratorDao;
	}
	
	@Resource public void setUnitDao(FilterDao<Unit> unitDao) {
		this.unitDao = unitDao;
	}
	
	@Resource public void setUserAssociationDao(
			FilterDao<UserAssociation> userAssociationDao) {
		this.userAssociationDao = userAssociationDao;
	}
	
	@Resource public void setUserGroupDao(FilterDao<UserGroup> userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	
	@Resource public void setCredentialDao(FilterDao<Credential> credentialDao) {
		this.credentialDao = credentialDao;
	}
	
	@Resource public void setUserLogDao(FilterDao<UserLog> userLogDao) {
		this.userLogDao = userLogDao;
	}
	
	@Resource public void setIdentityDao(FilterDao<Identity> identityDao) {
		this.identityDao = identityDao;
	}
	
	@Resource public void setPublicAddressDao(FilterDao<PublicAddress> publicAddressDao) {
		this.publicAddressDao = publicAddressDao;
	}
	
	@Resource public void setPersonalAddressDao(
			FilterDao<PersonalAddress> personalAddressDao) {
		this.personalAddressDao = personalAddressDao;
	}
	
	@Resource public void setServerDao(FilterDao<Server> serverDao) {
		this.serverDao = serverDao;
	}
	
	@Resource public void setUserRoleDao(FilterDao<UserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	@Resource public void setEntityPreferenceDao(
			FilterDao<EntityPreference> entityPreferenceDao) {
		this.entityPreferenceDao = entityPreferenceDao;
	}
	
	@Resource public void setUserRequestDao(FilterDao<UserRequest> userRequestDao) {
		this.userRequestDao = userRequestDao;
	}
	
	@Resource public void setPublicEntityDao(FilterDao<PublicEntity> publicEntityDao) {
		this.publicEntityDao = publicEntityDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CoreRepositoryIntegrationTests.class);
	
}
