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

package org.helianto.partner;

import java.util.List;
import java.util.Map;

import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.partner.domain.ContactGroup;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
import org.helianto.partner.form.ContactGroupForm;
import org.helianto.partner.form.PartnerCategoryForm;
import org.helianto.partner.form.PartnerForm;
import org.helianto.partner.form.PartnerPhoneForm;
import org.helianto.partner.form.PrivateAddressForm;
import org.helianto.partner.form.PrivateEntityForm;
import org.helianto.partner.form.PrivateEntityKeyForm;
import org.helianto.user.domain.UserGroup;

/**
 * Default service layer interface for the partner package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerMgr {

	/**
     * Find <code>PrivateEntity</code>.
     * 
     * @param form
     */
	public List<? extends PrivateEntity> findPrivateEntities(PrivateEntityForm form);
	
    /**
     * Write <code>PrivateEntity</code> to the datastore.
     * 
     * @param privateEntity
     */
    public PrivateEntity storePrivateEntity(PrivateEntity privateEntity);

    /**
     * Remove <code>PrivateEntity</code> from the datastore.
     * 
     * @param privateEntity
     */
    public void removePrivateEntity(PrivateEntity privateEntity);

    /**
     * Find <code>Partner</code>.
     * 
     * @param form
     */
	public List<? extends Partner> findPartners(PartnerForm form);
	
    /**
     * Find <code>Partner</code>.
     * 
     * @param partnerFilter
     * @deprecated
     */
	public List<? extends Partner> findPartners(Filter partnerFilter);
	
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
     * Find <code>PartnerPhone</code>.
     * 
     * @param partnerFilter
     */
	public List<PartnerPhone> findPartnerPhones(PartnerPhoneForm form);

    /**
     * Write <code>PartnerPhone</code> to the datastore.
     * 
     * @param partnerKey
     */
	public PartnerPhone storePartnerPhone(PartnerPhone phone);

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
	
	/**
     * Find <code>PrivateAddress</code>.
     * 
     * @param form
     */
	public List<PrivateAddress> findPrivateAddresses(PrivateAddressForm form);
	
    /**
     * Write <code>Address</code> to the datastore.
     * 
     * @param address
     */
	public PrivateAddress storePrivateAddress(PrivateAddress address);

    /**
     * Remove <code>Address</code> from the datastore.
     * 
     * @param address
     */
	public PrivateEntity removePrivateAddress(PrivateAddress address);
	
	/**
     * Find <code>PrivateEntityKey</code>.
     * 
     * @param form
     */
	public List<PrivateEntityKey> findPrivateEntityKeys(PrivateEntityKeyForm form);
	
    /**
     * Write <code>PrivateEntityKey</code> to the datastore.
     * 
     * @param privateEntityKey
     */
	public PrivateEntityKey storePrivateEntityKey(PrivateEntityKey privateEntityKey);
	
	/**
     * Find <code>ContactGroup</code>.
     * 
     * @param form
     */
	public List<? extends UserGroup> findContactGroups(ContactGroupForm form);
	
    /**
     * Find <code>PartnerCategory</code>.
     * 
     * @param partnerFilter
     */
	public List<PartnerCategory> findPartnerCategories(PartnerCategoryForm form);

    /**
     * Write <code>PartnerCategory</code> to the datastore.
     * 
     * @param partnerCategory
     */
	public PartnerCategory storePartnerCategory(PartnerCategory partnerCategory);

	
}