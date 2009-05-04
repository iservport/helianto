package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

/**
 * <code>Laboratory</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LaboratoryTests extends TestCase {
    
    /**
     * Test <code>Laboratory</code> static factory method.
     */
    public void testLaboratoryFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Laboratory laboratory = Laboratory.laboratoryFactory(partnerRegistry);
        
        assertSame(partnerRegistry, laboratory.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(laboratory));
        
    }
    
    /**
     * Test <code>Laboratory</code> equals() method.
     */
    public void testLaboratoryEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Laboratory laboratory = Laboratory.laboratoryFactory(partnerRegistry);
        Laboratory copy = (Laboratory) DomainTestSupport.minimalEqualsTest(laboratory);
        
        copy.setPartnerRegistry(null);
        assertFalse(laboratory.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(laboratory.equals(copy));
    }

}
    
    
