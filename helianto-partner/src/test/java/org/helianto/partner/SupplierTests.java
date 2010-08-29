package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Supplier</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class SupplierTests {
    
    /**
     * Test <code>Supplier</code> equals() method.
     */
	@Test
    public void testSupplierEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Supplier supplier = new Supplier(partnerRegistry);
        Supplier copy = (Supplier) DomainTestSupport.minimalEqualsTest(supplier);
        
        copy.setPartnerRegistry(null);
        assertFalse(supplier.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(supplier.equals(copy));
    }

}
    
    
