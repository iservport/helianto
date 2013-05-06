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

import org.helianto.core.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.KeyType;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.partner.domain.Account;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity2;
import org.helianto.partner.domain.PrivateEntityKey;
import org.helianto.partner.domain.nature.Agent;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
import org.helianto.partner.domain.nature.Laboratory;
import org.helianto.partner.domain.nature.Manufacturer;
import org.helianto.partner.domain.nature.Supplier;
import org.helianto.partner.domain.nature.TransportPartner;
import org.helianto.partner.test.AbstractPartnerDaoIntegrationTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class PartnerIntegrationTests extends AbstractPartnerDaoIntegrationTest {
	
	@Resource FilterDao<Account> accountDao;
	@Resource BasicDao<PrivateAddress> addressDao;
	@Resource FilterDao<Partner> partnerDao;
	@Resource BasicDao<PartnerKey> partnerKeyDao;
	@Resource BasicDao<PartnerPhone> partnerPhoneDao;
	@Resource FilterDao<PrivateEntity2> privateEntityDao;
	@Resource FilterDao<PartnerCategory> partnerCategoryDao;
//	@Resource FilterDao<Category> categoryDao;
	@Resource FilterDao<PrivateEntityKey> privateEntityKeyDao;
	@Resource FilterDao<KeyType> keyTypeDao;

	@Test
	public void partner() {
		Account account = new Account(entity, "CODE");
		accountDao.saveOrUpdate(account);
		assertEquals(account, accountDao.findUnique(entity, "CODE"));

		PrivateEntity2 partnerRegistry = new PrivateEntity2(entity, "ENTITYALIAS");
		privateEntityDao.saveOrUpdate(partnerRegistry);
		assertEquals(partnerRegistry, privateEntityDao.findUnique(entity, "ENTITYALIAS", 'R'));

		KeyType keyType = new KeyType(entity.getOperator(), "KEYCODE");
		keyTypeDao.saveOrUpdate(keyType);
		
		PrivateEntityKey partnerRegistryKey = new PrivateEntityKey(partnerRegistry, keyType);
		privateEntityKeyDao.saveOrUpdate(partnerRegistryKey);
		assertEquals(partnerRegistryKey, privateEntityKeyDao.findUnique(partnerRegistryKey.getPrivateEntity(), partnerRegistryKey.getKeyType()));

		PartnerPhone partnerPhone = new PartnerPhone(partnerRegistry, 100);
		partnerPhoneDao.saveOrUpdate(partnerPhone);
		assertEquals(partnerPhone, partnerPhoneDao.findUnique(partnerPhone.getPrivateEntity(), 100));

		Partner partner = new Partner(partnerRegistry);
		partnerDao.saveOrUpdate(partner);
		assertEquals(partner, partnerDao.findUnique(partner.getPrivateEntity(), 'P'));

		Agent agent = new Agent(partnerRegistry);
		partnerDao.saveOrUpdate(agent);
		assertEquals(agent, partnerDao.findUnique(agent.getPrivateEntity(), 'A'));

		Customer customer = new Customer(partnerRegistry);
		partnerDao.saveOrUpdate(customer);
		assertEquals(customer, partnerDao.findUnique(customer.getPrivateEntity(), 'C'));

		Division division = new Division(partnerRegistry);
		partnerDao.saveOrUpdate(division);
		assertEquals(division, partnerDao.findUnique(division.getPrivateEntity(), 'D'));

		Laboratory laboratory = new Laboratory(partnerRegistry);
		partnerDao.saveOrUpdate(laboratory);
		assertEquals(laboratory, partnerDao.findUnique(laboratory.getPrivateEntity(), 'L'));

		Manufacturer manufacturer = new Manufacturer(partnerRegistry);
		partnerDao.saveOrUpdate(manufacturer);
		assertEquals(manufacturer, partnerDao.findUnique(manufacturer.getPrivateEntity(), 'M'));

		Supplier supplier = new Supplier(partnerRegistry);
		partnerDao.saveOrUpdate(supplier);
		assertEquals(supplier, partnerDao.findUnique(supplier.getPrivateEntity(), 'S'));

		TransportPartner transport = new TransportPartner(partnerRegistry);
		partnerDao.saveOrUpdate(transport);
		assertEquals(transport, partnerDao.findUnique(transport.getPrivateEntity(), 'T'));

		PartnerKey partnerKey = new PartnerKey(partner, keyType);
		partnerKeyDao.saveOrUpdate(partnerKey);
		assertEquals(partnerKey, partnerKeyDao.findUnique(partnerKey.getPartner(), partnerKey.getKeyType()));

//		Category category = new Category(entity, CategoryGroup.NOT_DEFINED, "CATEGORY");
//		categoryDao.saveOrUpdate(category);
//		PartnerCategory partnerCategory =  new PartnerCategory(partner, category);
//		partnerCategoryDao.saveOrUpdate(partnerCategory);
//		assertEquals(partnerCategory, partnerCategoryDao.findUnique(partner, category));

		PrivateAddress address =  new PrivateAddress(partnerRegistry, 100);
		addressDao.saveOrUpdate(address);
		assertEquals(address, addressDao.findUnique(partnerRegistry, 100));
		
	}

}
