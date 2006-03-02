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

import java.util.Date;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Supplier;

import org.helianto.core.Customer;

import org.helianto.core.Credential;
import org.helianto.core.Contact;
import org.helianto.core.Partner;
import org.helianto.core.PartnerState;

/**
 * Default implementation of the 
 * <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class PartnerMgrImpl extends CoreMgrImpl implements PartnerMgr {

    public Customer customerFactory(Entity entity, String alias) {
        Customer customer = new Customer();
        customer.setEntity(entity);
        customer.setAlias(alias);
        customer.setRelatedSince(new Date());
        customer.setState(PartnerState.ACTIVE.getValue());
        customer.setStrong(false);
        return customer;
    }
    
    public Supplier supplierFactory(Entity entity, String alias) {
        Supplier supplier = new Supplier();
        supplier.setEntity(entity);
        supplier.setAlias(alias);
        supplier.setRelatedSince(new Date());
        supplier.setState(PartnerState.ACTIVE.getValue());
        supplier.setStrong(false);
        return supplier;
    }
    
    public Contact contactFactory(Partner partner, Credential cred) {
        Contact cntct = new Contact();
        cntct.setPartner(partner);
        cntct.setCredential(cred);
        return cntct;
    }

    public void persistCustomer(Customer customer) {
        this.getGenericDao().merge(customer);
    }
    
    public void persistSupplier(Supplier supplier) {
        this.getGenericDao().merge(supplier);
    }
    
    public void persistContact(Contact contact) {
        this.getGenericDao().merge(contact);
    }
    
    public Customer loadCustomer(Long key) {
        try {
            return (Customer) getGenericDao().load(Customer.class, key);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unable to load customer with id "+key+", raised the exception:"+e.toString());
            }
        }
        return null;
    }
    
    public Supplier loadSupplier(Long key) {
        try {
            return (Supplier) getGenericDao().load(Supplier.class, key);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unable to load supplier with id "+key+", raised the exception:"+e.toString());
            }
        }
        return null;
    }
    
    public Contact loadContact(String key) {
        try {
            return (Contact) getGenericDao().load(Contact.class, key);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unable to load contact with id "+key+", raised the exception:"+e.toString());
            }
        }
        return null;
    }
    
    public List<Customer> findCustomerByEntity(Entity entity) {
        return (List<Customer>) getGenericDao().find(CUSTOMER_QRY_BY_ENTITY, entity.getId());
    }

    public List findContactByPartner(Partner partner) {
        return (List) getGenericDao().find(CONTACT_QRY_BY_PARTNER, partner.getId());
    }
    public void bindCustomerToEntity(Customer customer, Entity entity) {
        customer.setRelated(entity);
        customer.setStrong(true);
    }

    public void bindSupplierToEntity(Supplier supplier, Entity entity) {
        supplier.setRelated(entity);
        supplier.setStrong(true);
    }

    static final String CUSTOMER_QRY_BY_ENTITY = 
        "from Customer cust where cust.entity.id = ?";
    
    static final String CONTACT_QRY_BY_PARTNER = 
        "from Contact contact " +
        "where contact.partner.id = ?";
    
}
