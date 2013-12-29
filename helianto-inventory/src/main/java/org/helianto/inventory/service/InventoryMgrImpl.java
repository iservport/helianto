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
import org.helianto.inventory.InventoryMgr;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.ProcessRequirement;
import org.helianto.inventory.domain.Tax;
import org.helianto.inventory.filter.ProcessAgreementFilterAdapter;
import org.helianto.inventory.filter.ProcessRequirementFilterAdapter;
import org.helianto.inventory.filter.TaxFilterAdapter;
import org.helianto.inventory.form.ProcessAgreementForm;
import org.helianto.inventory.form.ProcessRequirementForm;
import org.helianto.inventory.form.TaxForm;
import org.helianto.inventory.repository.ProcessAgreementRepository;
import org.helianto.inventory.repository.ProcessRequirementRepository;
import org.helianto.inventory.repository.TaxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default <code>InventoryMgr</code> interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("inventoryMgr")
public class InventoryMgrImpl 
	implements InventoryMgr 
{

	@Transactional(readOnly=true)
	public List<ProcessRequirement> findProcessRequirements(ProcessRequirementForm form) {
		Filter filter = new ProcessRequirementFilterAdapter(form);
    	List<ProcessRequirement> requirementList = (List<ProcessRequirement>) processRequirementRepository.find(filter);
    	if (logger.isDebugEnabled() && requirementList!=null) {
    		logger.debug("Found requirement list of size {}", requirementList.size());
    	}
    	return requirementList;
	}
	
	@Transactional
	public ProcessRequirement storeProcessRequirement(ProcessRequirement requirement) {
		sequenceMgr.validateInternalNumber(requirement);
		return processRequirementRepository.saveAndFlush(requirement);
	}

	@Transactional(readOnly=true)
	public List<ProcessAgreement> findProcessAgreement(ProcessAgreementForm form) {
		Filter filter = new ProcessAgreementFilterAdapter(form);
    	List<ProcessAgreement> agreementList = (List<ProcessAgreement>) processAgreementRepository.find(filter);
    	if (logger.isDebugEnabled() && agreementList!=null) {
    		logger.debug("Found agreement list of size {}", agreementList.size());
    	}
    	return agreementList;
	}
	
	@Transactional
	public ProcessAgreement storeProcessAgreement(ProcessAgreement agreement) {
		sequenceMgr.validateInternalNumber(agreement);
		return processAgreementRepository.saveAndFlush(agreement);
	}
	
	@Transactional(readOnly=true)
	public List<Tax> findTaxes(TaxForm form) {
		Filter filter = new TaxFilterAdapter(form);
    	List<Tax> taxList = (List<Tax>) taxRepository.find(filter);
    	if (logger.isDebugEnabled() && taxList!=null) {
    		logger.debug("Found tax list of size {}", taxList.size());
    	}
    	return taxList;
	}
	
	@Transactional
	public Tax storeTax(Tax tax) {
		return taxRepository.saveAndFlush(tax);
	}

	// collabs

	private ProcessRequirementRepository processRequirementRepository;
	private ProcessAgreementRepository processAgreementRepository;
	private TaxRepository taxRepository;
	private SequenceMgr sequenceMgr;

	@Resource
	public void setProcessRequirementRepository(
			ProcessRequirementRepository processRequirementRepository) {
		this.processRequirementRepository = processRequirementRepository;
	}
	
	@Resource
	public void setProcessAgreementRepository(
			ProcessAgreementRepository processAgreementRepository) {
		this.processAgreementRepository = processAgreementRepository;
	}
	
	@Resource
	public void setTaxRepository(TaxRepository taxRepository) {
		this.taxRepository = taxRepository;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(InventoryMgrImpl.class);

}
