package org.helianto.partner.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.partner.domain.nature.Manufacturer;
import org.junit.Test;

/**
 * <code>Manufacturer</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ManufacturerTests  {
    
    /**
     * Test <code>Manufacturer</code> equals() method.
     */
	@Test
    public void testManufacturerEquals() {
		Entity entity = new Entity(new Operator("DEFAULT"));
        PrivateEntity partnerRegistry = new PrivateEntity(entity, "TEST");
        
        Manufacturer manufacturer = new Manufacturer();
        Manufacturer other = new Manufacturer();
        
        assertTrue(manufacturer.equals(other));
        
        manufacturer.setPrivateEntity(partnerRegistry);
        assertFalse(manufacturer.equals(other));
        other.setPrivateEntity(partnerRegistry);
        assertTrue(manufacturer.equals(other));
        assertEquals(manufacturer.hashCode(), other.hashCode());
        assertFalse(manufacturer.equals(new Partner(partnerRegistry)));
    }

}
    
    
