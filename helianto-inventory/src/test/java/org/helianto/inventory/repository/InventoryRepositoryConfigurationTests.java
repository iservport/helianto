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

import org.helianto.core.filter.ListFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.TopLevelNumberedEntityTestSupport;
import org.helianto.inventory.Card;
import org.helianto.inventory.CardSet;
import org.helianto.inventory.Inventory;
import org.helianto.inventory.Movement;
import org.helianto.inventory.Picking;
import org.helianto.inventory.ProcessAgreement;
import org.helianto.inventory.ProcessRequirement;
import org.helianto.inventory.test.AbstractInventoryDaoIntegrationTest;
import org.helianto.partner.Partner;
import org.helianto.partner.test.PartnerTestSupport;
import org.helianto.process.ProcessDocument;
import org.helianto.process.test.ProcessDocumentTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class InventoryRepositoryConfigurationTests extends AbstractInventoryDaoIntegrationTest {

	@Resource FilterDao<ProcessDocument, ListFilter> processDocumentDao;
	@Resource FilterDao<Partner, ListFilter> partnerDao;
	@Resource FilterDao<Card, ListFilter> cardDao;
	@Resource FilterDao<CardSet, ListFilter> cardSetDao;
	@Resource FilterDao<Inventory, ListFilter> inventoryDao;
	@Resource FilterDao<Movement, ListFilter> movementDao;
	@Resource FilterDao<Picking, ListFilter> pickingDao;
	@Resource FilterDao<ProcessRequirement, ListFilter> processRequirementDao;
	@Resource FilterDao<ProcessAgreement, ListFilter> agreementDao;

	@Test
	public void inventory() {
		ProcessDocument processDocument = ProcessDocumentTestSupport.createProcessDocument(entity);
		processDocumentDao.saveOrUpdate(processDocument);

		CardSet cardSet = new CardSet(processDocument, 100L);
		cardSetDao.saveOrUpdate(cardSet);
		assertEquals(cardSet, cardSetDao.findUnique(entity, 100L));

		Card card = new Card(cardSet, 20);
		cardDao.saveOrUpdate(card);
//		assertEquals(card, cardDao.findUnique(cardSet, "010000020"));

		Inventory inventory = TopLevelNumberedEntityTestSupport.create(Inventory.class, entity);
		inventoryDao.saveOrUpdate(inventory);
		assertEquals(inventory, inventoryDao.findUnique(inventory.getEntity(), inventory.getInternalNumber()));

		Movement movement = new Movement(inventory);
		movementDao.saveOrUpdate(movement);
		assertEquals(movement, movementDao.findUnique(movement.getInventoryTransaction(), inventory));

		Picking picking = TopLevelNumberedEntityTestSupport.create(Picking.class, entity);
		pickingDao.saveOrUpdate(picking);
		assertEquals(picking, pickingDao.findUnique(picking.getEntity(), picking.getInternalNumber()));
	
		ProcessRequirement processRequirement = new ProcessRequirement(entity, Long.MAX_VALUE);
		processRequirementDao.saveOrUpdate(processRequirement);
		assertEquals(processRequirement, processRequirementDao.findUnique(entity, Long.MAX_VALUE));

		Partner partner = PartnerTestSupport.createPartner(entity);
		partnerDao.saveOrUpdate(partner);
		ProcessAgreement processAgreement = new ProcessAgreement(partner);
		agreementDao.saveOrUpdate(processAgreement);
		assertEquals(processAgreement, agreementDao.findUnique(processAgreement.getEntity(), processAgreement.getInternalNumber()));

	}
		
}
