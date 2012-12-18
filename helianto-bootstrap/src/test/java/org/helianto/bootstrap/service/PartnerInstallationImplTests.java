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

package org.helianto.bootstrap.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.easymock.EasyMock;
import org.helianto.core.ContextMgr;
import org.helianto.core.SequenceMgr;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.repository.PublicEntityRepository;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.repository.PartnerKeyRepository;
import org.helianto.partner.repository.PartnerRepository;
import org.junit.After;
import org.junit.Before;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerInstallationImplTests {
    
	/*
	 * Customer installation use cases:
	 * 1. There is no public entity previously in db: must be installed first;
	 * 2. There is a previous public entity, install only a new customer;
	 * 3. It is already installed: do nothing;
	 * 4. Case 1, but province is not installed: throw exception.
	 */

//	@Test
	public void installCustomerCase1() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");

//		EasyMock.expect(publicEntityRepository.findByEntityAndEntityAlias(entity, "ALIAS")).andReturn(null);
//		publicEntityRepository.save(EasyMock.isA(PrivateEntity2.class));
//		EasyMock.replay(publicEntityRepository);
//		
//		EasyMock.expect(provincePartnerKeyRepository.findUnique(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
//		EasyMock.replay(provincePartnerKeyRepository);
//		
//		EasyMock.expect(partnerPartnerKeyRepository.findUnique(privateEntity, "D")).andReturn(null);
//		partnerPartnerKeyRepository.saveOrUpdate(EasyMock.isA(Customer.class));
//		partnerPartnerKeyRepository.flush();
//		EasyMock.replay(partnerPartnerKeyRepository);
		
		Customer customer = partnerInstallationMgr.installCustomer(entity, "NAME", partnerAddress, false);
		assertEquals("ALIAS", customer.getEntityAlias());
		assertEquals("NAME", customer.getEntityName());
		assertEquals(province, customer.getProvince());
		assertEquals("address1", customer.getAddress1());
		assertEquals("addressNumber", customer.getAddressNumber());
		assertEquals("addressDetail", customer.getAddressDetail());
		assertEquals("county", customer.getAddress2());
	}

//	@Test
	public void installCustomerCase2() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");

//		EasyMock.expect(publicEntityRepository.findUnique(entity, "ALIAS")).andReturn(privateEntity);
//		publicEntityRepository.save(privateEntity);
//		EasyMock.replay(publicEntityRepository);
//		
//		EasyMock.expect(provincePartnerKeyRepository.findUnique(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
//		EasyMock.replay(provincePartnerKeyRepository);
//		
//		EasyMock.expect(partnerPartnerKeyRepository.findUnique(privateEntity, "D")).andReturn(null);
//		partnerPartnerKeyRepository.saveOrUpdate(EasyMock.isA(Customer.class));
//		partnerPartnerKeyRepository.flush();
//		EasyMock.replay(partnerPartnerKeyRepository);
		
		Customer customer = partnerInstallationMgr.installCustomer(entity, "NAME", partnerAddress, false);
		assertEquals("ALIAS", customer.getEntityAlias());
		assertSame(privateEntity, customer.getPrivateEntity());
	}

//	@Test
	public void installCustomerCase3() {
		PrivateEntity privateEntity = new PrivateEntity(entity, "ALIAS");

//		EasyMock.expect(publicEntityRepository.findUnique(entity, "ALIAS")).andReturn(privateEntity);
//		publicEntityRepository.save(privateEntity);
//		EasyMock.replay(publicEntityRepository);
		
//		EasyMock.expect(provincePartnerKeyRepository.findUnique(entity.getOperator(), partnerAddress.getProvinceCode())).andReturn(province);
//		EasyMock.replay(provincePartnerKeyRepository);
		
		Customer customer = new Customer(privateEntity);
		
		EasyMock.expect(partnerRepository.findByPrivateEntity(privateEntity)).andReturn(customer);
		partnerRepository.save(EasyMock.isA(Customer.class));
		partnerRepository.flush();
		EasyMock.replay(partnerRepository);
		
		assertSame(customer, partnerInstallationMgr.installCustomer(entity, "NAME", partnerAddress, false));
	}

    private PartnerInstallationMgrImpl partnerInstallationMgr;
    
    private PublicEntityRepository publicEntityRepository;
    private PartnerRepository partnerRepository;
	private PartnerKeyRepository partnerKeyRepository;
    private ProvinceRepository provinceRepository;
	private ContextMgr contextMgr;
    private Province province;
    private AbstractAddress partnerAddress;
	private SequenceMgr sequenceMgr;
    
    private Entity entity;
    
	@SuppressWarnings({ "unchecked", "serial" })
	@Before
    public void setUp() {
		Operator operator = new Operator();
		entity = new Entity(operator, "ALIAS");
		publicEntityRepository = EasyMock.createMock(PublicEntityRepository.class);
        partnerRepository = EasyMock.createMock(PartnerRepository.class);
        partnerKeyRepository = EasyMock.createMock(PartnerKeyRepository.class);
        provinceRepository = EasyMock.createMock(ProvinceRepository.class);
        sequenceMgr = EasyMock.createMock(SequenceMgr.class);
        partnerInstallationMgr = new PartnerInstallationMgrImpl(publicEntityRepository, partnerRepository, partnerKeyRepository, provinceRepository, contextMgr);
        
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
    	EasyMock.reset(publicEntityRepository);
    	EasyMock.reset(partnerRepository);
    	EasyMock.reset(provinceRepository);
    	EasyMock.reset(contextMgr);
    	EasyMock.reset(sequenceMgr);
    }

}
