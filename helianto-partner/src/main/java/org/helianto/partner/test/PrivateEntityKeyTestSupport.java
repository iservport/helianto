package org.helianto.partner.test;

import org.helianto.core.KeyType;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.partner.PrivateEntity;
import org.helianto.partner.PrivateEntityKey;

/**
 * Class to support <code>PartnerKeyDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PrivateEntityKeyTestSupport {

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * 
     */
    public static PrivateEntityKey createPartnerRegistryKey() {
        return createPartnerRegistryKey(PrivateEntityTestSupport.createPartnerRegistry());
    }

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * 
     * @param privateEntity
     */
    public static PrivateEntityKey createPartnerRegistryKey(PrivateEntity privateEntity) {
        return createPartnerRegistryKey(privateEntity, KeyTypeTestSupport.createKeyType());
    }

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * 
     * @param privateEntity
     * @param keyType
     */
    public static PrivateEntityKey createPartnerRegistryKey(PrivateEntity privateEntity, KeyType keyType) {
        PrivateEntityKey privateEntityKey = new PrivateEntityKey(privateEntity, keyType);
        return privateEntityKey;
    }

}
