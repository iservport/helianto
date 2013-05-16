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
import org.helianto.inventory.domain.ProcessRequirement;
import org.helianto.inventory.domain.Tax;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default <code>ProcessAgreementMgr</code> interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("inventoryMgr")
public class InventoryMgrImpl implements InventoryMgr {

	@Transactional(readOnly=true)
	public List<ProcessRequirement> findProcessRequirements(Filter filter) {
    	List<ProcessRequirement> requirementList = (List<ProcessRequirement>) processRequirementDao.find(filter);
    	if (logger.isDebugEnabled() && requirementList!=null) {
    		logger.debug("Found requirement list of size {}", requirementList.size());
    	}
    	return requirementList;
	}
	
	@Transactional
	public ProcessRequirement storeProcessRequirement(ProcessRequirement requirement) {
		sequenceMgr.validateInternalNumber(requirement);
		processRequirementDao.saveOrUpdate(requirement);
		return requirement;
	}

	@Transactional(readOnly=true)
	public List<ProcessAgreement> findProcessAgreement(Filter agreementFilter) {
    	List<ProcessAgreement> agreementList = (List<ProcessAgreement>) agreementDao.find(agreementFilter);
    	if (logger.isDebugEnabled() && agreementList!=null) {
    		logger.debug("Found agreement list of size {}", agreementList.size());
    	}
    	return agreementList;
	}
	
	@Transactional
	public ProcessAgreement storeProcessAgreement(ProcessAgreement agreement) {
		sequenceMgr.validateInternalNumber(agreement);
		agreementDao.saveOrUpdate(agreement);
		agreementDao.flush();
		return agreement;
	}
	
	@Transactional(readOnly=true)
	public List<Tax> findTaxes(Filter filter) {
    	List<Tax> taxList = (List<Tax>) taxDao.find(filter);
    	if (logger.isDebugEnabled() && taxList!=null) {
    		logger.debug("Found tax list of size {}", taxList.size());
    	}
    	return taxList;
	}
	
	@Transactional
	public Tax storeTax(Tax tax) {
		taxDao.saveOrUpdate(tax);
		taxDao.flush();
		return tax;
	}

	// collabs

	private FilterDao<ProcessRequirement> processRequirementDao;
	private FilterDao<ProcessAgreement> agreementDao;
	private FilterDao<Tax> taxDao;
	private SequenceMgr sequenceMgr;

	@Resource(name="processRequirementDao")
	public void setProcessRequirementDao(FilterDao<ProcessRequirement> processRequirementDao) {
		this.processRequirementDao = processRequirementDao;
	}
	
	@Resource(name="agreementDao")
	public void setAgreementDao(FilterDao<ProcessAgreement> agreementDao) {
		this.agreementDao = agreementDao;
	}
	
	@Resource(name="taxDao")
	public void setTaxDao(FilterDao<Tax> taxDao) {
		this.taxDao = taxDao;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(InventoryMgrImpl.class);

}
