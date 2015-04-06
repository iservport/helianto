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

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.domain.Service;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.ServiceFormFilterAdapter;
import org.helianto.core.form.ProvinceForm;
import org.helianto.core.form.ServiceForm;
import org.helianto.core.repository.EntityRepository;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.repository.OperatorRepository;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.repository.ServiceRepository;
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
	public void findAllContexts() {
		List<Operator> operatorList = OperatorTestSupport.createOperatorList(1);
		
		expect(contextRepository.findAll()).andReturn(operatorList);
		replay(contextRepository);
		
		assertSame(operatorList , contextMgr.findAllContexts());
		verify(contextRepository);
	}
	
	@Test
	public void findOneContext() {
		Operator operator = new Operator();
		
		expect(contextRepository.findByOperatorName("NAME")).andReturn(operator);
		replay(contextRepository);
		
		assertSame(operator , contextMgr.findOneContext("NAME"));
		verify(contextRepository);
	}
	
	@Test
	public void storeContext() {
		Operator operator = OperatorTestSupport.createOperator();
		
		expect(contextRepository.saveAndFlush(operator)).andReturn(operator);
		replay(contextRepository);
		
		assertSame(operator , contextMgr.storeContext(operator));
		verify(contextRepository);
	}
	
	@Test
	public void findProvinces() {
		List<Province> provinceList = new ArrayList<Province>();
		ProvinceForm form = new EasyMockSupport().createMock(ProvinceForm.class);
		
		expect(provinceRepository.find(EasyMock.isA(Filter.class))).andReturn(provinceList);
		replay(provinceRepository);
		
		assertSame(provinceList , contextMgr.findProvinces(form));
		verify(provinceRepository);
	}
	
	@Test
	public void storeProvince() {
		Province province = new Province();
		Province managedProvince = new Province();
		
		expect(provinceRepository.saveAndFlush(province)).andReturn(managedProvince);
		replay(provinceRepository);
		
		assertSame(managedProvince , contextMgr.storeProvince(province));
		verify(provinceRepository);
	}
	
	@Test
	public void storeEntity() {
		Entity entity = EntityTestSupport.createEntity();
		
		expect(entityRepository.saveAndFlush(entity)).andReturn(entity);
		replay(entityRepository);
		
		assertSame(entity , contextMgr.storeEntity(entity));
		verify(entityRepository);
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
	public void findServicesByOperator() {
		Operator operator = new Operator("DEFAULT");
		List<Service> serviceList = new ArrayList<Service>();
		
		expect(serviceRepository.findByOperator(operator)).andReturn(serviceList);
		replay(serviceRepository);
		
		assertSame(serviceList , contextMgr.findServices(operator));
		verify(serviceRepository);
	}
	
	@Test
	public void findServicesByForm() {
		List<Service> serviceList = new ArrayList<Service>();
		ServiceForm form = createMock(ServiceForm.class);
		
		expect(serviceRepository.find(isA(ServiceFormFilterAdapter.class))).andReturn(serviceList);
		replay(serviceRepository);
		
		assertSame(serviceList , contextMgr.findServices(form));
		verify(serviceRepository);
	}
	
	@Test
	public void storeService() {
		Service service = new Service(OperatorTestSupport.createOperator(), "NAME");
		
		expect(serviceRepository.saveAndFlush(service)).andReturn(service);
		replay(serviceRepository);
		
		assertSame(service , contextMgr.storeService(service));
		verify(serviceRepository);
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
//		expect(contextRepository.save(operator)).andReturn(managedOperator);
//		replay(contextRepository);
//		
//		Map<String, String> serviceNameMap = namespaceMgr.loadServiceNameMap(operator, userRole);
//		assertSame(service.getServiceName() , serviceNameMap.get("2147483647"));
//		assertSame(service, userRole.getService());
//		verify(contextRepository);
//	}
	
	private ContextMgrImpl contextMgr;
	private OperatorRepository contextRepository;
	private ProvinceRepository provinceRepository;
	private EntityRepository entityRepository;
	private KeyTypeRepository keyTypeRepository;
	private ServiceRepository serviceRepository;
	
	
	@Before
	public void setUp() {
		contextMgr = new ContextMgrImpl();
		contextRepository = createMock(OperatorRepository.class);
		contextMgr.setOperatorRepository(contextRepository);
		provinceRepository = createMock(ProvinceRepository.class);
		contextMgr.setProvinceRepository(provinceRepository);
		entityRepository = createMock(EntityRepository.class);
		contextMgr.setEntityRepository(entityRepository);
		keyTypeRepository = createMock(KeyTypeRepository.class);
		contextMgr.setKeyTypeRepository(keyTypeRepository);
		serviceRepository = createMock(ServiceRepository.class);
		contextMgr.setServiceRepository(serviceRepository);
	}
	
	@After
	public void tearDown() {
		reset(contextRepository);
		reset(provinceRepository);
		reset(entityRepository);
		reset(keyTypeRepository);
		reset(serviceRepository);
	}

}
