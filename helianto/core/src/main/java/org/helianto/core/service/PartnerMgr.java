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

/**
 * The partner service layer facade interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public interface PartnerMgr extends CoreMgr {
    
    /**
     * Persist a <code>Customer</code>
     */
    public void persistCustomer(Customer customer);
    
    /**
     * Persist a <code>Supplier</code>
     */
    public void persistSupplier(Supplier supplier);
    
    /**
     * Persist a <code>Contact</code>
     */
    public void persistContact(Contact contact);
    
    /**
     * Load a <code>Customer</code>
     */
    public Customer loadCustomer(Long key);
    
    /**
     * Load a <code>Supplier</code>
     */
    public Supplier loadSupplier(Long key);
    
    /**
     * Load a <code>Contact</code>
     */
    public Contact loadContact(String key);
    
    /**
     * Find a <code>Customer</code> list by <code>Entity</code>.
     */
    public List<Customer> findCustomerByEntity(Entity entity);
    
    /**
     * Find a <code>Manufacturer</code> list by <code>Entity</code>.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity);
    
    /**
     * Find a <code>Manufacturer</code> list by <code>Entity</code>
     * and priority.
     */
    public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority);
    
    /**
     * Find a <code>Contact</code> list by <code>Partner</code>.
     */
    public List findContactByPartner(Partner partner);
    
    /**
     * Bind a <code>Customer</code> to an <code>Entity</code>
     * and assure the relationship is assigned as strong.
     */
    public void bindCustomerToEntity(Customer customer, Entity entity);

    /**
     * Bind a <code>Supplier</code> to an <code>Entity</code>
     * and assure the relationship is assigned as strong.
     */
    public void bindSupplierToEntity(Supplier supplier, Entity entity);

}
