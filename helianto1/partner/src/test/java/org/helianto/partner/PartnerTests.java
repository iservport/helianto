package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.Partner;
import org.helianto.partner.PartnerAssociation;

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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Partner partner = Partner.partnerFactory(partnerAssociation, sequence);
        
        assertSame(partnerAssociation, partner.getPartnerAssociation());
        assertEquals(sequence, partner.getSequence());
        assertTrue(partnerAssociation.getPartners().contains(partner));
        
    }
    
    /**
     * Test <code>Partner</code> equals() method.
     */
    public void testPartnerEquals() {
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Partner partner = Partner.partnerFactory(partnerAssociation, sequence);
        Partner copy = (Partner) DomainTestSupport.minimalEqualsTest(partner);
        
        copy.setPartnerAssociation(null);
        copy.setSequence(sequence);
        assertFalse(partner.equals(copy));

        copy.setPartnerAssociation(partnerAssociation);
        copy.setSequence(0);
        assertFalse(partner.equals(copy));

        copy.setPartnerAssociation(partnerAssociation);
        copy.setSequence(sequence);

        assertTrue(partner.equals(copy));
    }

}
    
    
