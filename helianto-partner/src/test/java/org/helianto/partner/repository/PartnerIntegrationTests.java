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

package org.helianto.partner.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.KeyType;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.partner.Account;
import org.helianto.partner.Address;
import org.helianto.partner.Agent;
import org.helianto.partner.Customer;
import org.helianto.partner.Division;
import org.helianto.partner.Laboratory;
import org.helianto.partner.Manufacturer;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.Phone;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.PrivateEntityKey;
import org.helianto.partner.PublicAddress;
import org.helianto.partner.PublicEntity;
import org.helianto.partner.Supplier;
import org.helianto.partner.TransportPartner;
import org.helianto.partner.test.AbstractPartnerDaoIntegrationTest;
import org.helianto.partner.test.AccountTestSupport;
import org.helianto.partner.test.AddressTestSupport;
import org.helianto.partner.test.PartnerKeyTestSupport;
import org.helianto.partner.test.PartnerTestSupport;
import org.helianto.partner.test.PrivateEntityKeyTestSupport;
import org.helianto.partner.test.PrivateEntityTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class PartnerIntegrationTests extends AbstractPartnerDaoIntegrationTest {
	
	@Resource FilterDao<PublicEntity> publicEntityDao;
	@Resource FilterDao<Account> accountDao;
	@Resource FilterDao<PrivateEntity> privateEntityDao;
	@Resource BasicDao<KeyType> keyTypeDao;
	@Resource BasicDao<PrivateEntityKey> privateEntityKeyDao;
	@Resource FilterDao<Partner> partnerDao;
	@Resource BasicDao<PartnerKey> partnerKeyDao;
	@Resource BasicDao<Address> addressDao;
	@Resource FilterDao<PublicAddress> publicAddressDao;
	@Resource BasicDao<Phone> phoneDao;

	@Test
	public void partner() {
		PublicEntity publicEntity = new PublicEntity(entity);
		assertEquals(publicEntityDao.merge(publicEntity), 
				publicEntityDao.findUnique(publicEntity.getOperator(), publicEntity.getEntity(), 'P'));

		Account target = AccountTestSupport.createAccount(entity);
		assertEquals(accountDao.merge(target), accountDao.findUnique(target.getEntity(), target.getAccountCode()));

		PrivateEntity partnerRegistry = privateEntityDao.merge(PrivateEntityTestSupport.createPartnerRegistry(entity));
		assertEquals(partnerRegistry, privateEntityDao.findUnique(partnerRegistry.getEntity(), partnerRegistry.getPartnerAlias()));

		KeyType keyType = keyTypeDao.merge(KeyTypeTestSupport.createKeyType(entity.getOperator()));
		PrivateEntityKey partnerRegistryKey = PrivateEntityKeyTestSupport.createPartnerRegistryKey(partnerRegistry, keyType);
		privateEntityKeyDao.saveOrUpdate(partnerRegistryKey);
		assertEquals(partnerRegistryKey, privateEntityKeyDao.findUnique(partnerRegistryKey.getPrivateEntity(), partnerRegistryKey.getKeyType()));

		Partner partner = partnerDao.merge(PartnerTestSupport.createPartner(partnerRegistry));
		assertEquals(partner, partnerDao.findUnique(partner.getPrivateEntity(), 'P'));

		Agent agent = new Agent(partnerRegistry);
		assertEquals(partnerDao.merge(agent), partnerDao.findUnique(agent.getPrivateEntity(), 'A'));

		Customer customer = new Customer(partnerRegistry);
		assertEquals(partnerDao.merge(customer), partnerDao.findUnique(customer.getPrivateEntity(), 'C'));

		Division division = new Division(partnerRegistry);
		assertEquals(partnerDao.merge(division), partnerDao.findUnique(division.getPrivateEntity(), 'D'));

		Laboratory laboratory = new Laboratory(partnerRegistry);
		assertEquals(partnerDao.merge(laboratory), partnerDao.findUnique(laboratory.getPrivateEntity(), 'L'));

		Manufacturer manufacturer = new Manufacturer(partnerRegistry);
		assertEquals(partnerDao.merge(manufacturer), partnerDao.findUnique(manufacturer.getPrivateEntity(), 'M'));

		Supplier supplier = new Supplier(partnerRegistry);
		assertEquals(partnerDao.merge(supplier), partnerDao.findUnique(supplier.getPrivateEntity(), 'S'));

		TransportPartner transport = new TransportPartner(partnerRegistry);
		assertEquals(partnerDao.merge(transport), partnerDao.findUnique(transport.getPrivateEntity(), 'T'));

		PartnerKey partnerKey = PartnerKeyTestSupport.createPartnerKey(partner, keyType);
		assertEquals(partnerKeyDao.merge(partnerKey), partnerKeyDao.findUnique(partnerKey.getPartner(), partnerKey.getKeyType()));

		Address address = AddressTestSupport.createAddress(partnerRegistry);
		assertEquals(addressDao.merge(address), addressDao.findUnique(address.getPartnerRegistry(), address.getSequence()));
		
		PublicAddress publicAddress = new PublicAddress(entity.getOperator(), "POSTALCODE");
		publicAddressDao.saveOrUpdate(publicAddress);
		assertEquals(publicAddress, publicAddressDao.findUnique(entity.getOperator(), "POSTALCODE"));

	}

}
