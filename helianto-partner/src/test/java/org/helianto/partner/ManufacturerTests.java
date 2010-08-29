package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
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
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Manufacturer manufacturer = new Manufacturer(partnerRegistry);
        Manufacturer copy = (Manufacturer) DomainTestSupport.minimalEqualsTest(manufacturer);
        
        copy.setPartnerRegistry(null);
        assertFalse(manufacturer.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(manufacturer.equals(copy));
    }

}
    
    
