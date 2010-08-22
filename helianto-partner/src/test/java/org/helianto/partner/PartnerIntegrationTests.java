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

import org.helianto.core.KeyType;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.KeyTypeTestSupport;
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
import org.helianto.partner.test.SupplierTestSupport;
import org.helianto.partner.test.TransportPartnerTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class PartnerIntegrationTests extends AbstractPartnerDaoIntegrationTest {
	
	@Resource FilterDao<PublicEntity, PublicEntityFilter> publicEntityDao;
	@Resource FilterDao<Account, AccountFilter> accountDao;
	@Resource FilterDao<PartnerRegistry, PartnerRegistryFilter> partnerRegistryDao;
	@Resource BasicDao<KeyType> keyTypeDao;
	@Resource BasicDao<PartnerRegistryKey> partnerRegistryKeyDao;
	@Resource FilterDao<Partner, PartnerFilter> partnerDao;
	@Resource BasicDao<PartnerKey> partnerKeyDao;
	@Resource BasicDao<Address> addressDao;
	@Resource BasicDao<Phone> phoneDao;

	@Test
	public void partner() {
		PublicEntity publicEntity = new PublicEntity(entity);
		assertEquals(publicEntityDao.merge(publicEntity), 
				publicEntityDao.findUnique(publicEntity.getOperator(), publicEntity.getEntity(), 'P'));

		Account target = AccountTestSupport.createAccount(entity);
		assertEquals(accountDao.merge(target), accountDao.findUnique(target.getEntity(), target.getAccountCode()));

		PartnerRegistry partnerRegistry = partnerRegistryDao.merge(PartnerRegistryTestSupport.createPartnerRegistry(entity));
		assertEquals(partnerRegistry, partnerRegistryDao.findUnique(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias()));

		KeyType keyType = keyTypeDao.merge(KeyTypeTestSupport.createKeyType(entity.getOperator()));
		PartnerRegistryKey partnerRegistryKey = PartnerRegistryKeyTestSupport.createPartnerRegistryKey(partnerRegistry, keyType);
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

		PartnerKey partnerKey = PartnerKeyTestSupport.createPartnerKey(partner, keyType);
		assertEquals(partnerKeyDao.merge(partnerKey), partnerKeyDao.findUnique(partnerKey.getPartner(), partnerKey.getKeyType()));

		Address address = AddressTestSupport.createAddress(partnerRegistry);
		assertEquals(addressDao.merge(address), addressDao.findUnique(address.getPartnerRegistry(), address.getSequence()));

	}

}
