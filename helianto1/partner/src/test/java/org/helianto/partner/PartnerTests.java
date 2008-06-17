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
        
        Partner partner = Partner.partnerFactory(partnerRegistry);
        
        assertSame(partnerRegistry, partner.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(partner));
        
    }
    
    /**
     * Test <code>Partner</code> equals() method.
     */
    public void testPartnerEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Partner partner = Partner.partnerFactory(partnerRegistry);
        Partner copy = (Partner) DomainTestSupport.minimalEqualsTest(partner);
        
        copy.setPartnerRegistry(null);
        assertFalse(partner.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        assertTrue(partner.equals(copy));
    }

}
    
    
