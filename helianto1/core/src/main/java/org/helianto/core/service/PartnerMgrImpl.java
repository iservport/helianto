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
import org.helianto.core.dao.PartnerDao;

/**
 * Default implementation of the 
 * <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImpl implements PartnerMgr {
    
    private PartnerDao partnerDao;

    /**
     * Find <code>PartnerData</code> by <code>Entity</code> and alias.
     */
    public PartnerData findPartnerDataByNaturalId(Entity entity, String alias) {
        return partnerDao.findPartnerDataByNaturalId(entity, alias);
    }
    
    /**
     * Find <code>PartnerData</code> by <code>Entity</code>.
     */
    public List<PartnerData> findPartnerDataByEntity(Entity entity) {
        return partnerDao.findPartnerDataByEntity(entity);
    }
    
    /**
     * Persist <code>Partner</code> or its descendants: 
     * <code>Customer</code>, <code>Supplier</code>, <code>Division</code>, 
     * <code>Agent</code>, <code>Bank</code>, <code>Manufacturer</code>.
     */
    public void persistPartner(Partner partner) {
        partnerDao.persistPartner(partner);
    }
    
    /**
     * Find <code>Partner</code> by <code>PartnerData</code> and sequence.
     */
    public Partner findPartnerByNaturalId(PartnerData partnerData, byte sequence) {
        return partnerDao.findPartnerByNaturalId(partnerData, sequence);
    }

    /**
     * Find <code>Partner</code> by <code>Entity</code>.
     */
    public List<Partner> findPartnerByEntity(Entity entity) {
        return partnerDao.findPartnerByEntity(entity);
    }

    /**
     * Find <code>Partner</code> by <code>Entity</code> and priority.
     */
    public List<Partner> findPartnerByEntity(Entity entity, int priority) {
        return partnerDao.findPartnerByEntity(entity, priority);
    }
    
    /**
     * Remove <code>Partner</code>.
     */
    public void removePartner(Partner partner) {
        partnerDao.removePartner(partner);
    }

    /*
     * Customer
     */
    
    public void persistCustomer(Customer customer) {
        partnerDao.persistPartner(customer);
    }
    
    /**
     * Find <code>Customer</code> by <code>Entity</code>.
     */
    public List<Customer> findCustomerByEntity(Entity entity) {
        return partnerDao.findCustomerByEntity(entity);
    }
    
    /**
     * Find <code>Customer</code> by <code>Entity</code> and priority.
     */
    public List<Customer> findCustomerByEntity(Entity entity, int priority) {
        return partnerDao.findCustomerByEntity(entity, priority);
    }
    
    /**
     * Remove <code>Customer</code>.
     */
    public void removeCustomer(Customer customer) {
        partnerDao.removePartner(customer);
    }

    /*
     * Supplier
     */
    
    public void persistSupplier(Supplier supplier) {
        partnerDao.persistPartner(supplier);
    }
    
    /**
     * Find <code>Supplier</code> by <code>Entity</code>.
     */
    public List<Supplier> findSupplierByEntity(Entity entity) {
        return partnerDao.findSupplierByEntity(entity);
    }
    
    /**
     * Find <code>Supplier</code> by <code>Entity</code> and priority.
     */
    public List<Supplier> findSupplierByEntity(Entity entity, int priority) {
        return partnerDao.findSupplierByEntity(entity, priority);
    }
    
    /**
     * Remove <code>Supplier</code>.
     */
    public void removeSupplier(Supplier supplier) {
        partnerDao.removePartner(supplier);
    }

    /*
     * Division
     */
    
    public void persistDivision(Division division) {
        partnerDao.persistPartner(division);
    }
    
    /**
     * Find <code>Division</code> by <code>Entity</code>.
     */
    public List<Division> findDivisionByEntity(Entity entity) {
        return partnerDao.findDivisionByEntity(entity);
    }
    
    /**
     * Find <code>Division</code> by <code>Entity</code> and priority.
     */
    public List<Division> findDivisionByEntity(Entity entity, int priority) {
        return partnerDao.findDivisionByEntity(entity, priority);
    }
    
    /**
     * Remove <code>Division</code>.
     */
    public void removeDivision(Division division) {
        partnerDao.removePartner(division);
    }

    /*
     * Agent
     */
    
    public void persistAgent(Agent agent) {
        partnerDao.persistPartner(agent);
    }
    
    /**
     * Find <code>Agent</code> by <code>Entity</code>.
     */
    public List<Agent> findAgentByEntity(Entity entity) {
        return partnerDao.findAgentByEntity(entity);
    }
    
    /**
     * Find <code>Agent</code> by <code>Entity</code> and priority.
     */
    public List<Agent> findAgentByEntity(Entity entity, int priority) {
        return partnerDao.findAgentByEntity(entity, priority);
    }
    
    /**
     * Remove <code>Agent</code>.
     */
    public void removeAgent(Agent agent) {
        partnerDao.removePartner(agent);
    }

    /*
     * Bank
     */
    
    public void persistBank(Bank bank) {
        partnerDao.persistPartner(bank);
    }
    
    /**
     * Find <code>Bank</code> by <code>Entity</code>.
     */
    public List<Bank> findBankByEntity(Entity entity) {
        return partnerDao.findBankByEntity(entity);
    }
    
    /**
     * Find <code>Bank</code> by <code>Entity</code> and priority.
     */
    public List<Bank> findBankByEntity(Entity entity, int priority) {
        return partnerDao.findBankByEntity(entity, priority);
    }
    
    /**
     * Remove <code>Bank</code>.
     */
    public void removeBank(Bank bank) {
        partnerDao.removePartner(bank);
    }

    /*
     * Manufacturer
     */
    
    public void persistManufacturer(Manufacturer manufacturer) {
        partnerDao.persistPartner(manufacturer);
    }
    
    /**
     * Find <code>Manufacturer</code> by <code>Entity</code>.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity) {
        return partnerDao.findManufacturerByEntity(entity, -1);
    }

    /**
     * Find <code>Manufacturer</code> by <code>Entity</code> and priority.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority) {
        return partnerDao.findManufacturerByEntity(entity, priority);
    }

    /**
     * Remove <code>Manufacturer</code>.
     */
    public void removeManufacturer(Manufacturer manufacturer) {
        partnerDao.removePartner(manufacturer);
    }

    /*
     * Contact
     */
    
    /**
     * Persist <code>Contact</code>.
     */
    public void persistContact(Contact contact) {
        partnerDao.persistContact(contact);
    }

    /**
     * Remove <code>Contact</code>.
     */
    public void removeContact(Contact contact) {
        partnerDao.removeContact(contact);
    }

    /**
     * Find <code>Contact</code> by <code>PartnerData</code> and <code>Identity</code>.
     */
    public Contact findContactByNaturalId(PartnerData partnerData, Identity identity) {
        return partnerDao.findContactByNaturalId(partnerData, identity);
    }

    /**
     * Find <code>Contact</code> by <code>PartnerData</code>.
     */
    public List<Contact> findContactByPartnerData(PartnerData partnerData) {
        return partnerDao.findContactByPartnerData(partnerData);
    }
    
    // mutators

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }


}
