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


package org.helianto.inventory.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.inventory.domain.ProcessAgreement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default <code>ProcessAgreementMgr</code> interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("processAgreementMgr")
public class ProcessAgreementMgrImpl implements ProcessAgreementMgr {

	/**
	 * @deprecated
	 */
	@Transactional
	public ProcessAgreement prepareAgreement(ProcessAgreement target) {
		ProcessAgreement managedTarget = agreementDao.merge(target);
		agreementDao.evict(managedTarget);
		return managedTarget;
	}
	
	@Transactional(readOnly=true)
	public List<ProcessAgreement> findAgreements(Filter agreementFilter) {
    	List<ProcessAgreement> agreementList = (List<ProcessAgreement>) agreementDao.find(agreementFilter);
    	if (logger.isDebugEnabled() && agreementList!=null) {
    		logger.debug("Found agreement list of size {}", agreementList.size());
    	}
    	return agreementList;
	}
	
	@Transactional
	public ProcessAgreement storeAgreement(ProcessAgreement agreement) {
		sequenceMgr.validateInternalNumber(agreement);
		agreementDao.saveOrUpdate(agreement);
		agreementDao.flush();
		return agreement;
	}

	// collabs

	private FilterDao<ProcessAgreement> agreementDao;
	private SequenceMgr sequenceMgr;

	@Resource(name="agreementDao")
	public void setAgreementDao(FilterDao<ProcessAgreement> agreementDao) {
		this.agreementDao = agreementDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(ProcessAgreementMgrImpl.class);

}
