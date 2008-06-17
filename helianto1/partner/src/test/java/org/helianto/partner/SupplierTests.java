package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

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
        
        Supplier supplier = Supplier.supplierFactory(partnerRegistry);
        
        assertSame(partnerRegistry, supplier.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(supplier));
        
    }
    
    /**
     * Test <code>Supplier</code> equals() method.
     */
    public void testSupplierEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Supplier supplier = Supplier.supplierFactory(partnerRegistry);
        Supplier copy = (Supplier) DomainTestSupport.minimalEqualsTest(supplier);
        
        copy.setPartnerRegistry(null);
        assertFalse(supplier.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(supplier.equals(copy));
    }

}
    
    
