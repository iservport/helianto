package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.partner.PartnerAssociation;
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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Supplier supplier = Supplier.supplierFactory(partnerAssociation, sequence);
        
        assertSame(partnerAssociation, supplier.getPartnerAssociation());
        assertEquals(sequence, supplier.getSequence());
        assertTrue(partnerAssociation.getPartners().contains(supplier));
        
    }
    
}
    
    
