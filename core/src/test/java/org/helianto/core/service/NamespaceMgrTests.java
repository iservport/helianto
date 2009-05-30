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
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.ProvinceTestSupport;
import org.helianto.core.test.ServiceTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class NamespaceMgrTests {
	
	@Test
	public void testFindOperatorExisting() {
		List<Operator> operatorList = OperatorTestSupport.createOperatorList(1);
		
		expect(operatorDao.find(isA(OperatorFilter.class))).andReturn(operatorList);
		replay(operatorDao);
		
		assertSame(operatorList , namespaceMgr.findOperator());
		verify(operatorDao);
	}
	
	@Test
	public void testFindOperatorFirstTime() {
		Operator operator = OperatorTestSupport.createOperator();
		List<Operator> operatorList = new ArrayList<Operator>();
		
		expect(operatorDao.find(isA(OperatorFilter.class))).andReturn(operatorList);
		expect(operatorDao.merge(isA(Operator.class))).andReturn(operator);
		replay(operatorDao);
		
		assertSame(operator , namespaceMgr.findOperator().get(0));
		verify(operatorDao);
	}
	
	@Test
	public void testFindOperatorByName() {
		Operator operator = OperatorTestSupport.createOperator();
		
		expect(operatorDao.findUnique("NAME")).andReturn(operator);
		replay(operatorDao);
		
		assertEquals(operator , namespaceMgr.findOperatorByName("NAME"));
		verify(operatorDao);
	}
	
	@Test
	public void testStoreOperator() {
		Operator operator = OperatorTestSupport.createOperator();
		Operator managedOperator = OperatorTestSupport.createOperator();
		
		expect(operatorDao.merge(operator)).andReturn(managedOperator);
		replay(operatorDao);
		
		assertSame(managedOperator , namespaceMgr.storeOperator(operator));
		verify(operatorDao);
	}
	
	@Test
	public void testFindProvinces() {
		List<Province> provinceList = ProvinceTestSupport.createProvinceList(1);
		ProvinceFilter filter = new ProvinceFilter();
		
		expect(provinceDao.find(filter)).andReturn(provinceList);
		replay(provinceDao);
		
		assertSame(provinceList , namespaceMgr.findProvinces(filter));
		verify(provinceDao);
	}
	
	@Test
	public void testPrepareProvince() {
		Province province = ProvinceTestSupport.createProvince();
		Province managedProvince = ProvinceTestSupport.createProvince();
		
		expect(provinceDao.merge(province)).andReturn(managedProvince);
		provinceDao.evict(managedProvince);
		replay(provinceDao);
		
		assertSame(managedProvince , namespaceMgr.prepareProvince(province));
		verify(provinceDao);
	}
	
	@Test
	public void testPrepareNewProvince() {
		Entity entity = EntityTestSupport.createEntity();
		Entity managedEntity = EntityTestSupport.createEntity();
		
		expect(entityDao.merge(entity)).andReturn(managedEntity);
		replay(entityDao);
		
		assertSame(managedEntity.getOperator() , namespaceMgr.prepareNewProvince(entity).getOperator());
		verify(entityDao);
	}
	
	@Test
	public void testStoreProvince() {
		Province province = ProvinceTestSupport.createProvince();
		Province managedProvince = ProvinceTestSupport.createProvince();
		
		expect(provinceDao.merge(province)).andReturn(managedProvince);
		replay(provinceDao);
		
		assertSame(managedProvince , namespaceMgr.storeProvince(province));
		verify(provinceDao);
	}
	
	@Test
	public void testFindEntities() {
		List<Entity> entityList = EntityTestSupport.createEntityList(1);
		EntityFilter filter = new EntityFilter();
		
		expect(entityDao.find(filter)).andReturn(entityList);
		replay(entityDao);
		
		assertSame(entityList , namespaceMgr.findEntities(filter));
		verify(entityDao);
	}
	
	@Test
	public void testPrepareEntity() {
		Entity entity = EntityTestSupport.createEntity();
		Entity managedEntity = EntityTestSupport.createEntity();
		
		expect(entityDao.merge(entity)).andReturn(managedEntity);
		entityDao.evict(managedEntity);
		replay(entityDao);
		
		assertSame(managedEntity , namespaceMgr.prepareEntity(entity));
		verify(entityDao);
	}
	
	@Test
	public void testLoadUserList() {
		Entity entity = new Entity();
		List<UserGroup> userList = new ArrayList<UserGroup>();
		
		expect(userMgr.findUsers(isA(UserFilter.class))).andReturn(userList);
		replay(userMgr);
		
		namespaceMgr.loadUserList(entity);
		assertSame(userList, entity.getUserList());
		verify(userMgr);
	}

	@Test
	public void testStoreNewEntity() {
		Entity entity = EntityTestSupport.createEntity();
		Entity managedEntity = EntityTestSupport.createEntity();
		
		expect(entityDao.merge(entity)).andReturn(managedEntity);
		replay(entityDao);
		
		assertSame(managedEntity , namespaceMgr.storeEntity(entity));
		verify(entityDao);
	}
	
	@Test
	public void testLoadKeyTypes() {
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
	public void testStoreKeyType() {
		KeyType keyType = KeyTypeTestSupport.createKeyType();
		KeyType managedKeyType = KeyTypeTestSupport.createKeyType();
		
		expect(keyTypeDao.merge(keyType)).andReturn(managedKeyType);
		replay(keyTypeDao);
		
		assertSame(managedKeyType , namespaceMgr.storeKeyType(keyType));
		verify(keyTypeDao);
	}
	
	@Test
	public void testLoadServices() {
		Operator operator = new Operator();
		Operator managedOperator = new Operator();
		Service service = new Service();
		managedOperator.getServices().add(service);
		
		expect(operatorDao.merge(operator)).andReturn(managedOperator);
		replay(operatorDao);
		
		List<Service> serviceList = namespaceMgr.loadServices(operator);
		assertSame(service, serviceList.get(0));
		verify(operatorDao);
	}

	@Test
	public void testStoreService() {
		Service service = ServiceTestSupport.createService();
		Service managedService = ServiceTestSupport.createService();
		
		expect(serviceDao.merge(service)).andReturn(managedService);
		replay(serviceDao);
		
		assertSame(managedService , namespaceMgr.storeService(service));
		verify(serviceDao);
	}
	
	private NamespaceMgrImpl namespaceMgr;
	private FilterDao<Operator, OperatorFilter> operatorDao;
	private FilterDao<Province, ProvinceFilter> provinceDao;
	private FilterDao<Entity, EntityFilter> entityDao;
	private BasicDao<KeyType> keyTypeDao;
	private BasicDao<Service> serviceDao;
	private UserMgr userMgr;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		namespaceMgr = new NamespaceMgrImpl();
		operatorDao = createMock(FilterDao.class);
		namespaceMgr.setOperatorDao(operatorDao);
		provinceDao = createMock(FilterDao.class);
		namespaceMgr.setProvinceDao(provinceDao);
		entityDao = createMock(FilterDao.class);
		namespaceMgr.setEntityDao(entityDao);
		keyTypeDao = createMock(BasicDao.class);
		namespaceMgr.setKeyTypeDao(keyTypeDao);
		serviceDao = createMock(BasicDao.class);
		namespaceMgr.setServiceDao(serviceDao);
		userMgr = createMock(UserMgr.class);
		namespaceMgr.setUserMgr(userMgr);
	}
	
	@After
	public void tearDown() {
		reset(operatorDao);
		reset(provinceDao);
		reset(entityDao);
		reset(keyTypeDao);
		reset(serviceDao);
		reset(userMgr);
	}

}
