package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.partner.PartnerAssociation;
import org.helianto.partner.PartnerKey;

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
        PartnerAssociation partnerAssociation;
        try {
            partnerAssociation = (PartnerAssociation) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
        }
        KeyType keyType;
        try {
            keyType = (KeyType) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            keyType = OperatorTestSupport.createKeyType();
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
        List<PartnerAssociation> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize);
        List<KeyType> keyTypeList = OperatorTestSupport.createKeyTypeList(keyTypeListSize, 1);
        return createPartnerKeyList(partnerAssociationList, keyTypeList);
    }

    /**
     * Test support method to create a <code>PartnerKey</code> list.
     *
     * @param partnerAssociationList
     * @param keyTypeList
     */
    public static List<PartnerKey> createPartnerKeyList(List<PartnerAssociation> partnerAssociationList, List<KeyType> keyTypeList) {
        List<PartnerKey> partnerKeyList = new ArrayList<PartnerKey>();
        for (PartnerAssociation partnerAssociation: partnerAssociationList) {
        	for (KeyType keyType: keyTypeList) {
    	        partnerKeyList.add(createPartnerKey(partnerAssociation, keyType));
        	}
        }
        return partnerKeyList;
    }

}
