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

package org.helianto.inventory;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.TopLevelNumberedEntityTestSupport;
import org.helianto.inventory.test.AbstractInventoryDaoIntegrationTest;
import org.helianto.inventory.test.CardSetTestSupport;
import org.helianto.inventory.test.CardTestSupport;
import org.helianto.inventory.test.ProcessAgreementTestSupport;
import org.helianto.partner.Partner;
import org.helianto.partner.test.PartnerTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class InventoryRepositoryConfigurationTests extends AbstractInventoryDaoIntegrationTest {

	@Resource BasicDao<Card> cardDao;
	@Resource FilterDao<CardSet, CardSetFilter> cardSetDao;
	@Resource BasicDao<Inventory> inventoryDao;
	@Resource FilterDao<Picking, PickingFilter> pickingDao;
	@Resource FilterDao<ProcessAgreement, ProcessAgreementFilter> agreementDao;

	@Test
	public void inventory() {
		Card card = CardTestSupport.createSample(entity);
		assertEquals(cardDao.merge(card), cardDao.findUnique(card.getCardSet(), card.getCardLabel()));

		CardSet cardSet = CardSetTestSupport.createCardSet(entity);
		assertEquals(cardSetDao.merge(cardSet), cardSetDao.findUnique(cardSet.getEntity(), cardSet.getInternalNumber()));

		Inventory inventory = TopLevelNumberedEntityTestSupport.create(Inventory.class, entity);
		assertEquals(inventoryDao.merge(inventory), inventoryDao.findUnique(inventory.getEntity(), inventory.getInternalNumber()));

		Picking picking = TopLevelNumberedEntityTestSupport.create(Picking.class, entity);
		assertEquals(pickingDao.merge(picking), pickingDao.findUnique(picking.getEntity(), picking.getInternalNumber()));
	
//		Partner partner = PartnerTestSupport.createPartner(entity);
//		ProcessAgreement processAgreement = new ProcessAgreement(partner);
//		agreementDao.saveOrUpdate(processAgreement);
//		assertEquals(processAgreement, agreementDao.findUnique(processAgreement.getEntity(), processAgreement.getInternalNumber()));

	}
	
	// FIXME
//	@Resource BasicDao<Movement> movementDao;
//	@Test
//	public void movement() {
//		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
//		Movement target = MovementTestSupport.create(entity);
//		assertEquals(movementDao.merge(target), movementDao.findUnique(target.getInventoryTransaction(), target.getInventory()));
//	}
	
}
