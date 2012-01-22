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

package org.helianto.finance.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.FilterDao;
import org.helianto.finance.CashFlow;
import org.helianto.finance.test.AbstractFinanceDaoIntegrationTest;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class FinanceRepositoryIntegrationTests extends AbstractFinanceDaoIntegrationTest {

	@Resource FilterDao<PrivateEntity> privateEntityDao;
	@Resource FilterDao<Partner> partnerDao;
	@Resource FilterDao<CashFlow> cashFlowDao;

	@Test
	public void finance() {
		
		PrivateEntity privateEntity = new PrivateEntity(entity, "ENTITY");
		privateEntityDao.saveOrUpdate(privateEntity);
		Partner partner = new Partner(privateEntity);
		partnerDao.saveOrUpdate(partner);

		CashFlow cashFlow = new CashFlow(partner);
		cashFlowDao.saveOrUpdate(cashFlow);
		
		assertEquals(cashFlow, cashFlowDao.findUnique(cashFlow.getEntity(), cashFlow.getInternalNumber()));
	}
	
}
