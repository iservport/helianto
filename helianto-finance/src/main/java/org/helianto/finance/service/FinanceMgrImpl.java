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

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.finance.CashFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation for <code>FinanceMgr</code> internface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("financeMgr")
public class FinanceMgrImpl implements FinanceMgr {

	public List<? extends CashFlow> findCashFlows(Filter filter) {
        List<? extends CashFlow> cashFlowList = (List<? extends CashFlow>) cashFlowDao.find(filter);
        if (logger.isDebugEnabled() && cashFlowList.size()>0) {
            logger.debug("Found {} item(s)", cashFlowList.size());
        }
        return cashFlowList ;
	}

	public CashFlow prepareCashFlow(CashFlow cashFlow) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeCashFlow(CashFlow cashFlow) {
		cashFlowDao.remove(cashFlow);
	}

	public CashFlow storeCashFlow(CashFlow cashFlow) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private FilterDao<CashFlow> cashFlowDao;
	
	@Resource(name="cashFlowDao")
	public void setCashFlowDao(FilterDao<CashFlow> cashFlowDao) {
		this.cashFlowDao = cashFlowDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(FinanceMgrImpl.class);

}
