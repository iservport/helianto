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
        PrivateEntity partnerRegistry = new PrivateEntity();
        
        Supplier supplier = new Supplier(partnerRegistry);
        Supplier copy = (Supplier) DomainTestSupport.minimalEqualsTest(supplier);
        
        copy.setPrivateEntity(null);
        assertFalse(supplier.equals(copy));

        copy.setPrivateEntity(partnerRegistry);
        assertTrue(supplier.equals(copy));
    }

}
    
    
