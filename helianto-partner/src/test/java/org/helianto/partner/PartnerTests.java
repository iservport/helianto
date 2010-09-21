package org.helianto.partner;

import static org.junit.Assert.assertFalse;
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
     * Test <code>Partner</code> equals() method.
     */
	@Test
    public void testPartnerEquals() {
        PrivateEntity partnerRegistry = new PrivateEntity();
        
        Partner partner = new Partner(partnerRegistry);
        Partner copy = (Partner) DomainTestSupport.minimalEqualsTest(partner);
        
        copy.setPrivateEntity(null);
        assertFalse(partner.equals(copy));

        copy.setPrivateEntity(partnerRegistry);
        assertTrue(partner.equals(copy));
    }

}
    
    
