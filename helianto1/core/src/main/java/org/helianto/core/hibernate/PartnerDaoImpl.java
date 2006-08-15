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

package org.helianto.core.hibernate;

import java.util.ArrayList;
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
 * Default implementation for <code>PartnerDao</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerDaoImpl extends GenericDaoImpl implements PartnerDao {
    
    /*
     * PartnerData
     */

    public void persistPartnerData(PartnerData partnerData) {
        merge(partnerData);
    }

    public PartnerData findPartnerDataByNaturalId(Entity entity, String alias) {
        return (PartnerData) findUnique(PARTNERDATA1_QRY, entity, alias);
    }
    
    public List<PartnerData> findPartnerDataByEntity(Entity entity) {
        return (ArrayList<PartnerData>) find(PARTNERDATA2_QRY, entity);
    }

    public void removePartnerData(PartnerData partnerData) {
        remove(partnerData);
    }

    static final String PARTNERDATA1_QRY = "from PartnerData partnerData " +
        "where partnerData.entity = ? and partnerData.alias = ? ";

    static final String PARTNERDATA2_QRY = "from PartnerData partnerData " +
        "where partnerData.entity = ? ";

    /*
     * Partner
     */

    public void persistPartner(Partner partner) {
        merge(partner);
    }

    public Partner findPartnerByNaturalId(PartnerData partnerData, byte sequence) {
        return (Partner) findUnique(PARTNER1_QRY, partnerData, sequence);
    }

    public List<Partner> findPartnerByEntity(Entity entity) {
        return (ArrayList<Partner>) find(PARTNER3_QRY, entity);
    }

    public List<Partner> findPartnerByEntity(Entity entity, int priority) {
        return (ArrayList<Partner>) find(PARTNER2_QRY, entity, priority);
    }

    public List<Partner> findPartnerByPartnerData(PartnerData partnerData) {
        return (ArrayList<Partner>) find(PARTNER4_QRY, partnerData);
    }

    public void removePartner(Partner partner) {
        remove(partner);
    }
    
    static final String PARTNER1_QRY = "from Partner partner " +
        "where partner.partnerData = ? and partner.sequence = ? ";

    static final String PARTNER2_QRY = "from Partner partner " +
        "where partner.partnerData.entity = ? and partner.partnerData.priority = ? ";

    static final String PARTNER3_QRY = "from Partner partner " +
        "where partner.partnerData.entity = ? ";

    static final String PARTNER4_QRY = "from Partner partner " +
        "where partner.partnerData = ? ";

    /*
     * Partner subclasses
     */

    public List<Customer> findCustomerByEntity(Entity entity) {
        return (ArrayList<Customer>) find(CUSTOMER1_QRY, entity);
    }

    public List<Customer> findCustomerByEntity(Entity entity, int priority) {
        return (ArrayList<Customer>) find(CUSTOMER2_QRY, entity, priority);
    }

    public List<Supplier> findSupplierByEntity(Entity entity) {
        return (ArrayList<Supplier>) find(SUPPLIER1_QRY, entity);
    }

    public List<Supplier> findSupplierByEntity(Entity entity, int priority) {
        return (ArrayList<Supplier>) find(SUPPLIER2_QRY, entity, priority);
    }
    
    public List<Division> findDivisionByEntity(Entity entity) {
        return (ArrayList<Division>) find(DIVISION1_QRY, entity);
    }

    public List<Division> findDivisionByEntity(Entity entity, int priority) {
        return (ArrayList<Division>) find(DIVISION2_QRY, entity, priority);
    }

	public List<Agent> findAgentByEntity(Entity entity) {
        return (ArrayList<Agent>) find(AGENT1_QRY, entity);
    }

    public List<Agent> findAgentByEntity(Entity entity, int priority) {
        return (ArrayList<Agent>) find(AGENT2_QRY, entity, priority);
    }

    public List<Bank> findBankByEntity(Entity entity) {
        return (ArrayList<Bank>) find(BANK1_QRY, entity);
    }

    public List<Bank> findBankByEntity(Entity entity, int priority) {
        return (ArrayList<Bank>) find(BANK2_QRY, entity, priority);
    }

    public List<Manufacturer> findManufacturerByEntity(Entity entity) {
        return (ArrayList<Manufacturer>) find(MANUFATURER1_QRY, entity);
    }

    public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority) {
        return (ArrayList<Manufacturer>) find(MANUFATURER2_QRY, entity, priority);
    }
    
    static String CUSTOMER1_QRY = "from Customer customer " +
        "where customer.partner.entity = ? ";

    static String CUSTOMER2_QRY = "from Customer customer " +
        "where customer.partner.entity = ? and customer.partner.priority = ? ";

    static String SUPPLIER1_QRY = "from Supplier supplier " +
        "where supplier.partner.entity = ? ";

    static String SUPPLIER2_QRY = "from Supplier supplier " +
        "where supplier.partner.entity = ?  and supplier.partner.priority = ? ";

    static String DIVISION1_QRY = "from Division division " +
        "where division.partner.entity = ? ";

    static String DIVISION2_QRY = "from Division division " +
        "where division.partner.entity = ?  and division.partner.priority = ? ";

    static String AGENT1_QRY = "from Agent agent " +
        "where agent.partner.entity = ? ";

    static String AGENT2_QRY = "from Agent agent " +
        "where agent.partner.entity = ?  and agent.partner.priority = ? ";

    static String BANK1_QRY = "from Bank bank " +
        "where bank.partner.entity = ? ";

    static String BANK2_QRY = "from Bank bank " +
        "where bank.partner.entity = ?  and bank.partner.priority = ? ";

    static String MANUFATURER1_QRY = "from Manufacturer manufaturer " +
        "where manufaturer.partner.entity = ? ";

    static String MANUFATURER2_QRY = "from Manufacturer manufaturer " +
        "where manufaturer.partner.entity = ?  and manufaturer.partner.priority = ? ";


    /*
     * Contact
     */

    public void persistContact(Contact contact) {
        merge(contact);
    }

    public List<Contact> findContactByPartnerData(PartnerData partnerData) {
        return (ArrayList<Contact>) find(CONTACT_QRY_BY_PARTNER, partnerData);
    }

    public Contact findContactByNaturalId(PartnerData partnerData, Identity identity) {
        return (Contact) findUnique(CONTACT_QRY, partnerData, identity);
    }

    public void removeContact(Contact contact) {
        remove(contact);
    }

    static final String CONTACT_QRY_BY_PARTNER = 
        "from Contact contact " +
        "where contact.partner.id = ?";
    
    static final String CONTACT_QRY = 
        "from Contact contact " +
        "where contact.partner.id = ?";

}
