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
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Service;
import org.helianto.core.UserRole;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.TestingFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserRoleTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class NamespaceMgrTests {
	
	@Test
	public void findOperators() {
		Filter filter = new TestingFilter();
		List<Operator> operatorList = OperatorTestSupport.createOperatorList(1);
		
		expect(operatorDao.find(filter)).andReturn(operatorList);
		replay(operatorDao);
		
		assertSame(operatorList , namespaceMgr.findOperators(filter));
		verify(operatorDao);
	}
	
	@Test
	public void storeOperator() {
		Operator operator = OperatorTestSupport.createOperator();
		
		operatorDao.saveOrUpdate(operator);
		replay(operatorDao);
		
		assertSame(operator , namespaceMgr.storeOperator(operator));
		verify(operatorDao);
	}
	
	@Test
	public void findProvinces() {
		List<Province> provinceList = new ArrayList<Province>();
		Filter filter = new TestingFilter();
		
		expect(provinceDao.find(filter)).andReturn(provinceList);
		replay(provinceDao);
		
		assertSame(provinceList , namespaceMgr.findProvinces(filter));
		verify(provinceDao);
	}
	
	@Test
	public void storeProvince() {
		Province province = new Province();
		Province managedProvince = new Province();
		
		expect(provinceDao.merge(province)).andReturn(managedProvince);
		replay(provinceDao);
		
		assertSame(managedProvince , namespaceMgr.storeProvince(province));
		verify(provinceDao);
	}
	
	@Test
	public void findEntities() {
		List<Entity> entityList = EntityTestSupport.createEntityList(1);
		Filter filter = new TestingFilter();
		
		expect(entityDao.find(filter)).andReturn(entityList);
		replay(entityDao);
		
		assertSame(entityList , namespaceMgr.findEntities(filter));
		verify(entityDao);
	}
	
	@Test
	public void storeEntity() {
		Entity entity = EntityTestSupport.createEntity();
		
		entityDao.saveOrUpdate(entity);
		replay(entityDao);
		
		assertSame(entity , namespaceMgr.storeEntity(entity));
		verify(entityDao);
	}
	
	@Test
	public void findKeyTypes() {
		List<KeyType> keyTypeList = new ArrayList<KeyType>();
		Filter filter = new TestingFilter();
		
		expect(keyTypeDao.find(filter)).andReturn(keyTypeList);
		replay(keyTypeDao);
		
		assertSame(keyTypeList , namespaceMgr.findKeyTypes(filter));
		verify(keyTypeDao);
	}
	
	@Test
	public void storeKeyType() {
		KeyType keyType = KeyTypeTestSupport.createKeyType();
		
		keyTypeDao.saveOrUpdate(keyType);
		replay(keyTypeDao);
		
		assertSame(keyType , namespaceMgr.storeKeyType(keyType));
		verify(keyTypeDao);
	}
	
	@Test
	public void findServices() {
		List<Service> serviceList = new ArrayList<Service>();
		Filter filter = new TestingFilter();
		
		expect(serviceDao.find(filter)).andReturn(serviceList);
		replay(serviceDao);
		
		assertSame(serviceList , namespaceMgr.findServices(filter));
		verify(serviceDao);
	}
	
	@Test
	public void storeService() {
		Service service = new Service(OperatorTestSupport.createOperator(), "NAME");
		
		serviceDao.saveOrUpdate(service);
		replay(serviceDao);
		
		assertSame(service , namespaceMgr.storeService(service));
		verify(serviceDao);
	}
	
	@Test
	public void findUserRoles() {
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		Filter filter = new TestingFilter();
		
		expect(userRoleDao.find(filter)).andReturn(userRoleList);
		replay(userRoleDao);
		
		assertSame(userRoleList , namespaceMgr.findUserRoles(filter));
		verify(userRoleDao);
	}
	
	@Test
	public void storeUserRole() {
		UserRole userRole = UserRoleTestSupport.createUserRole();
		
		userRoleDao.saveOrUpdate(userRole);
		replay(userRoleDao);
		
		assertSame(userRole , namespaceMgr.storeUserRole(userRole));
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
	private FilterDao<Operator> operatorDao;
	private FilterDao<Province> provinceDao;
	private FilterDao<Entity> entityDao;
	private FilterDao<KeyType> keyTypeDao;
	private FilterDao<Service> serviceDao;
	private FilterDao<UserRole> userRoleDao;
	
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
		keyTypeDao = createMock(FilterDao.class);
		namespaceMgr.setKeyTypeDao(keyTypeDao);
		serviceDao = createMock(FilterDao.class);
		namespaceMgr.setServiceDao(serviceDao);
		userRoleDao = createMock(FilterDao.class);
		namespaceMgr.setUserRoleDao(userRoleDao);
	}
	
	@After
	public void tearDown() {
		reset(operatorDao);
		reset(provinceDao);
		reset(entityDao);
		reset(keyTypeDao);
		reset(serviceDao);
		reset(userRoleDao);
	}

}
