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

package org.helianto.partner.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.Customer;
import org.helianto.partner.Partner;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.filter.classic.PartnerFilter;
import org.helianto.partner.filter.classic.PrivateEntityFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImplTests {
    
    private PartnerMgrImpl partnerMgr;
    
	@Test
    public void findPrivateEntities() {
    	PrivateEntityFilter partnerRegistryFilter = new PrivateEntityFilter();
    	List<PrivateEntity> partnerRegistryList = new ArrayList<PrivateEntity>();
    	
    	expect(privateEntityDao.find(partnerRegistryFilter)).andReturn(partnerRegistryList);
    	replay(privateEntityDao);
    	
    	assertSame(partnerRegistryList, partnerMgr.findPrivateEntities(partnerRegistryFilter));
    	verify(privateEntityDao);
    }
    
	@Test
    public void storePartnerRegistry() {
    	PrivateEntity partnerRegistry = new PrivateEntity();
    	
    	privateEntityDao.saveOrUpdate(partnerRegistry);
    	replay(privateEntityDao);

    	assertSame(partnerRegistry, partnerMgr.storePrivateEntity(partnerRegistry));
    	verify(privateEntityDao);
    }
    
	@Test
    public void removePartnerRegistry() {
    	PrivateEntity partnerRegistry = new PrivateEntity();
    	
    	privateEntityDao.remove(partnerRegistry);
    	replay(privateEntityDao);

    	partnerMgr.removePrivateEntity(partnerRegistry);
    	verify(privateEntityDao);
    }
	
	// partner...
	
	@Test
	public void findPartners() {
		PartnerFilter partnerFilter = new PartnerFilter();
		List<Partner> partnerList = new ArrayList<Partner>();
		
    	expect(partnerDao.find(partnerFilter)).andReturn(partnerList);
    	replay(partnerDao);
		
    	assertSame(partnerList, partnerMgr.findPartners(partnerFilter));
    	verify(partnerDao);
	}
	
	@Test
	public void storePartner1() {
		Partner partner = new Partner();
		
    	partnerDao.saveOrUpdate(partner);
    	replay(partnerDao);
		
    	assertSame(partner, partnerMgr.storePartner(partner));
    	verify(partnerDao);
	}
    
	@Test
	public void storePartner2() {
		Partner partner = new Partner();
		partner.setNewEntityAlias("TEST");
		Entity entity = new Entity();
		
    	partnerDao.saveOrUpdate(partner);
    	replay(partnerDao);
		
    	assertSame(partner, partnerMgr.storePartner(partner, entity));
    	verify(partnerDao);
    	
    	assertEquals("TEST", partner.getEntityAlias());
	}

	@Test(expected=IllegalArgumentException.class)
	public void storePartner3() {
		Partner partner = new Partner();
		Entity entity = new Entity();
		
    	partnerMgr.storePartner(partner, entity);
	}

	@Test
	public void removePartner() {
		Partner partner = new Partner();
		
    	partnerDao.remove(partner);
    	replay(partnerDao);
		
    	partnerMgr.removePartner(partner);
    	verify(partnerDao);
	}
	
	@Test
	public void installDivision() {
		
	}
	
	/*
	 * Customer installation use cases:
	 * 1. There is no public entity previously in db: must be installed first;
	 * 2. There is a previous public entity, install only a new customer;
	 * 3. It is already installed: do nothing;
	 * 4. Case 1, but province is not installed: throw exception.
	 */

	@Test
	public void installCustomerCase1() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");

		EasyMock.expect(privateEntityDao.findUnique(entity, "ALIAS")).andReturn(null);
		privateEntityDao.saveOrUpdate(EasyMock.isA(PrivateEntity.class));
		EasyMock.replay(privateEntityDao);
		
		EasyMock.expect(provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
		EasyMock.replay(provinceDao);
		
		EasyMock.expect(partnerDao.findUnique(privateEntity, "D")).andReturn(null);
		partnerDao.saveOrUpdate(EasyMock.isA(Customer.class));
		EasyMock.replay(partnerDao);
		
		Customer customer = partnerMgr.installCustomer(entity, "NAME", partnerAddress, false);
		assertEquals("ALIAS", customer.getEntityAlias());
		assertEquals("NAME", customer.getEntityName());
		assertEquals(province, customer.getProvince());
		assertEquals("address1", customer.getAddress1());
		assertEquals("addressNumber", customer.getAddressNumber());
		assertEquals("addressDetail", customer.getAddressDetail());
		assertEquals("county", customer.getAddress2());
	}

	@Test
	public void installCustomerCase2() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");

		EasyMock.expect(privateEntityDao.findUnique(entity, "ALIAS")).andReturn(privateEntity);
		privateEntityDao.saveOrUpdate(privateEntity);
		EasyMock.replay(privateEntityDao);
		
		EasyMock.expect(provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
		EasyMock.replay(provinceDao);
		
		EasyMock.expect(partnerDao.findUnique(privateEntity, "D")).andReturn(null);
		partnerDao.saveOrUpdate(EasyMock.isA(Customer.class));
		EasyMock.replay(partnerDao);
		
		Customer customer = partnerMgr.installCustomer(entity, "NAME", partnerAddress, false);
		assertEquals("ALIAS", customer.getEntityAlias());
		assertSame(privateEntity, customer.getPrivateEntity());
	}

	@Test
	public void installCustomerCase3() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");

		EasyMock.expect(privateEntityDao.findUnique(entity, "ALIAS")).andReturn(privateEntity);
		privateEntityDao.saveOrUpdate(privateEntity);
		EasyMock.replay(privateEntityDao);
		
		EasyMock.expect(provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
		EasyMock.replay(provinceDao);
		
		Customer customer = new Customer(privateEntity);
		
		EasyMock.expect(partnerDao.findUnique(privateEntity, "D")).andReturn(customer);
		partnerDao.saveOrUpdate(EasyMock.isA(Customer.class));
		EasyMock.replay(partnerDao);
		
		assertSame(customer, partnerMgr.installCustomer(entity, "NAME", partnerAddress, false));
	}

    private FilterDao<PrivateEntity> privateEntityDao;
    private FilterDao<Partner> partnerDao;
    private BasicDao<Province> provinceDao;
    private Province province;
    private AbstractAddress partnerAddress;
    
    private Entity entity;
    
	@SuppressWarnings({ "unchecked", "serial" })
	@Before
    public void setUp() {
		Operator operator = new Operator();
		entity = new Entity(operator, "ALIAS");
        partnerMgr = new PartnerMgrImpl();
        privateEntityDao = EasyMock.createMock(FilterDao.class);
        partnerDao = EasyMock.createMock(FilterDao.class);
        provinceDao = EasyMock.createMock(FilterDao.class);
        partnerMgr.setPrivateEntityDao(privateEntityDao);
        partnerMgr.setPartnerDao(partnerDao);
        partnerMgr.setProvinceDao(provinceDao);
        
		province = new Province(entity.getOperator(), "CODE");
        partnerAddress = new AbstractAddress(province) {};
        partnerAddress.appendStreet("address1", "addressNumber", "addressDetail", "county");

		assertEquals("address1", partnerAddress.getAddress1());
		assertEquals("addressNumber", partnerAddress.getAddressNumber());
		assertEquals("addressDetail", partnerAddress.getAddressDetail());
		assertEquals("county", partnerAddress.getAddress2());
	}
    
    @After
    public void tearDown() {
    	EasyMock.reset(privateEntityDao);
    	EasyMock.reset(partnerDao);
    	EasyMock.reset(provinceDao);
    }

}
