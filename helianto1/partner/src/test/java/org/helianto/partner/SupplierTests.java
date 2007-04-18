package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.Supplier;

/**
 * <code>Supplier</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SupplierTests extends TestCase {
    
    /**
     * Test <code>Supplier</code> static factory method.
     */
    public void testSupplierFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Supplier supplier = Supplier.supplierFactory(partnerRegistry, sequence);
        
        assertSame(partnerRegistry, supplier.getPartnerRegistry());
        assertEquals(sequence, supplier.getSequence());
        assertTrue(partnerRegistry.getPartners().contains(supplier));
        
    }
    
}
    
    
