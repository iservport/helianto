package org.helianto.partner.test;

import org.helianto.core.KeyType;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerRegistryKey;

/**
 * Class to support <code>PartnerKeyDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerRegistryKeyTestSupport {

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * 
     */
    public static PartnerRegistryKey createPartnerRegistryKey() {
        return createPartnerRegistryKey(PartnerRegistryTestSupport.createPartnerRegistry());
    }

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * @param partnerAssociation  PartnerAssociation 
     * 
     */
    public static PartnerRegistryKey createPartnerRegistryKey(PartnerRegistry partnerAssociation) {
        return createPartnerRegistryKey(partnerAssociation, KeyTypeTestSupport.createKeyType());
    }

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * @param partnerAssociation  PartnerAssociation 
     * @param keyType  KeyType 
     */
    public static PartnerRegistryKey createPartnerRegistryKey(PartnerRegistry partnerAssociation, KeyType keyType) {
        PartnerRegistryKey partnerRegistryKey = PartnerRegistryKey.partnerRegistryKeyFactory(partnerAssociation, keyType);
        return partnerRegistryKey;
    }

}
