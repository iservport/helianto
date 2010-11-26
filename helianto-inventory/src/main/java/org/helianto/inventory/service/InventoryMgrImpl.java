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

import org.helianto.core.filter.ListFilter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.inventory.ProcessAgreement;
import org.helianto.inventory.ProcessRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default <code>ProcessAgreementMgr</code> interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("inventoryMgr")
public class InventoryMgrImpl implements InventoryMgr {

	public List<ProcessRequirement> findProcessRequirements(ListFilter filter) {
    	List<ProcessRequirement> requirementList = (List<ProcessRequirement>) processRequirementDao.find(filter);
    	if (logger.isDebugEnabled() && requirementList!=null) {
    		logger.debug("Found requirement list of size {}", requirementList.size());
    	}
    	return requirementList;
	}
	
	public ProcessRequirement storeProcessRequirement(ProcessRequirement requirement) {
		sequenceMgr.validateInternalNumber(requirement);
		processRequirementDao.saveOrUpdate(requirement);
		return requirement;
	}

	public List<ProcessAgreement> findProcessAgreement(ListFilter agreementFilter) {
    	List<ProcessAgreement> agreementList = (List<ProcessAgreement>) agreementDao.find(agreementFilter);
    	if (logger.isDebugEnabled() && agreementList!=null) {
    		logger.debug("Found agreement list of size {}", agreementList.size());
    	}
    	return agreementList;
	}
	
	public ProcessAgreement storeProcessAgreement(ProcessAgreement agreement) {
		sequenceMgr.validateInternalNumber(agreement);
		return agreementDao.merge(agreement);
	}

	// collabs

	private FilterDao<ProcessRequirement, ListFilter> processRequirementDao;
	private FilterDao<ProcessAgreement, ListFilter> agreementDao;
	private SequenceMgr sequenceMgr;

	@Resource(name="processRequirementDao")
	public void setProcessRequirementDao(FilterDao<ProcessRequirement, ListFilter> processRequirementDao) {
		this.processRequirementDao = processRequirementDao;
	}
	
	@Resource(name="agreementDao")
	public void setAgreementDao(FilterDao<ProcessAgreement, ListFilter> agreementDao) {
		this.agreementDao = agreementDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(InventoryMgrImpl.class);

}
