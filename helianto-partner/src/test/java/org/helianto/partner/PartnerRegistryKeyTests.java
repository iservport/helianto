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
public class PartnerRegistryKeyTests {
    
    /**
     * Test <code>PartnerKey</code> static factory method.
     */
	@Test
    public void testPartnerRegistryKeyFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        KeyType keyType = new KeyType();
        
        PartnerRegistryKey partnerRegistryKey = PartnerRegistryKey.partnerRegistryKeyFactory(partnerRegistry, keyType);
        
        assertSame(partnerRegistry, partnerRegistryKey.getPartnerRegistry());
        assertSame(keyType, partnerRegistryKey.getKeyType());
        
    }
    
    /**
     * Test <code>PartnerRegistryKey</code> equals() method.
     */
	@Test
    public void testPartnerKeyEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        KeyType keyType = new KeyType();
        
        PartnerRegistryKey partnerRegistryKey = PartnerRegistryKey.partnerRegistryKeyFactory(partnerRegistry, keyType);
        PartnerRegistryKey copy = (PartnerRegistryKey) DomainTestSupport.minimalEqualsTest(partnerRegistryKey);
        
        copy.setPartnerRegistry(null);
        copy.setKeyType(keyType);
        assertFalse(partnerRegistryKey.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setKeyType(null);
        assertFalse(partnerRegistryKey.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setKeyType(keyType);

        assertTrue(partnerRegistryKey.equals(copy));
    }

}
    
    
