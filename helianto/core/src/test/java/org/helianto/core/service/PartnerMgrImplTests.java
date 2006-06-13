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

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Contact;
import org.helianto.core.Customer;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Supplier;
import org.helianto.core.dao.PartnerDao;

public class PartnerMgrImplTests extends TestCase {
    
    // class under test
	private PartnerMgrImpl partnerMgr;

//  public void persistCustomer(Customer customer);
    public void testPersistCustomer() {
        Customer customer = new Customer();
        partnerDao.persistCustomer(customer);
        replay(partnerDao);
        partnerMgr.persistCustomer(customer);
        verify(partnerDao);
    }

//	public void persistSupplier(Supplier supplier);
    public void testPersistSupplier() {
    	Supplier supplier = new Supplier();
        partnerDao.persistSupplier(supplier);
        replay(partnerDao);
        partnerMgr.persistSupplier(supplier);
        verify(partnerDao);
    }

//	public void persistContact(Contact contact);
    public void testPersistContact() {
    	Contact contact = new Contact();
        partnerDao.persistContact(contact);
        replay(partnerDao);
        partnerMgr.persistContact(contact);
        verify(partnerDao);
    }

//	public Customer loadCustomer(Long key);
    public void testLoadCustomer() {
    	
    }

//	public Supplier loadSupplier(Long key);
    public void testLoadSupplier() {
    	
    }

//	public Contact loadContact(String key);
    public void testLoadContact() {
    	
    }

//	public List<Customer> findCustomerByEntity(Entity entity);
    public void testFindCustomerByEntity() {
        Entity entity = new Entity();
        List<Customer> customerList = new ArrayList<Customer>();
    	expect(partnerDao.findCustomerByEntity(entity)).andReturn(customerList);
        replay(partnerDao);
        assertSame(customerList, partnerMgr.findCustomerByEntity(entity));
        verify(partnerDao);
    }

//  public List<Manufacturer> findManufacturerByEntity(Entity entity);
    public void testFindManufacturerByEntity() {
        Entity entity = new Entity();
        List<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
        expect(partnerDao.findManufacturerByEntity(entity, -1)).andReturn(manufacturerList);
        replay(partnerDao);
        assertSame(manufacturerList, partnerMgr.findManufacturerByEntity(entity));
        verify(partnerDao);
    }

//  public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority);
    public void testFindManufacturerByEntityAndPriority() {
        Entity entity = new Entity();
        List<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
        expect(partnerDao.findManufacturerByEntity(entity, 0)).andReturn(manufacturerList);
        replay(partnerDao);
        assertSame(manufacturerList, partnerMgr.findManufacturerByEntity(entity, 0));
        verify(partnerDao);
    }

//	public List findContactByPartner(Partner partner);
    public void testFindContactByPartner() {
    	
    }

//	public void bindCustomerToEntity(Customer customer, Entity entity);
    public void testBindCustomerToEntity() {
    	
    }

//	public void bindSupplierToEntity(Supplier supplier, Entity entity);
    public void testBindSupplierToEntity() {
    	
    }

    // collabs
    
    private PartnerDao partnerDao;
    
    @Override
    public void setUp() {
        partnerDao = createMock(PartnerDao.class);
        partnerMgr = new PartnerMgrImpl();
        partnerMgr.setPartnerDao(partnerDao);
    }
    
}
