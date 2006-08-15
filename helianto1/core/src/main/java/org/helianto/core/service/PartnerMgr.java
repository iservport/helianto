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

package org.helianto.core.service;

import java.util.List;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Contact;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.PartnerData;
import org.helianto.core.Supplier;

/**
 * <code>Partner</code> service layer interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PartnerMgr {

    /**
     * Find <code>PartnerData</code> by <code>Entity</code> and alias.
     */
    public PartnerData findPartnerDataByNaturalId(Entity entity, String alias);

    /**
     * Find <code>PartnerData</code> by <code>Entity</code>.
     */
    public List<PartnerData> findPartnerDataByEntity(Entity entity);

    /**
     * Persist <code>Partner</code> or its descendants: 
     * <code>Customer</code>, <code>Supplier</code>, <code>Division</code>, 
     * <code>Agent</code>, <code>Bank</code>, <code>Manufacturer</code>.
     */
    public void persistPartner(Partner partner);

    /**
     * Find <code>Partner</code> by <code>PartnerData</code> and sequence.
     */
    public Partner findPartnerByNaturalId(PartnerData partnerData, byte sequence);

    /**
     * Find <code>Partner</code> by <code>Entity</code>.
     */
    public List<Partner> findPartnerByEntity(Entity entity);

    /**
     * Find <code>Partner</code> by <code>Entity</code> and priority.
     */
    public List<Partner> findPartnerByEntity(Entity entity, int priority);

    /**
     * Remove <code>Partner</code>.
     */
    public void removePartner(Partner partner);

    /**
     * Persist <code>Customer</code>.
     */
    public void persistCustomer(Customer customer);

    /**
     * Find <code>Customer</code> by <code>Entity</code>.
     */
    public List<Customer> findCustomerByEntity(Entity entity);

    /**
     * Find <code>Customer</code> by <code>Entity</code> and priority.
     */
    public List<Customer> findCustomerByEntity(Entity entity, int priority);

    /**
     * Remove <code>Customer</code>.
     */
    public void removeCustomer(Customer customer);

    /**
     * Persist <code>Supplier</code>.
     */
    public void persistSupplier(Supplier supplier);

    /**
     * Find <code>Supplier</code> by <code>Entity</code>.
     */
    public List<Supplier> findSupplierByEntity(Entity entity);

    /**
     * Find <code>Supplier</code> by <code>Entity</code> and priority.
     */
    public List<Supplier> findSupplierByEntity(Entity entity, int priority);

    /**
     * Remove <code>Supplier</code>.
     */
    public void removeSupplier(Supplier supplier);

    /**
     * Persist <code>Division</code>.
     */
    public void persistDivision(Division division);

    /**
     * Find <code>Division</code> by <code>Entity</code>.
     */
    public List<Division> findDivisionByEntity(Entity entity);

    /**
     * Find <code>Division</code> by <code>Entity</code> and priority.
     */
    public List<Division> findDivisionByEntity(Entity entity, int priority);

    /**
     * Remove <code>Division</code>.
     */
    public void removeDivision(Division division);

    /**
     * Persist <code>Agent</code>.
     */
    public void persistAgent(Agent agent);

    /**
     * Find <code>Agent</code> by <code>Entity</code>.
     */
    public List<Agent> findAgentByEntity(Entity entity);

    /**
     * Find <code>Agent</code> by <code>Entity</code> and priority.
     */
    public List<Agent> findAgentByEntity(Entity entity, int priority);

    /**
     * Remove <code>Agent</code>.
     */
    public void removeAgent(Agent agent);

    /**
     * Persist <code>Bank</code>.
     */
    public void persistBank(Bank bank);

    /**
     * Find <code>Bank</code> by <code>Entity</code>.
     */
    public List<Bank> findBankByEntity(Entity entity);

    /**
     * Find <code>Bank</code> by <code>Entity</code> and priority.
     */
    public List<Bank> findBankByEntity(Entity entity, int priority);

    /**
     * Remove <code>Bank</code>.
     */
    public void removeBank(Bank bank);

    /**
     * Persist <code>Manufacturer</code>.
     */
    public void persistManufacturer(Manufacturer manufacturer);

    /**
     * Find <code>Manufacturer</code> by <code>Entity</code>.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity);

    /**
     * Find <code>Manufacturer</code> by <code>Entity</code> and priority.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity,
            int priority);

    /**
     * Remove <code>Manufacturer</code>.
     */
    public void removeManufacturer(Manufacturer manufacturer);

    /**
     * Persist <code>Contact</code>.
     */
    public void persistContact(Contact contact);

    /**
     * Remove <code>Contact</code>.
     */
    public void removeContact(Contact contact);

    /**
     * Find <code>Contact</code> by <code>PartnerData</code> and <code>Identity</code>.
     */
    public Contact findContactByNaturalId(PartnerData partnerData,
            Identity identity);

    /**
     * Find <code>Contact</code> by <code>PartnerData</code>.
     */
    public List<Contact> findContactByPartnerData(PartnerData partnerData);

}