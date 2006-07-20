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

import org.helianto.core.Contact;
import org.helianto.core.Customer;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.Supplier;
import org.helianto.core.dao.PartnerDao;

/**
 * Default implementation of the 
 * <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class PartnerMgrImpl implements PartnerMgr {

    public void persistCustomer(Customer customer) {
        partnerDao.persistPartnerRole(customer);
    }
    
    public void persistSupplier(Supplier supplier) {
        partnerDao.persistPartnerRole(supplier);
    }
    
    public void persistContact(Contact contact) {
        partnerDao.persistContact(contact);
    }
    
    public List<Customer> findCustomerByEntity(Entity entity) {
        return partnerDao.findCustomerByEntity(entity);
    }

    public List<Manufacturer> findManufacturerByEntity(Entity entity) {
        return partnerDao.findManufacturerByEntity(entity, -1);
    }

    public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority) {
        return partnerDao.findManufacturerByEntity(entity, priority);
    }
    
    public List<Contact> findContactByPartner(Partner partner) {
        return partnerDao.findContactByPartner(partner);
    }
    
    private PartnerDao partnerDao;

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

}
