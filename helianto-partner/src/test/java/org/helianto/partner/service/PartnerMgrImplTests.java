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
import org.helianto.core.SequenceMgr;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.filter.classic.PartnerFilter;
import org.helianto.partner.repository.PartnerCategoryRepository;
import org.helianto.partner.repository.PartnerKeyRepository;
import org.helianto.partner.repository.PartnerPhoneRepository;
import org.helianto.partner.repository.PartnerRepository;
import org.helianto.partner.repository.PrivateEntityKeyRepository;
import org.helianto.partner.repository.PrivateEntityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImplTests {
    
    private PartnerMgrImpl partnerMgr;
    
	@Test
    public void storePrivateEntity() {
    	PrivateEntity privateEntity = new PrivateEntity();
    	Partner partner = new Partner(privateEntity);
    	
		EasyMock.expect(privateEntityRepository.save(EasyMock.isA(PrivateEntity.class))).andReturn(privateEntity);
    	replay(privateEntityRepository);

		EasyMock.expect(partnerRepository.save(EasyMock.isA(Partner.class))).andReturn(partner);
    	replay(partnerRepository);

    	sequenceMgr.validateInternalNumber(privateEntity);
    	replay(sequenceMgr);

    	assertSame(privateEntity, partnerMgr.storePrivateEntity(privateEntity));
    	verify(privateEntityRepository);
    	verify(sequenceMgr);
    }
    
	@Test
    public void storePrivateEntityKey() {
		PrivateEntityKey privateEntityKey = new PrivateEntityKey();
		
    	expect(privateEntityKeyRepository.saveAndFlush(privateEntityKey)).andReturn(privateEntityKey);
    	replay(privateEntityKeyRepository);
		
    	assertSame(privateEntityKey, partnerMgr.storePrivateEntityKey(privateEntityKey));
    	verify(privateEntityKeyRepository);
    }
    
	@Test
    public void removePartnerRegistry() {
    	PrivateEntity partnerRegistry = new PrivateEntity();
    	
    	privateEntityRepository.delete(partnerRegistry);
    	replay(privateEntityRepository);

    	partnerMgr.removePrivateEntity(partnerRegistry);
    	verify(privateEntityRepository);
    }
	
	// partner...
	
	@Test
	public void findPartners() {
		PartnerFilter partnerFilter = new PartnerFilter();
		List<Partner> partnerList = new ArrayList<Partner>();
		
    	expect(partnerRepository.find(partnerFilter)).andReturn(partnerList);
    	replay(partnerRepository);
		
    	assertSame(partnerList, partnerMgr.findPartners(partnerFilter));
    	verify(partnerRepository);
	}
	
	@Test
	public void storePartner1() {
		Partner partner = new Partner();
		
    	expect(partnerRepository.saveAndFlush(partner)).andReturn(partner);
    	replay(partnerRepository);
		
    	assertSame(partner, partnerMgr.storePartner(partner));
    	verify(partnerRepository);
	}
    
	@Test
	public void storePartner2() {
		Partner partner = new Partner();
		partner.setNewEntityAlias("TEST");
		Entity entity = new Entity();
		
    	expect(partnerRepository.saveAndFlush(partner)).andReturn(partner);
    	replay(partnerRepository);
		
    	assertSame(partner, partnerMgr.storePartner(partner, entity));
    	verify(partnerRepository);
    	
    	assertEquals("TEST", partner.getEntityAlias());
	}

	@Test
	public void storePartnerKey() {
		PartnerKey partnerKey = new PartnerKey();
		
    	expect(partnerKeyRepository.saveAndFlush(partnerKey)).andReturn(partnerKey);
    	replay(partnerKeyRepository);
		
    	assertSame(partnerKey, partnerMgr.storePartnerKey(partnerKey));
    	verify(partnerKeyRepository);
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
		
    	partnerRepository.delete(partner);
    	replay(partnerRepository);
		
    	partnerMgr.removePartner(partner);
    	verify(partnerRepository);
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
		Customer newCustomer = new Customer();

		EasyMock.expect(privateEntityRepository.findByEntityAndEntityAlias(entity, "ALIAS")).andReturn(null);
		EasyMock.expect(privateEntityRepository.saveAndFlush(EasyMock.isA(PrivateEntity.class))).andReturn(privateEntity);
		EasyMock.replay(privateEntityRepository);
		
		EasyMock.expect(provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
		EasyMock.replay(provinceRepository);
		
		EasyMock.expect(partnerRepository.findByPrivateEntityAndType(privateEntity, 'C')).andReturn(null);
    	expect(partnerRepository.saveAndFlush(EasyMock.isA(Customer.class))).andReturn(newCustomer);
		EasyMock.replay(partnerRepository);
		
		Customer customer = partnerMgr.installCustomer(entity, "NAME", partnerAddress, false);
		assertEquals("ALIAS", customer.getEntityAlias());
	}

	@Test
	public void installCustomerCase2() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");
		Customer newCustomer = new Customer();

		EasyMock.expect(privateEntityRepository.findByEntityAndEntityAlias(entity, "ALIAS")).andReturn(privateEntity);
		EasyMock.expect(privateEntityRepository.saveAndFlush(privateEntity)).andReturn(privateEntity);
		EasyMock.replay(privateEntityRepository);
		
		EasyMock.expect(provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
		EasyMock.replay(provinceRepository);
		
		EasyMock.expect(partnerRepository.findByPrivateEntityAndType(privateEntity, 'C')).andReturn(null);
    	expect(partnerRepository.saveAndFlush(EasyMock.isA(Customer.class))).andReturn(newCustomer);
		EasyMock.replay(partnerRepository);
		
		Customer customer = partnerMgr.installCustomer(entity, "NAME", partnerAddress, false);
		assertEquals("ALIAS", customer.getEntityAlias());
		assertSame(privateEntity, customer.getPrivateEntity());
	}

	@Test
	public void installCustomerCase3() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");
		Customer newCustomer = new Customer();

		EasyMock.expect(privateEntityRepository.findByEntityAndEntityAlias(entity, "ALIAS")).andReturn(privateEntity);
		EasyMock.expect(privateEntityRepository.saveAndFlush(privateEntity)).andReturn(privateEntity);
		EasyMock.replay(privateEntityRepository);
		
		EasyMock.expect(provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
		EasyMock.replay(provinceRepository);
		
		Partner customer = new Customer(privateEntity);
		
		EasyMock.expect(partnerRepository.findByPrivateEntityAndType(privateEntity, 'C')).andReturn(customer);
    	expect(partnerRepository.saveAndFlush(EasyMock.isA(Customer.class))).andReturn(newCustomer);
		EasyMock.replay(partnerRepository);
		
		assertSame(customer, partnerMgr.installCustomer(entity, "NAME", partnerAddress, false));
	}

	@Test
    public void storePartnerPhone() {
		PartnerPhone phone = new PartnerPhone();
		
    	expect(partnerPhoneRepository.saveAndFlush(phone)).andReturn(phone);
    	replay(partnerPhoneRepository);
		
    	assertSame(phone, partnerMgr.storePartnerPhone(phone));
    	verify(partnerPhoneRepository);
    }
    
	@Test
    public void storePrivateCategory() {
		PartnerCategory partnerCategory = new PartnerCategory();
		
    	expect(partnerCategoryRepository.saveAndFlush(partnerCategory)).andReturn(partnerCategory);
    	replay(partnerCategoryRepository);
		
    	assertSame(partnerCategory, partnerMgr.storePartnerCategory(partnerCategory));
    	verify(partnerCategoryRepository);
    }
    
    private PrivateEntityRepository privateEntityRepository;
    private PrivateEntityKeyRepository privateEntityKeyRepository;
    private PartnerRepository partnerRepository;
    private ProvinceRepository provinceRepository;
    private PartnerKeyRepository partnerKeyRepository;
    private PartnerPhoneRepository partnerPhoneRepository;
    private PartnerCategoryRepository partnerCategoryRepository;

    private Province province;
    private AbstractAddress partnerAddress;
	private SequenceMgr sequenceMgr;
    
    private Entity entity;
    
	@SuppressWarnings("serial")
	@Before
    public void setUp() {
		Operator operator = new Operator();
		entity = new Entity(operator, "ALIAS");
        partnerMgr = new PartnerMgrImpl();
        privateEntityRepository = EasyMock.createMock(PrivateEntityRepository.class);
        privateEntityKeyRepository = EasyMock.createMock(PrivateEntityKeyRepository.class);
        partnerRepository = EasyMock.createMock(PartnerRepository.class);
        provinceRepository = EasyMock.createMock(ProvinceRepository.class);
        partnerKeyRepository = EasyMock.createMock(PartnerKeyRepository.class);
        partnerPhoneRepository = EasyMock.createMock(PartnerPhoneRepository.class);
        partnerCategoryRepository = EasyMock.createMock(PartnerCategoryRepository.class);
        sequenceMgr = EasyMock.createMock(SequenceMgr.class);
        partnerMgr.setPrivateEntityRepository(privateEntityRepository);
        partnerMgr.setPrivateEntityKeyRepository(privateEntityKeyRepository);
        partnerMgr.setPartnerRepository(partnerRepository);
        partnerMgr.setProvinceRepository(provinceRepository);
        partnerMgr.setPartnerKeyRepository(partnerKeyRepository);
        partnerMgr.setPartnerPhoneRepository(partnerPhoneRepository);
        partnerMgr.setPartnerCategoryRepository(partnerCategoryRepository);
        partnerMgr.setSequenceMgr(sequenceMgr);
        
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
    	EasyMock.reset(privateEntityRepository);
    	EasyMock.reset(privateEntityKeyRepository);
    	EasyMock.reset(partnerRepository);
    	EasyMock.reset(provinceRepository);
    	EasyMock.reset(partnerKeyRepository);
    	EasyMock.reset(partnerPhoneRepository);
    	EasyMock.reset(partnerCategoryRepository);
    	EasyMock.reset(sequenceMgr);
    }

}
