package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Customer;
import org.helianto.partner.PartnerAssociation;

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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Customer customer = Customer.customerFactory(partnerAssociation, sequence);
        
        assertSame(partnerAssociation, customer.getPartnerAssociation());
        assertEquals(sequence, customer.getSequence());
        assertTrue(partnerAssociation.getPartners().contains(customer));
        
    }
    
}
    
    
