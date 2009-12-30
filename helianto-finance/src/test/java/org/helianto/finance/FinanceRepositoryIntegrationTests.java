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

import org.helianto.core.Entity;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.finance.test.AbstractFinanceDaoIntegrationTest;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class FinanceRepositoryIntegrationTests extends AbstractFinanceDaoIntegrationTest {

	@Resource
	FilterDao<CashFlow, CashFlowFilter> cashFlowDao;

	@Test
	public void cashFlow() {
		Entity entity = entityDao.merge(EntityTestSupport.createEntity());
		CashFlow target = cashFlowDao.merge(new CashFlow(entity));
		assertEquals(target, cashFlowDao.findUnique(target.getEntity(), target.getInternalNumber()));
	}

}
