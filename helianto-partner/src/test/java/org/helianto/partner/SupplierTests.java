package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
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
		Entity entity = new Entity(new Operator("DEFAULT"));
        PrivateEntity partnerRegistry = new PrivateEntity(entity, "TEST");
        
        Supplier supplier = new Supplier();
        Supplier other = new Supplier();
        
        assertTrue(supplier.equals(other));
        
        supplier.setPrivateEntity(partnerRegistry);
        assertFalse(supplier.equals(other));
        other.setPrivateEntity(partnerRegistry);
        assertTrue(supplier.equals(other));
        assertEquals(supplier.hashCode(), other.hashCode());
        assertFalse(supplier.equals(new Partner(partnerRegistry)));
    }

}
    
    
