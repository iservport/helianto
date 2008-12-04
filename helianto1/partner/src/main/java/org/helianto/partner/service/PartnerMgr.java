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

package org.helianto.partner.service;

import java.util.List;

import org.helianto.partner.Address;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryFilter;

/**
 * Default service layer interface for the partner package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerMgr {

    /**
     * Find <code>PartnerRegistry</code>.
     */
	public List<PartnerRegistry> findPartnerRegistries(PartnerRegistryFilter partnerRegistryFilter);
	
    /**
     * Write <code>PartnerRegistry</code> to the datastore.
     */
    public PartnerRegistry storePartnerRegistry(PartnerRegistry partnerRegistry);

    /**
     * Remove <code>PartnerRegistry</code> from the datastore.
     */
    public void removePartnerRegistry(PartnerRegistry partnerRegistry);

    /**
     * Find <code>Partner</code>.
     */
	public List<? extends Partner> findPartners(PartnerFilter partnerFilter);

    /**
     * Write <code>Partner</code> to the datastore.
     */
	public Partner storePartner(Partner partner);

    /**
     * Remove <code>Partner</code> from the datastore.
     */
	public void removePartner(Partner partner);

    /**
     * Write <code>Address</code> to the datastore.
     */
	public Address storeAddress(Address address);

    /**
     * REmove <code>Address</code> from the datastore.
     */
	public PartnerRegistry removeAddress(Address address);

}
