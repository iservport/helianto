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

package org.helianto.bootstrap;

import org.helianto.classic.base.AbstractAddress;
import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;

/**
 * Default partner installation service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerInstallationMgr {

    /**
     * Convenience to install a division.
     * 
     * @param entity
     * @param partnerName
     * @param partnerAddress
     * @param reinstall
     */
	public Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall);
	
    /**
     * Convenience to install a customer.
     * 
     * @param entity
     * @param partnerName
     * @param partnerAddress
     * @param reinstall
     */
	public Customer installCustomer(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall);
	
    /**
     * Convenience to install key values.
     * 
     * @param keyValues
     * @param partner
     */
	public void installPartnerKeys(String[] keyValues, Partner partner);
	
}
