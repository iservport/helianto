package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Customer</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CustomerTests {
    
    /**
     * Test <code>Customer</code> static factory method.
     */
	@Test
    public void customerFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Customer customer = Customer.customerFactory(partnerRegistry);
        
        assertSame(partnerRegistry, customer.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(customer));
        
    }
    
    /**
     * Test <code>Customer</code> equals() method.
     */
	@Test
    public void customerEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Customer customer = Customer.customerFactory(partnerRegistry);
        Customer copy = (Customer) DomainTestSupport.minimalEqualsTest(customer);
        
        copy.setPartnerRegistry(null);
        assertFalse(customer.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(customer.equals(copy));
    }

}
    
    
