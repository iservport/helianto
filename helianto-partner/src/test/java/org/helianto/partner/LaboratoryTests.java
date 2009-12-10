package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Laboratory</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LaboratoryTests{
    
    /**
     * Test <code>Laboratory</code> static factory method.
     */
	@Test
    public void laboratoryFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Laboratory laboratory = Laboratory.laboratoryFactory(partnerRegistry);
        
        assertSame(partnerRegistry, laboratory.getPartnerRegistry());
        
    }
    
    /**
     * Test <code>Laboratory</code> equals() method.
     */
	@Test
    public void laboratoryEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Laboratory laboratory = Laboratory.laboratoryFactory(partnerRegistry);
        Laboratory copy = (Laboratory) DomainTestSupport.minimalEqualsTest(laboratory);
        
        copy.setPartnerRegistry(null);
        assertFalse(laboratory.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(laboratory.equals(copy));
    }

}
    
    
