package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.KeyType;

import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerKey;

/**
 * <code>PartnerKey</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerKeyTests extends TestCase {
    
    /**
     * Test <code>PartnerKey</code> static factory method.
     */
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
    
    
