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

package org.helianto.finance;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.repository.FilterDao;
import org.helianto.finance.test.AbstractFinanceDaoIntegrationTest;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.test.PrivateEntityTestSupport;
import org.helianto.partner.test.PartnerTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class FinanceRepositoryIntegrationTests extends AbstractFinanceDaoIntegrationTest {

	@Resource FilterDao<Partner, PartnerFilter> partnerDao;
	@Resource FilterDao<CashFlow, CashFlowFilter> cashFlowDao;

	@Test
	public void finance() {
		
		PrivateEntity partnerRegistry = PrivateEntityTestSupport.createPartnerRegistry(entity);
		Partner partner = PartnerTestSupport.createPartner(partnerRegistry);
		partnerDao.saveOrUpdate(partner);
		partnerDao.flush();

		CashFlow cashFlow = new CashFlow(partner);
		cashFlowDao.saveOrUpdate(cashFlow);
		
		assertEquals(cashFlow, cashFlowDao.findUnique(cashFlow.getEntity(), cashFlow.getInternalNumber()));
	}
	
}
