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
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        KeyType keyType = new KeyType();
        
        PartnerKey partnerKey = PartnerKey.partnerKeyFactory(partnerRegistry, keyType);
        
        assertSame(partnerRegistry, partnerKey.getPartnerRegistry());
        assertSame(keyType, partnerKey.getKeyType());
        
    }
    
    /**
     * Test <code>PartnerKey</code> equals() method.
     */
	@Test
    public void testPartnerKeyEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        KeyType keyType = new KeyType();
        
        PartnerKey partnerKey = PartnerKey.partnerKeyFactory(partnerRegistry, keyType);
        PartnerKey copy = (PartnerKey) DomainTestSupport.minimalEqualsTest(partnerKey);
        
        copy.setPartnerRegistry(null);
        copy.setKeyType(keyType);
        assertFalse(partnerKey.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setKeyType(null);
        assertFalse(partnerKey.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setKeyType(keyType);

        assertTrue(partnerKey.equals(copy));
    }

}
    
    
