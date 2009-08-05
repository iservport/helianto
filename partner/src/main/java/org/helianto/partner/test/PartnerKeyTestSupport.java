package org.helianto.partner.test;

import org.helianto.core.KeyType;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerKey;

/**
 * Class to support <code>PartnerKey</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerKeyTestSupport {

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * 
     */
    public static PartnerKey createPartnerKey() {
        return createPartnerKey(PartnerTestSupport.createPartner());
    }

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * @param partnerAssociation  partner 
     * 
     */
    public static PartnerKey createPartnerKey(Partner partner) {
        return createPartnerKey(partner, KeyTypeTestSupport.createKeyType());
    }

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * @param partner  partner 
     * @param keyType  keyType 
     */
    public static PartnerKey createPartnerKey(Partner partner, KeyType keyType) {
        PartnerKey partnerKey = PartnerKey.partnerKeyFactory(partner, keyType);
        return partnerKey;
    }

}
