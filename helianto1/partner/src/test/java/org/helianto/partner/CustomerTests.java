package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Customer;
import org.helianto.partner.PartnerRegistry;

import junit.framework.TestCase;

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
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Customer customer = Customer.customerFactory(partnerRegistry, sequence);
        
        assertSame(partnerRegistry, customer.getPartnerRegistry());
        assertEquals(sequence, customer.getSequence());
        assertTrue(partnerRegistry.getPartners().contains(customer));
        
    }
    
}
    
    
