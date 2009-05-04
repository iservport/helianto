package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

/**
 * <code>Manufacturer</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ManufacturerTests extends TestCase {
    
    /**
     * Test <code>Manufacturer</code> static factory method.
     */
    public void testManufacturerFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Manufacturer manufacturer = Manufacturer.manufacturerFactory(partnerRegistry);
        
        assertSame(partnerRegistry, manufacturer.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(manufacturer));
        
    }
    
    /**
     * Test <code>Manufacturer</code> equals() method.
     */
    public void testManufacturerEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Manufacturer manufacturer = Manufacturer.manufacturerFactory(partnerRegistry);
        Manufacturer copy = (Manufacturer) DomainTestSupport.minimalEqualsTest(manufacturer);
        
        copy.setPartnerRegistry(null);
        assertFalse(manufacturer.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(manufacturer.equals(copy));
    }

}
    
    
