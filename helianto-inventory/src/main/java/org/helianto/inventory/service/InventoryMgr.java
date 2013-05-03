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

import org.helianto.core.filter.Filter;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.ProcessRequirement;
import org.helianto.inventory.domain.Tax;

/**
 * Inventory service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface InventoryMgr {

	/**
	 * Find process requirement.
	 * 
	 * @param filter
	 */
	public List<ProcessRequirement> findProcessRequirements(Filter filter);

	/**
	 * Store agreement.
	 * 
	 * @param requirement
	 */
	public ProcessRequirement storeProcessRequirement(ProcessRequirement requirement);

	/**
	 * Find process agreement.
	 * 
	 * @param filter
	 */
	public List<ProcessAgreement> findProcessAgreement(Filter filter);

	/**
	 * Store process agreement.
	 * 
	 * @param agreement
	 */
	public ProcessAgreement storeProcessAgreement(ProcessAgreement agreement);
	
	/**
	 * Find taxes.
	 * 
	 * @param filter
	 */
	public List<Tax> findTaxes(Filter filter);

	/**
	 * Store tax.
	 * 
	 * @param tax
	 */
	public Tax storeTax(Tax tax);

	

}
