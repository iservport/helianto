package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Partner</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerTests {
    
    /**
     * Test <code>Partner</code> static factory method.
     */
	@Test
    public void testPartnerFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        
        Partner partner = Partner.partnerFactory(partnerRegistry);
        
        assertSame(partnerRegistry, partner.getPartnerRegistry());
        assertTrue(partnerRegistry.getPartners().contains(partner));
        
    }
    
    /**
     * Test <code>Partner</code> equals() method.
     */
	@Test
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
    
    