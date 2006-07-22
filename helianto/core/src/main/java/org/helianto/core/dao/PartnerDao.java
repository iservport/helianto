/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under tUserhe License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.dao;

import java.util.List;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Contact;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.PartnerRole;
import org.helianto.core.Supplier;

/**
 * <p>
 * <code>Partner</code> and sub-classes interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public interface PartnerDao {
    
    /**
     * Persist <code>Partner</code>.
     */
    public void persistPartner(Partner partner);
    
    /**
     * Persist <code>PartnerRole</code> or its descendants: 
     * <code>Customer</code>, <code>Supplier</code>, <code>Division</code>, 
     * <code>Agent</code>, <code>Bank</code>, <code>Manufacturer</code>.
     */
	public void persistPartnerRole(PartnerRole partnerRole);
    
    /**
     * Persist <code>Contact</code>.
     */
    public void persistContact(Contact contact);

    /**
     * Find <code>Partner</code> by <code>Entity</code> and alias.
     */
    public Partner findPartnerByEntityAndAlias(Entity entity, String alias);
    
    /**
     * Find <code>Partner</code> by <code>Entity</code>.
     */
    public List<Partner> findPartnerByEntity(Entity entity);
    
    /**
     * Find <code>PartnerRole</code> by <code>Entity</code>.
     */
    public List<PartnerRole> findPartnerRoleByEntity(Entity entity);

    /**
     * Find <code>PartnerRole</code> by <code>Partner</code>.
     */
    public List<PartnerRole> findPartnerRoleByPartner(Partner partner);

    /**
     * Find <code>Customer</code> by <code>Entity</code>.
     */
    public List<Customer> findCustomerByEntity(Entity entity);
    
    /**
     * Find <code>Supplier</code> by <code>Entity</code>.
     */
    public List<Supplier> findSupplierByEntity(Entity entity);
    
    /**
     * Find <code>Division</code> by <code>Entity</code>.
     */
    public List<Division> findDivisionByEntity(Entity entity);
    
    /**
     * Find <code>Agent</code> by <code>Entity</code>.
     */
    public List<Agent> findAgentByEntity(Entity entity);
    
    /**
     * Find <code>Bank</code> by <code>Entity</code>.
     */
    public List<Bank> findBankByEntity(Entity entity);
    
    /**
     * Find <code>Manufacturer</code> by <code>Entity</code>.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity);

    //TODO detail this
    /**
     * Find <code>Manufacturer</code> by <code>Entity</code> and priority.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority);
    
    /**
     * Find <code>Contact</code> by <code>Partner</code>.
     */
    public List<Contact> findContactByPartner(Partner partner);

    /**
     * Remove <code>Partner</code>.
     */
    public void removePartner(Partner partner);

    /**
     * Remove <code>PartnerRole</code>.
     */
    public void removePartnerRole(PartnerRole partnerRole);

    /**
     * Remove <code>Contact</code>.
     */
    public void removeContact(Contact contact);

}
