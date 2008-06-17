package org.helianto.partner;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

/**
 * <code>Customer</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CustomerTests extends TestCase {
    
    /**
     * Test <code>Customer</code> static factory method.
     */
    public void testCustomerFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Customer customer = Customer.customerFactory(partnerRegistry);
        
        assertSame(partnerRegistry, customer.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(customer));
        
    }
    
    /**
     * Test <code>Customer</code> equals() method.
     */
    public void testCustomerEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Customer customer = Customer.customerFactory(partnerRegistry);
        Customer copy = (Customer) DomainTestSupport.minimalEqualsTest(customer);
        
        copy.setPartnerRegistry(null);
        assertFalse(customer.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(customer.equals(copy));
    }

}
    
    
