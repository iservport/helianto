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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.Service;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.ProvinceTestSupport;
import org.helianto.core.test.ServiceTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class NamespaceMgrTests {
	
	@Test
	public void findOperatorExisting() {
		List<Operator> operatorList = OperatorTestSupport.createOperatorList(1);
		
		expect(operatorDao.find(isA(OperatorFilter.class))).andReturn(operatorList);
		replay(operatorDao);
		
		assertSame(operatorList , namespaceMgr.findOperator());
		verify(operatorDao);
	}
	
	@Test
	public void findOperatorFirstTime() {
		List<Operator> operatorList = new ArrayList<Operator>();
		Operator managedOperator = OperatorTestSupport.createOperator();
		
		expect(operatorDao.merge(isA(Operator.class))).andReturn(managedOperator);
		expect(operatorDao.find(isA(OperatorFilter.class))).andReturn(operatorList);
		expect(postInstallationMgr.installOperator("DEFAULT", false)).andReturn(managedOperator);
		replay(operatorDao);
		replay(postInstallationMgr);
		
		entityDao.persist(isA(Entity.class));
		replay(entityDao);
		
		userGroupDao.persist(isA(UserGroup.class));
		expectLastCall().times(2);
		replay(userGroupDao);
		
		assertSame(managedOperator, namespaceMgr.findOperator().get(0));
		verify(operatorDao);
		verify(entityDao);
		verify(userGroupDao);
		
	}
	
	@Test
	public void findOperatorByName() {
		Operator operator = OperatorTestSupport.createOperator();
		
		expect(operatorDao.findUnique("NAME")).andReturn(operator);
		replay(operatorDao);
		
		assertEquals(operator , namespaceMgr.findOperatorByName("NAME"));
		verify(operatorDao);
	}
	
	@Test
	public void storeOperator() {
		Operator operator = OperatorTestSupport.createOperator();
		Operator managedOperator = OperatorTestSupport.createOperator();
		
		expect(operatorDao.merge(operator)).andReturn(managedOperator);
		replay(operatorDao);
		
		assertSame(managedOperator , namespaceMgr.storeOperator(operator));
		verify(operatorDao);
	}
	
	@Test
	public void findProvinces() {
		List<Province> provinceList = ProvinceTestSupport.createProvinceList(1);
		ProvinceFilter filter = new ProvinceFilter();
		
		expect(provinceDao.find(filter)).andReturn(provinceList);
		replay(provinceDao);
		
		assertSame(provinceList , namespaceMgr.findProvinces(filter));
		verify(provinceDao);
	}
	
	@Test
	public void prepareProvince() {
		Province province = ProvinceTestSupport.createProvince();
		Province managedProvince = ProvinceTestSupport.createProvince();
		
		expect(provinceDao.merge(province)).andReturn(managedProvince);
		provinceDao.evict(managedProvince);
		replay(provinceDao);
		
		assertSame(managedProvince , namespaceMgr.prepareProvince(province));
		verify(provinceDao);
	}
	
	@Test
	public void prepareNewProvince() {
		Entity entity = EntityTestSupport.createEntity();
		Entity managedEntity = EntityTestSupport.createEntity();
		
		expect(entityDao.merge(entity)).andReturn(managedEntity);
		replay(entityDao);
		
		assertSame(managedEntity.getOperator() , namespaceMgr.prepareNewProvince(entity).getOperator());
		verify(entityDao);
	}
	
	@Test
	public void storeProvince() {
		Province province = ProvinceTestSupport.createProvince();
		Province managedProvince = ProvinceTestSupport.createProvince();
		
		expect(provinceDao.merge(province)).andReturn(managedProvince);
		replay(provinceDao);
		
		assertSame(managedProvince , namespaceMgr.storeProvince(province));
		verify(provinceDao);
	}
	
	@Test
	public void findEntities() {
		List<Entity> entityList = EntityTestSupport.createEntityList(1);
		EntityFilter filter = new EntityFilter();
		
		expect(entityDao.find(filter)).andReturn(entityList);
		replay(entityDao);
		
		assertSame(entityList , namespaceMgr.findEntities(filter));
		verify(entityDao);
	}
	
	@Test
	public void prepareEntityNoId() {
		Entity entity = EntityTestSupport.createEntity();
		
		replay(entityDao);

		assertSame(entity , namespaceMgr.prepareEntity(entity));
		verify(entityDao);
	}
	
	@Test
	public void prepareEntity() {
		Entity entity = EntityTestSupport.createEntity();
		entity.setId(1);
		Entity managedEntity = EntityTestSupport.createEntity();
		User user = new User();
		managedEntity.getUsers().add(user);
		
		expect(entityDao.merge(entity)).andReturn(managedEntity);
		entityDao.evict(managedEntity);
		replay(entityDao);
		
		assertSame(managedEntity , namespaceMgr.prepareEntity(entity));
		assertSame(user , managedEntity.getUserList().get(0));
		verify(entityDao);
	}
	
	@Test
	public void storeNewEntity() {
		Entity entity = EntityTestSupport.createEntity();
		Entity managedEntity = EntityTestSupport.createEntity();
		
		expect(entityDao.merge(entity)).andReturn(managedEntity);
		replay(entityDao);
		
		assertSame(managedEntity , namespaceMgr.storeEntity(entity));
		verify(entityDao);
	}
	
	@Test
	public void loadKeyTypes() {
		Operator operator = new Operator();
		Operator managedOperator = new Operator();
		KeyType keyType = new KeyType();
		managedOperator.getKeyTypes().add(keyType);
		
		expect(operatorDao.merge(operator)).andReturn(managedOperator);
		replay(operatorDao);
		
		List<KeyType> keyTypeList = namespaceMgr.loadKeyTypes(operator);
		assertSame(keyType, keyTypeList.get(0));
		verify(operatorDao);
	}

	@Test
	public void storeKeyType() {
		KeyType keyType = KeyTypeTestSupport.createKeyType();
		KeyType managedKeyType = KeyTypeTestSupport.createKeyType();
		
		expect(keyTypeDao.merge(keyType)).andReturn(managedKeyType);
		replay(keyTypeDao);
		
		assertSame(managedKeyType , namespaceMgr.storeKeyType(keyType));
		verify(keyTypeDao);
	}
	
//	@Test
//	public void loadServices() {
//		Operator operator = new Operator();
//		Operator managedOperator = new Operator();
//		Service service = new Service();
//		managedOperator.getServices().add(service);
//		
//		expect(operatorDao.merge(operator)).andReturn(managedOperator);
//		replay(operatorDao);
//		
//		List<Service> serviceList = namespaceMgr.loadServices(operator);
//		assertSame(service, serviceList.get(0));
//		verify(operatorDao);
//	}
//
	@Test
	public void storeService() {
		Service service = ServiceTestSupport.createService();
		Service managedService = ServiceTestSupport.createService();
		
		expect(serviceDao.merge(service)).andReturn(managedService);
		replay(serviceDao);
		
		assertSame(managedService , namespaceMgr.storeService(service));
		verify(serviceDao);
	}
	
	@Test
	public void storeUserRole() {
		UserRole userRole = UserRoleTestSupport.createUserRole();
		UserRole managedUserRole = UserRoleTestSupport.createUserRole();
		
		expect(userRoleDao.merge(userRole)).andReturn(managedUserRole);
		replay(userRoleDao);
		
		assertSame(managedUserRole , namespaceMgr.storeUserRole(userRole));
		verify(userRoleDao);
	}
	
//	@Test
//	public void loadServiceNameMap() {
//		Operator operator = new Operator();
//		Operator managedOperator = new Operator();
//		Service service = ServiceTestSupport.createService();
//		service.setId(Integer.MAX_VALUE);
//		managedOperator.getServices().add(service);
//		UserRole userRole = new UserRole();
//		
//		expect(operatorDao.merge(operator)).andReturn(managedOperator);
//		replay(operatorDao);
//		
//		Map<String, String> serviceNameMap = namespaceMgr.loadServiceNameMap(operator, userRole);
//		assertSame(service.getServiceName() , serviceNameMap.get("2147483647"));
//		assertSame(service, userRole.getService());
//		verify(operatorDao);
//	}
	
	private NamespaceMgrImpl namespaceMgr;
	private PostInstallationMgr postInstallationMgr;
	private FilterDao<Operator, OperatorFilter> operatorDao;
	private FilterDao<Province, ProvinceFilter> provinceDao;
	private FilterDao<Entity, EntityFilter> entityDao;
	private BasicDao<UserGroup> userGroupDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;
	private BasicDao<UserRole> userRoleDao;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		namespaceMgr = new NamespaceMgrImpl();
		postInstallationMgr = createMock(PostInstallationMgr.class);
		namespaceMgr.setPostInstallationMgr(postInstallationMgr);
		operatorDao = createMock(FilterDao.class);
		namespaceMgr.setOperatorDao(operatorDao);
		provinceDao = createMock(FilterDao.class);
		namespaceMgr.setProvinceDao(provinceDao);
		entityDao = createMock(FilterDao.class);
		namespaceMgr.setEntityDao(entityDao);
		userGroupDao = createMock(BasicDao.class);
		namespaceMgr.setUserGroupDao(userGroupDao);
		keyTypeDao = createMock(BasicDao.class);
		namespaceMgr.setKeyTypeDao(keyTypeDao);
		serviceDao = createMock(BasicDao.class);
		namespaceMgr.setServiceDao(serviceDao);
		userRoleDao = createMock(BasicDao.class);
		namespaceMgr.setUserRoleDao(userRoleDao);
	}
	
	@After
	public void tearDown() {
		reset(postInstallationMgr);
		reset(operatorDao);
		reset(provinceDao);
		reset(entityDao);
		reset(userGroupDao);
		reset(keyTypeDao);
		reset(serviceDao);
		reset(userRoleDao);
	}

}
