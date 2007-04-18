package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.partner.PartnerKey;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>PartnerKeyDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerKeyTestSupport {

    /**
     * Test support method to create a <code>PartnerKey</code>.
     * @param partnerAssociation optional PartnerAssociation 
     * @param keyType optional KeyType 
     */
    public static PartnerKey createPartnerKey(Object... args) {
        PartnerRegistry partnerAssociation;
        try {
            partnerAssociation = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
        }
        KeyType keyType;
        try {
            keyType = (KeyType) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            keyType = KeyTypeTestSupport.createKeyType();
        }
        PartnerKey partnerKey = PartnerKey.partnerKeyFactory(partnerAssociation, keyType);
        return partnerKey;
    }

    /**
     * Test support method to create a <code>PartnerKey</code> list.
     *
     * @param partnerKeyListSize
     */
    public static List<PartnerKey> createPartnerKeyList(int partnerKeyListSize) {
        return createPartnerKeyList(partnerKeyListSize, 1);
    }

    /**
     * Test support method to create a <code>PartnerKey</code> list.
     *
     * @param partnerAssociationListSize
     * @param keyTypeListSize
     */
    public static List<PartnerKey> createPartnerKeyList(int partnerAssociationListSize, int keyTypeListSize) {
        List<PartnerRegistry> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize);
        List<KeyType> keyTypeList = KeyTypeTestSupport.createKeyTypeList(keyTypeListSize, 1);
        return createPartnerKeyList(partnerAssociationList, keyTypeList);
    }

    /**
     * Test support method to create a <code>PartnerKey</code> list.
     *
     * @param partnerAssociationList
     * @param keyTypeList
     */
    public static List<PartnerKey> createPartnerKeyList(List<PartnerRegistry> partnerAssociationList, List<KeyType> keyTypeList) {
        List<PartnerKey> partnerKeyList = new ArrayList<PartnerKey>();
        for (PartnerRegistry partnerRegistry: partnerAssociationList) {
        	for (KeyType keyType: keyTypeList) {
    	        partnerKeyList.add(createPartnerKey(partnerRegistry, keyType));
        	}
        }
        return partnerKeyList;
    }

}
