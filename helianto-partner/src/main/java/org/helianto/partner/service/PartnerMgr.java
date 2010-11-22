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
import java.util.Map;

import org.helianto.core.Entity;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.Address;
import org.helianto.partner.Division;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerFilter;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.PrivateEntityFilter;

/**
 * Default service layer interface for the partner package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerMgr {

    /**
     * Find <code>PartnerRegistry</code>.
     * 
     * @param privateEntityFilter
     */
	public List<PrivateEntity> findPartnerRegistries(PrivateEntityFilter privateEntityFilter);
	
    /**
     * Prepare <code>PartnerRegistry</code> to the application layer.
     * 
     * @param privateEntity
     */
    public PrivateEntity preparePartnerRegistry(PrivateEntity privateEntity);

    /**
     * Write <code>PartnerRegistry</code> to the datastore.
     * 
     * @param privateEntity
     */
    public PrivateEntity storePartnerRegistry(PrivateEntity privateEntity);

    /**
     * Remove <code>PartnerRegistry</code> from the datastore.
     * 
     * @param privateEntity
     */
    public void removePartnerRegistry(PrivateEntity privateEntity);

    /**
     * Find <code>Partner</code>.
     * 
     * @param partnerFilter
     */
	public List<? extends Partner> findPartners(PartnerFilter partnerFilter);
	
    /**
     * Write <code>Partner</code> to the datastore.
     * 
     * @param partner
     */
	public Partner storePartner(Partner partner);
	
    /**
     * Write <code>Partner</code> to the datastore.
     * 
     * <p>
     * Requires a newEntityAlias to allow for the owning <code>PrivateEntity</code> creation.
     * </p>
     * 
     * @param partner
     * @param entity
     * @exception IllegalArgumentException
     */
	public Partner storePartner(Partner partner, Entity entity) throws IllegalArgumentException;

    /**
     * Remove <code>Partner</code> from the datastore.
     * 
     * @param partner
     */
	public void removePartner(Partner partner);

    /**
     * Write <code>Address</code> to the datastore.
     * 
     * @param address
     */
	public Address storeAddress(Address address);

    /**
     * Remove <code>Address</code> from the datastore.
     * 
     * @param address
     */
	public PrivateEntity removeAddress(Address address);
	
    /**
     * Load <code>PartnerKey</code> map keyed with the KeyCode.
     * 
     * @param partner
     */
	public Map<String, PartnerKey> loadPartnerKeyMap(Partner partner);
	
    /**
     * Write <code>PartnerKey</code> to the datastore.
     * 
     * @param partnerKey
     */
	public PartnerKey storePartnerKey(PartnerKey partnerKey);
	
    /**
     * Remove <code>PartnerKey</code> from the datastore.
     * 
     * @param partnerKey
     */
	public PrivateEntity removePartnerKey(PartnerKey partnerKey);

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
     * Convenience to install key values.
     * 
     * @param keyValues
     * @param partner
     */
	public void installPartnerKeys(String[] keyValues, Partner partner);
	
}
