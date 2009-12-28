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

package org.helianto.finance.service;

import java.util.List;

import org.helianto.core.filter.Filter;
import org.helianto.finance.CashFlow;
import org.springframework.security.annotation.Secured;

/**
 * Finance service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface FinanceMgr {
	
	/**
	 * Store <code>CashFlow</code> in the datastore and return a managed instance.
	 * 
	 * @param cashFlow
	 */
	@Secured("ROLE_FINANCE_CHANGE")
	public CashFlow storeCashFlow(CashFlow cashFlow);

	/**
	 * Prepare a <code>CashFlow</code> instance to return a
	 * managed instance loaded with lazy collections.
	 * 
	 * @param cashFlow
	 */
	public CashFlow prepareCashFlow(CashFlow cashFlow);

	/**
	 * Find a <code>CashFlow</code> list.
	 * 
	 * @param cashFlowFilter
	 */
	public List<? extends CashFlow> findCashFlows(Filter cashFlowFilter);
	
	/**
	 * Remove the <code>CashFlow</code> from the datastore.
	 * 
	 * @param cashFlow
	 */
	@Secured("ROLE_FINANCE_DEL")
	public void removeCashFlow(CashFlow cashFlow);

}
