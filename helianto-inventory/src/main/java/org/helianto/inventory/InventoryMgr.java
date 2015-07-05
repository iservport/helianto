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

package org.helianto.inventory;

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
	 * Store agreement.
	 * 
	 * @param requirement
	 */
	ProcessRequirement storeProcessRequirement(ProcessRequirement requirement);

	/**
	 * Store process agreement.
	 * 
	 * @param agreement
	 */
	ProcessAgreement storeProcessAgreement(ProcessAgreement agreement);
	
	/**
	 * Store tax.
	 * 
	 * @param tax
	 */
	Tax storeTax(Tax tax);

}
