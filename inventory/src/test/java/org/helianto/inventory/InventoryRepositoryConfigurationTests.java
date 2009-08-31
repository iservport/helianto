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

import org.helianto.core.Entity;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.test.EntityTestSupport;
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
	@Test
	public void card() {
		Card target = CardTestSupport.createSample();
		assertEquals(cardDao.merge(target), cardDao.findUnique(target.getCardSet(), target.getCardLabel()));
	}
	
	@Resource FilterDao<CardSet, CardSetFilter> cardSetDao;
	@Test
	public void cardSet() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		CardSet target = CardSetTestSupport.createCardSet(entity);
		assertEquals(cardSetDao.merge(target), cardSetDao.findUnique(target.getEntity(), target.getInternalNumber()));
	}

	@Resource FilterDao<Picking, PickingFilter> pickingDao;
	@Test
	public void picking() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		Picking target = TopLevelNumberedEntityTestSupport.create(Picking.class, entity);
		assertEquals(pickingDao.merge(target), pickingDao.findUnique(target.getEntity(), target.getInternalNumber()));
	}

	@Resource FilterDao<ProcessAgreement, ProcessAgreementFilter> agreementDao;
	@Test
	public void agreement() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		Partner partner = PartnerTestSupport.createPartner(entity);
		ProcessAgreement target = ProcessAgreementTestSupport.createProcessAgreement(partner);
		assertEquals(agreementDao.merge(target), agreementDao.findUnique(target.getEntity(), target.getInternalNumber()));
	}

}
