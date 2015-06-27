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

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.inventory.InventoryMgr;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.ProcessRequirement;
import org.helianto.inventory.domain.Tax;
import org.helianto.inventory.repository.ProcessAgreementRepository;
import org.helianto.inventory.repository.ProcessRequirementRepository;
import org.helianto.inventory.repository.TaxRepository;
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

	@Transactional
	public ProcessRequirement storeProcessRequirement(ProcessRequirement requirement) {
		sequenceMgr.validateInternalNumber(requirement);
		return processRequirementRepository.saveAndFlush(requirement);
	}

	@Transactional
	public ProcessAgreement storeProcessAgreement(ProcessAgreement agreement) {
		sequenceMgr.validateInternalNumber(agreement);
		return processAgreementRepository.saveAndFlush(agreement);
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

}
