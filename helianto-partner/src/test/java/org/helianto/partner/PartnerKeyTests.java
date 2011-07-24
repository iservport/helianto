package org.helianto.partner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.KeyType;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>PartnerKey</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerKeyTests {
    
    /**
     * Test <code>PartnerKey</code> static factory method.
     */
	@Test
    public void testPartnerKeyFactory() {
        Partner partner = new Partner();
        KeyType keyType = new KeyType();
        
        PartnerKey partnerKey = new PartnerKey(partner, keyType);
        
        assertSame(partner, partnerKey.getPartner());
        assertSame(keyType, partnerKey.getKeyType());
        
    }
    
    /**
     * Test <code>PartnerKey</code> equals() method.
     */
	@Test
    public void testPartnerKeyEquals() {
        Partner partner = new Partner();
        KeyType keyType = new KeyType();
        
        PartnerKey partnerKey = new PartnerKey(partner, keyType);
        PartnerKey copy = (PartnerKey) DomainTestSupport.minimalEqualsTest(partnerKey);
        
        copy.setPartner(null);
        copy.setKeyType(keyType);
        assertFalse(partnerKey.equals(copy));

        copy.setPartner(partner);
        copy.setKeyType(null);
        assertFalse(partnerKey.equals(copy));

        copy.setPartner(partner);
        copy.setKeyType(keyType);

        assertTrue(partnerKey.equals(copy));
    }

}
    
    
