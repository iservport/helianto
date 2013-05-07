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
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.classic.TestingFilter;
import org.helianto.core.form.KeyTypeForm;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mauricio Fernandes de Castro
 */
public class ContextMgrImplTests {
	
	@Test
	public void findOperators() {
		Filter filter = new TestingFilter();
		List<Operator> operatorList = OperatorTestSupport.createOperatorList(1);
		
		expect(operatorDao.find(filter)).andReturn(operatorList);
		replay(operatorDao);
		
		assertSame(operatorList , contextMgr.findOperators(filter));
		verify(operatorDao);
	}
	
	@Test
	public void storeOperator() {
		Operator operator = OperatorTestSupport.createOperator();
		
		operatorDao.saveOrUpdate(operator);
		replay(operatorDao);
		
		assertSame(operator , contextMgr.storeOperator(operator));
		verify(operatorDao);
	}
	
	@Test
	public void findProvinces() {
		List<Province> provinceList = new ArrayList<Province>();
		Filter filter = new TestingFilter();
		
		expect(provinceDao.find(filter)).andReturn(provinceList);
		replay(provinceDao);
		
		assertSame(provinceList , contextMgr.findProvinces(filter));
		verify(provinceDao);
	}
	
	@Test
	public void storeProvince() {
		Province province = new Province();
		Province managedProvince = new Province();
		
		expect(provinceDao.merge(province)).andReturn(managedProvince);
		replay(provinceDao);
		
		assertSame(managedProvince , contextMgr.storeProvince(province));
		verify(provinceDao);
	}
	
	@Test
	public void findEntities() {
		List<Entity> entityList = EntityTestSupport.createEntityList(1);
		Filter filter = new TestingFilter();
		
		expect(entityDao.find(filter)).andReturn(entityList);
		replay(entityDao);
		
		assertSame(entityList , contextMgr.findEntities(filter));
		verify(entityDao);
	}
	
	@Test
	public void storeEntity() {
		Entity entity = EntityTestSupport.createEntity();
		
		entityDao.saveOrUpdate(entity);
		replay(entityDao);
		
		assertSame(entity , contextMgr.storeEntity(entity));
		verify(entityDao);
	}
	
	@Test
	public void findKeyTypes() {
		List<KeyType> keyTypeList = new ArrayList<KeyType>();
		KeyTypeForm form = createMock(KeyTypeForm.class);
		
		expect(keyTypeRepository.find(isA(Filter.class))).andReturn(keyTypeList);
		replay(keyTypeRepository);
		
		assertSame(keyTypeList , contextMgr.findKeyTypes(form));
		verify(keyTypeRepository);
	}
	
	@Test
	public void storeKeyType() {
		KeyType keyType = new KeyType(new Operator("DEFAULT"), "CODE");
		
		expect(keyTypeRepository.saveAndFlush(keyType)).andReturn(keyType);
		replay(keyTypeRepository);
		
		assertSame(keyType , contextMgr.storeKeyType(keyType));
		verify(keyTypeRepository);
	}
	
	@Test
	public void findServices() {
		List<Service> serviceList = new ArrayList<Service>();
		Filter filter = new TestingFilter();
		
		expect(serviceDao.find(filter)).andReturn(serviceList);
		replay(serviceDao);
		
		assertSame(serviceList , contextMgr.findServices(filter));
		verify(serviceDao);
	}
	
	@Test
	public void storeService() {
		Service service = new Service(OperatorTestSupport.createOperator(), "NAME");
		
		serviceDao.saveOrUpdate(service);
		replay(serviceDao);
		
		assertSame(service , contextMgr.storeService(service));
		verify(serviceDao);
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
	
	private ContextMgrImpl contextMgr;
	private FilterDao<Operator> operatorDao;
	private FilterDao<Province> provinceDao;
	private FilterDao<Entity> entityDao;
	private KeyTypeRepository keyTypeRepository;
	private FilterDao<Service> serviceDao;
	
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		contextMgr = new ContextMgrImpl();
		operatorDao = createMock(FilterDao.class);
		contextMgr.setOperatorDao(operatorDao);
		provinceDao = createMock(FilterDao.class);
		contextMgr.setProvinceDao(provinceDao);
		entityDao = createMock(FilterDao.class);
		contextMgr.setEntityDao(entityDao);
		keyTypeRepository = createMock(KeyTypeRepository.class);
		contextMgr.setKeyTypeRepository(keyTypeRepository);
		serviceDao = createMock(FilterDao.class);
		contextMgr.setServiceDao(serviceDao);
	}
	
	@After
	public void tearDown() {
		reset(operatorDao);
		reset(provinceDao);
		reset(entityDao);
		reset(keyTypeRepository);
		reset(serviceDao);
	}

}
