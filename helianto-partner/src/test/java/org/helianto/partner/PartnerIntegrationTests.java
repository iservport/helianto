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

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
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
	
	@Resource FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
	@Resource BasicDao<PartnerRegistryKey> partnerRegistryKeyDao;
	@Resource FilterDao<Partner, PartnerFilter> partnerDao;
	@Resource BasicDao<PartnerKey> partnerKeyDao;
	@Test
	public void partnerRegistry() {
		PartnerRegistry partnerRegistry = partnerRegistryDao.merge(PartnerRegistryTestSupport.createPartnerRegistry());
		assertEquals(partnerRegistry, partnerRegistryDao.findUnique(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias()));

		PartnerRegistryKey partnerRegistryKey = PartnerRegistryKeyTestSupport.createPartnerRegistryKey(partnerRegistry);
		assertEquals(partnerRegistryKeyDao.merge(partnerRegistryKey), partnerRegistryKeyDao.findUnique(partnerRegistryKey.getPartnerRegistry(), partnerRegistryKey.getKeyType()));

		Partner partner = partnerDao.merge(PartnerTestSupport.createPartner(partnerRegistry));
		assertEquals(partner, partnerDao.findUnique(partner.getPartnerRegistry(), 'P'));

		Agent agent = AgentTestSupport.createAgent(partnerRegistry);
		assertEquals(partnerDao.merge(agent), partnerDao.findUnique(agent.getPartnerRegistry(), 'A'));

		Customer customer = CustomerTestSupport.createCustomer(partnerRegistry);
		assertEquals(partnerDao.merge(customer), partnerDao.findUnique(customer.getPartnerRegistry(), 'C'));

		Division division = DivisionTestSupport.createDivision(partnerRegistry);
		assertEquals(partnerDao.merge(division), partnerDao.findUnique(division.getPartnerRegistry(), 'D'));

		Laboratory laboratory = LaboratoryTestSupport.createLaboratory(partnerRegistry);
		assertEquals(partnerDao.merge(laboratory), partnerDao.findUnique(laboratory.getPartnerRegistry(), 'L'));

		Manufacturer manufacturer = ManufacturerTestSupport.createManufacturer(partnerRegistry);
		assertEquals(partnerDao.merge(manufacturer), partnerDao.findUnique(manufacturer.getPartnerRegistry(), 'M'));

		Supplier supplier = SupplierTestSupport.createSupplier(partnerRegistry);
		assertEquals(partnerDao.merge(supplier), partnerDao.findUnique(supplier.getPartnerRegistry(), 'S'));

		TransportPartner transport = TransportPartnerTestSupport.createTransportPartner(partnerRegistry);
		assertEquals(partnerDao.merge(transport), partnerDao.findUnique(transport.getPartnerRegistry(), 'T'));

		PartnerKey target = PartnerKeyTestSupport.createPartnerKey(partner);
		assertEquals(partnerKeyDao.merge(target), partnerKeyDao.findUnique(target.getPartner(), target.getKeyType()));
	}

	@Resource BasicDao<Phone> phoneDao;
	@Test
	public void phone() {
		Phone target = PhoneTestSupport.createPhone();
		assertEquals(phoneDao.merge(target), phoneDao.findUnique(target.getAddress(), target.getSequence()));
	}

}
