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

package org.helianto.inventory.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.domain.KeyType;
import org.helianto.core.repository.FilterDao;
import org.helianto.inventory.Card;
import org.helianto.inventory.CardSet;
import org.helianto.inventory.Inventory;
import org.helianto.inventory.Movement;
import org.helianto.inventory.Picking;
import org.helianto.inventory.ProcessAgreement;
import org.helianto.inventory.ProcessRequirement;
import org.helianto.inventory.Tax;
import org.helianto.inventory.test.AbstractInventoryDaoIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity2;
import org.helianto.process.ProcessDocument;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class InventoryRepositoryConfigurationTests extends AbstractInventoryDaoIntegrationTest {

	@Resource FilterDao<KeyType> keyTypeDao;
	@Resource FilterDao<ProcessDocument> processDocumentDao;
	@Resource FilterDao<PrivateEntity2> privateEntityDao;
	@Resource FilterDao<Partner> partnerDao;
	@Resource FilterDao<Card> cardDao;
	@Resource FilterDao<CardSet> cardSetDao;
	@Resource FilterDao<Inventory> inventoryDao;
	@Resource FilterDao<Movement> movementDao;
	@Resource FilterDao<Picking> pickingDao;
	@Resource FilterDao<ProcessRequirement> processRequirementDao;
	@Resource FilterDao<ProcessAgreement> agreementDao;
	@Resource FilterDao<Tax> taxDao;

	@Test
	public void inventory() {
		ProcessDocument processDocument = new ProcessDocument(entity, "CODE");
		processDocumentDao.saveOrUpdate(processDocument);

		CardSet cardSet = new CardSet(processDocument, 100L);
		cardSetDao.saveOrUpdate(cardSet);
		assertEquals(cardSet, cardSetDao.findUnique(entity, 100L));

		Card card = new Card(cardSet, 20);
		cardDao.saveOrUpdate(card);
//		assertEquals(card, cardDao.findUnique(cardSet, "010000020"));

		Inventory inventory = new Inventory(entity, Long.MAX_VALUE);
		inventoryDao.saveOrUpdate(inventory);
		assertEquals(inventory, inventoryDao.findUnique(entity, Long.MAX_VALUE));
		
		Movement movement = new Movement(inventory);
		movementDao.saveOrUpdate(movement);
		assertEquals(movement, movementDao.findUnique(movement.getInventoryTransaction(), inventory));

		Picking picking = new Picking(entity, Long.MAX_VALUE);
		pickingDao.saveOrUpdate(picking);
		assertEquals(picking, pickingDao.findUnique(entity, Long.MAX_VALUE));
	
		ProcessRequirement processRequirement = new ProcessRequirement(entity, Long.MAX_VALUE);
		processRequirementDao.saveOrUpdate(processRequirement);
		assertEquals(processRequirement, processRequirementDao.findUnique(entity, Long.MAX_VALUE));

		PrivateEntity2 privateEntity = new PrivateEntity2(entity, "ENTITY");
		privateEntityDao.saveOrUpdate(privateEntity);
		Partner partner = new Partner(privateEntity);
		partnerDao.saveOrUpdate(partner);
		ProcessAgreement processAgreement = new ProcessAgreement(partner);
		agreementDao.saveOrUpdate(processAgreement);
		assertEquals(processAgreement, agreementDao.findUnique(processAgreement.getEntity(), processAgreement.getInternalNumber()));

		KeyType keyType = new KeyType(entity.getOperator(), "KEYTYPE");
		keyTypeDao.saveOrUpdate(keyType);
		Tax tax = new Tax(processAgreement, keyType);
		taxDao.saveOrUpdate(tax);
		assertEquals(tax, taxDao.findUnique(processAgreement, keyType));

	}
		
}
