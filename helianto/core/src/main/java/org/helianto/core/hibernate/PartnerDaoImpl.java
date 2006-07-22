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
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.PartnerRole;
import org.helianto.core.Supplier;
import org.helianto.core.dao.PartnerDao;
import org.springframework.util.Assert;

/**
 * Default implementation for <code>PartnerDao</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerDaoImpl extends GenericDaoImpl implements PartnerDao {

    public void persistPartner(Partner partner) {
        Assert.notNull(partner);
        merge(partner);
    }

    public void persistPartnerRole(PartnerRole partnerRole) {
        Assert.notNull(partnerRole);
        merge(partnerRole);
    }

    public void persistContact(Contact contact) {
        Assert.notNull(contact);
        merge(contact);
    }

    public Partner findPartnerByEntityAndAlias(Entity entity, String alias) {
        return (Partner) findUnique(PARTNER1_QRY, entity, alias);
    }

    public List<Partner> findPartnerByEntity(Entity entity) {
        return (ArrayList<Partner>) find(PARTNER2_QRY, entity);
    }

    public List<PartnerRole> findPartnerRoleByEntity(Entity entity) {
        return (ArrayList<PartnerRole>) find(PARTNERROLE1_QRY, entity);
    }

    public List<PartnerRole> findPartnerRoleByPartner(Partner partner) {
        return (ArrayList<PartnerRole>) find(PARTNERROLE2_QRY, partner);
    }

    public List<Customer> findCustomerByEntity(Entity entity) {
        return (ArrayList<Customer>) find(CUSTOMER_QRY, entity);
    }

    public List<Supplier> findSupplierByEntity(Entity entity) {
        return (ArrayList<Supplier>) find(SUPPLIER_QRY, entity);
    }

    public List<Division> findDivisionByEntity(Entity entity) {
        return (ArrayList<Division>) find(DIVISION_QRY, entity);
    }

	public Division findCurrentDivision(Entity entity) {
		// TODO TO BE REMOVED
		return null;
	}
    
	public List<Agent> findAgentByEntity(Entity entity) {
        return (ArrayList<Agent>) find(AGENT_QRY, entity);
    }

    public List<Bank> findBankByEntity(Entity entity) {
        return (ArrayList<Bank>) find(BANK_QRY, entity);
    }

    // TODO add priority to partner
    public List<Manufacturer> findManufacturerByEntity(Entity entity) {
        return findManufacturerByEntity(entity, -1);
    }

    public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority) {
        if (priority<0) {
            return (ArrayList<Manufacturer>) find(MANUFATURER_QRY, entity);
        }
//        return (ArrayList<Manufacturer>) partnerDao.find(MANUFACTURER_QRY_BY_ENTITY+
//                MANUFACTURER_FILTER_BY_PRIORITY, entity, priority);
        return null;
    }
    
    public List<Contact> findContactByPartner(Partner partner) {
        return (ArrayList<Contact>) find(CONTACT_QRY_BY_PARTNER, partner.getId());
    }

    public void removePartner(Partner partner) {
        remove(partner);
    }

    public void removePartnerRole(PartnerRole partnerRole) {
        remove(partnerRole);
    }

    public void removeContact(Contact contact) {
        remove(contact);
    }

    static final String PARTNER1_QRY = "from Partner partner " +
        "where partner.entity = ? and partner.alias = ? ";

    static final String PARTNER2_QRY = "from Partner partner " +
        "where partner.entity = ? ";

    static final String PARTNERROLE1_QRY = "from PartnerRole partnerRole " +
        "where partnerRole.partner.entity = ? ";

    static final String PARTNERROLE2_QRY = "from PartnerRole partnerRole " +
        "where partnerRole.partner = ? ";

    static String CUSTOMER_QRY = "from Customer customer " +
    	"where customer.partner.entity = ? ";

    static String SUPPLIER_QRY = "from Supplier supplier " +
        "where supplier.partner.entity = ?";

    static String DIVISION_QRY = "from Division division " +
        "where division.partner.entity = ?";

    static String CURRENTDIVISION_QRY = "from Division division " +
    	"where division.partner.entity = ? and division.related = division.entity";

    static String AGENT_QRY = "from Agent agent " +
        "where agent.partner.entity = ?";

    static String BANK_QRY = "from Bank bank " +
        "where bank.partner.entity = ?";

    static String MANUFATURER_QRY = "from Manufacturer manufaturer " +
        "where manufaturer.partner.entity = ?";

    static final String CONTACT_QRY_BY_PARTNER = 
        "from Contact contact " +
        "where contact.partner.id = ?";

}
