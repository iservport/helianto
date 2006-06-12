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

import org.helianto.core.junit.AbstractIntegrationTest;

public class PartnerMgrImplTests extends AbstractIntegrationTest {
    
    // class under test
	private PartnerMgr partnerMgr;

    public void test() {
//        Entity entity = getTestEntity();
//        partnerMgr.persistEntity(entity);
//        assertNotNull("0", entity.getId());
//        
//        //Customer
//        String customerAlias = generateKey(20); 
//        Customer customer = partnerMgr.customerFactory(entity, customerAlias);
//        assertNull("1.1", customer.getId());
//        assertSame("1.2", entity, customer.getEntity());
//        assertEquals("1.3", customerAlias, customer.getAlias());
//        assertTrue("1.4", (new Date()).getTime()-customer.getRelatedSince().getTime()<1000);
//        assertEquals("1.5", PartnerState.ACTIVE.getValue(), customer.getState());
//        assertFalse("1.5", customer.isStrong());
//        
//        partnerMgr.persistCustomer(customer);
//        assertNotNull("2.1", customer.getId());
//        
//        customerAlias = generateKey(20); 
//        customer = partnerMgr.customerFactory(entity, customerAlias);
//        assertNull("3.1", customer.getId());
//
//        partnerMgr.persistCustomer(customer);
//        assertNotNull("4.1", customer.getId());
//        
//        hibernateTemplate.clear();
//        
//        Customer loadedCustomer = partnerMgr.loadCustomer(customer.getId());
//        assertEquals("5.1", loadedCustomer.getId(), customer.getId());
//        
//        List list = partnerMgr.findCustomerByEntity(entity);
//        assertEquals("6.1", 2, list.size());
    	
    	
//        
//        
    }
    
//  public void persistCustomer(Customer customer);
    public void testPersistCustomer() {
    	
    }

//	public void persistSupplier(Supplier supplier);
    public void testPersistSupplier() {
    	
    }

//	public void persistContact(Contact contact);
    public void testPersistContact() {
    	
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
    	
    }

//	public List<Manufacturer> findManufacturerByEntity(Entity entity);
//	public List<Manufacturer> findManufacturerByEntity(Entity entity, int priority);
    public void testFindManufacturerByEntity() {
    	
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
    
    public void setPartnerMgr(PartnerMgr partnerMgr) {
        this.partnerMgr = partnerMgr;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/dataSource.xml", 
                "deploy/sessionFactory.xml",
                "deploy/support.xml",
                "deploy/transaction.xml",
                "deploy/core.xml"};
    }
    
}
