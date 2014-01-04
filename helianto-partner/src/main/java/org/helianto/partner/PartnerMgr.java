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

import org.helianto.core.domain.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.core.internal.AbstractAddress;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;
import org.helianto.partner.domain.PrivateSegment;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
import org.helianto.partner.form.ContactGroupForm;
import org.helianto.partner.form.PartnerCategoryForm;
import org.helianto.partner.form.PartnerForm;
import org.helianto.partner.form.PartnerKeyForm;
import org.helianto.partner.form.PartnerPhoneForm;
import org.helianto.partner.form.PrivateAddressForm;
import org.helianto.partner.form.PrivateEntityForm;
import org.helianto.partner.form.PrivateEntityKeyForm;
import org.helianto.partner.form.PrivateSegmentForm;
import org.helianto.user.domain.UserGroup;

/**
 * Partner service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerMgr {

	/**
     * Find <code>PrivateEntity</code>.
     * 
     * @param form
     */
	List<? extends PrivateEntity> findPrivateEntities(PrivateEntityForm form);
	
    /**
     * Write <code>PrivateEntity</code> to the datastore.
     * 
     * @param privateEntity
     */
    PrivateEntity storePrivateEntity(PrivateEntity privateEntity);

    /**
     * Remove <code>PrivateEntity</code> from the datastore.
     * 
     * @param privateEntity
     */
    void removePrivateEntity(PrivateEntity privateEntity);
    
	/**
     * Load <code>PrivateSegment</code>.
     * 
     * @param entity
     */
	PrivateSegment loadPrivateSegment(Entity entity, String segmentAlias);
	
	/**
     * Find <code>PrivateSegment</code>.
     * 
     * @param entity
     */
	List<PrivateSegment> findPrivateSegments(Entity entity);
	
	/**
     * Find <code>PrivateSegment</code>.
     * 
     * @param form
     */
	List<PrivateSegment> findPrivateSegments(PrivateSegmentForm form);
	
    /**
     * Write <code>PrivateSegment</code> to the datastore.
     * 
     * @param privateSegment
     */
	PrivateSegment storePrivateSegment(PrivateSegment privateSegment);

    /**
     * Find <code>Partner</code>.
     * 
     * @param form
     */
	List<? extends Partner> findPartners(PartnerForm form);
	
    /**
     * Find <code>Partner</code>.
     * 
     * @param partnerFilter
     * @deprecated
     */
	List<? extends Partner> findPartners(Filter partnerFilter);
	
    /**
     * Write <code>Partner</code> to the datastore.
     * 
     * @param partner
     */
	Partner storePartner(Partner partner);
	
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
	Partner storePartner(Partner partner, Entity entity) throws IllegalArgumentException;

    /**
     * Remove <code>Partner</code> from the datastore.
     * 
     * @param partner
     */
	void removePartner(Partner partner);

    /**
     * Load <code>PartnerKey</code> map keyed with the KeyCode.
     * 
     * @param partner
     */
	Map<String, PartnerKey> loadPartnerKeyMap(Partner partner);
	
    /**
     * Find <code>PartnerKey</code>.
     * 
     * @param form
     */
	List<PartnerKey> findPartnerKeys(PartnerKeyForm form);
	
    /**
     * Write <code>PartnerKey</code> to the datastore.
     * 
     * @param partnerKey
     */
	PartnerKey storePartnerKey(PartnerKey partnerKey);
	
    /**
     * Remove <code>PartnerKey</code> from the datastore.
     * 
     * @param partnerKey
     */
	PrivateEntity removePartnerKey(PartnerKey partnerKey);
	
    /**
     * Find <code>PartnerPhone</code>.
     * 
     * @param partnerFilter
     */
	List<PartnerPhone> findPartnerPhones(PartnerPhoneForm form);

    /**
     * Write <code>PartnerPhone</code> to the datastore.
     * 
     * @param partnerKey
     */
	PartnerPhone storePartnerPhone(PartnerPhone phone);

    /**
     * Convenience to install a division.
     * 
     * @param entity
     * @param partnerName
     * @param partnerAddress
     * @param reinstall
     */
	Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall);
	
    /**
     * Convenience to install a customer.
     * 
     * @param entity
     * @param partnerName
     * @param partnerAddress
     * @param reinstall
     */
	Customer installCustomer(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall);
	
    /**
     * Convenience to install key values.
     * 
     * @param keyValues
     * @param partner
     */
	void installPartnerKeys(String[] keyValues, Partner partner);
	
	/**
     * Find <code>PrivateAddress</code>.
     * 
     * @param form
     */
	List<PrivateAddress> findPrivateAddresses(PrivateAddressForm form);
	
    /**
     * Write <code>Address</code> to the datastore.
     * 
     * @param address
     */
	PrivateAddress storePrivateAddress(PrivateAddress address);

    /**
     * Remove <code>Address</code> from the datastore.
     * 
     * @param address
     */
	PrivateEntity removePrivateAddress(PrivateAddress address);
	
	/**
     * Find <code>PrivateEntityKey</code>.
     * 
     * @param form
     */
	List<PrivateEntityKey> findPrivateEntityKeys(PrivateEntityKeyForm form);
	
    /**
     * Write <code>PrivateEntityKey</code> to the datastore.
     * 
     * @param privateEntityKey
     */
	PrivateEntityKey storePrivateEntityKey(PrivateEntityKey privateEntityKey);
	
	/**
     * Find <code>ContactGroup</code>.
     * 
     * @param form
     */
	List<? extends UserGroup> findContactGroups(ContactGroupForm form);
	
    /**
     * Find <code>PartnerCategory</code>.
     * 
     * @param partnerFilter
     */
	List<PartnerCategory> findPartnerCategories(PartnerCategoryForm form);

    /**
     * Write <code>PartnerCategory</code> to the datastore.
     * 
     * @param partnerCategory
     */
	PartnerCategory storePartnerCategory(PartnerCategory partnerCategory);

	
}
