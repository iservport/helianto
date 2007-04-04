package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.KeyType;

import org.helianto.partner.PartnerAssociation;
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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        KeyType keyType = new KeyType();
        
        PartnerKey partnerKey = PartnerKey.partnerKeyFactory(partnerAssociation, keyType);
        
        assertSame(partnerAssociation, partnerKey.getPartnerAssociation());
        assertSame(keyType, partnerKey.getKeyType());
        
    }
    
    /**
     * Test <code>PartnerKey</code> equals() method.
     */
    public void testPartnerKeyEquals() {
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        KeyType keyType = new KeyType();
        
        PartnerKey partnerKey = PartnerKey.partnerKeyFactory(partnerAssociation, keyType);
        PartnerKey copy = (PartnerKey) DomainTestSupport.minimalEqualsTest(partnerKey);
        
        copy.setPartnerAssociation(null);
        copy.setKeyType(keyType);
        assertFalse(partnerKey.equals(copy));

        copy.setPartnerAssociation(partnerAssociation);
        copy.setKeyType(null);
        assertFalse(partnerKey.equals(copy));

        copy.setPartnerAssociation(partnerAssociation);
        copy.setKeyType(keyType);

        assertTrue(partnerKey.equals(copy));
    }

}
    
    
