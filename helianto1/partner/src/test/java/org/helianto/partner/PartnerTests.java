package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;

/**
 * <code>Partner</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerTests extends TestCase {
    
    /**
     * Test <code>Partner</code> static factory method.
     */
    public void testPartnerFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Partner partner = Partner.partnerFactory(partnerRegistry, sequence);
        
        assertSame(partnerRegistry, partner.getPartnerRegistry());
        assertEquals(sequence, partner.getSequence());
        assertTrue(partnerRegistry.getPartners().contains(partner));
        
    }
    
    /**
     * Test <code>Partner</code> equals() method.
     */
    public void testPartnerEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Partner partner = Partner.partnerFactory(partnerRegistry, sequence);
        Partner copy = (Partner) DomainTestSupport.minimalEqualsTest(partner);
        
        copy.setPartnerRegistry(null);
        copy.setSequence(sequence);
        assertFalse(partner.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setSequence(0);
        assertFalse(partner.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setSequence(sequence);

        assertTrue(partner.equals(copy));
    }

}
    
    
