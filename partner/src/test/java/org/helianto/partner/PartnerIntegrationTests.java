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

package org.helianto.partner;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.partner.test.AbstractPartnerDaoIntegrationTest;
import org.helianto.partner.test.AccountTestSupport;
import org.helianto.partner.test.AddressTestSupport;
import org.helianto.partner.test.AgentTestSupport;
import org.helianto.partner.test.CustomerTestSupport;
import org.helianto.partner.test.DivisionTestSupport;
import org.helianto.partner.test.LaboratoryTestSupport;
import org.helianto.partner.test.ManufacturerTestSupport;
import org.helianto.partner.test.PartnerKeyTestSupport;
import org.helianto.partner.test.PartnerRegistryKeyTestSupport;
import org.helianto.partner.test.PartnerRegistryTestSupport;
import org.helianto.partner.test.PartnerTestSupport;
import org.helianto.partner.test.PhoneTestSupport;
import org.helianto.partner.test.SupplierTestSupport;
import org.helianto.partner.test.TransportPartnerTestSupport;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerIntegrationTests extends AbstractPartnerDaoIntegrationTest {

	@Resource FilterDao<Account, AccountFilter> accountDao;
	@Test
	public void account() {
		Account target = AccountTestSupport.createAccount();
		assertEquals(accountDao.merge(target), accountDao.findUnique(target.getEntity(), target.getAccountCode()));
	}
	
	@Resource BasicDao<Address> addressDao;
	@Test
	public void address() {
		Address target = AddressTestSupport.createAddress();
		assertEquals(addressDao.merge(target), addressDao.findUnique(target.getPartnerRegistry(), target.getSequence()));
	}
	
	@Resource FilterDao<Partner, PartnerFilter> partnerDao;
	@Test
	public void partner() {
		Partner target = PartnerTestSupport.createPartner();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'P'));
	}
	// subclasses
	@Test
	public void agent() {
		Agent target = AgentTestSupport.createAgent();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'A'));
	}
	@Test
	public void customer() {
		Customer target = CustomerTestSupport.createCustomer();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'C'));
	}
	@Test
	public void division() {
		Division target = DivisionTestSupport.createDivision();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'D'));
	}
	@Test
	public void laboratory() {
		Laboratory target = LaboratoryTestSupport.createLaboratory();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'L'));
	}
	@Test
	public void manufacturer() {
		Manufacturer target = ManufacturerTestSupport.createManufacturer();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'M'));
	}
	@Test
	public void supplier() {
		Supplier target = SupplierTestSupport.createSupplier();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'S'));
	}
	@Test
	public void transportPartner() {
		TransportPartner target = TransportPartnerTestSupport.createTransportPartner();
		assertEquals(partnerDao.merge(target), partnerDao.findUnique(target.getPartnerRegistry(), 'T'));
	}
	
	@Resource BasicDao<PartnerKey> partnerKeyDao;
	@Test
	public void partnerKey() {
		PartnerKey target = PartnerKeyTestSupport.createPartnerKey();
		assertEquals(partnerKeyDao.merge(target), partnerKeyDao.findUnique(target.getPartner(), target.getKeyType()));
	}

	@Resource FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
	@Test
	public void partnerRegistry() {
		PartnerRegistry target = PartnerRegistryTestSupport.createPartnerRegistry();
		assertEquals(partnerRegistryDao.merge(target), partnerRegistryDao.findUnique(target.getEntity(), target.getPartnerAlias()));
	}
	
	@Resource BasicDao<PartnerRegistryKey> partnerRegistryKeyDao;
	@Test
	public void partnerRegistryKey() {
		PartnerRegistryKey target = PartnerRegistryKeyTestSupport.createPartnerRegistryKey();
		assertEquals(partnerRegistryKeyDao.merge(target), partnerRegistryKeyDao.findUnique(target.getPartnerRegistry(), target.getKeyType()));
	}

	@Resource BasicDao<Phone> phoneDao;
	@Test
	public void phone() {
		Phone target = PhoneTestSupport.createPhone();
		assertEquals(phoneDao.merge(target), phoneDao.findUnique(target.getAddress(), target.getSequence()));
	}

}
