package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerAssociation;

/**
 * Class to support <code>PartnerDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Partner</code>.
     * @param partnerAssociation optional PartnerAssociation 
     * @param sequence optional int 
     */
    public static Partner createPartner(Object... args) {
        PartnerAssociation partnerAssociation;
        try {
            partnerAssociation = (PartnerAssociation) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociation = PartnerAssociationTestSupport.createPartnerAssociation();
        }
        int sequence;
        try {
            sequence = (Integer) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            sequence = DomainTestSupport.getNonRepeatableIntValue(testKey++);
        }
        Partner partner = Partner.partnerFactory(partnerAssociation, sequence);
        return partner;
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param partnerListSize
     */
    public static List<Partner> createPartnerList(int partnerListSize) {
        return createPartnerList(partnerListSize, 1);
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param partnerListSize
     * @param partnerAssociationListSize
     */
    public static List<Partner> createPartnerList(int partnerListSize, int partnerAssociationListSize) {
        List<PartnerAssociation> partnerAssociationList = PartnerAssociationTestSupport.createPartnerAssociationList(partnerAssociationListSize);

        return createPartnerList(partnerListSize, partnerAssociationList);
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param partnerListSize
     * @param partnerAssociationList
     */
    public static List<Partner> createPartnerList(int partnerListSize, List<PartnerAssociation> partnerAssociationList) {
        List<Partner> partnerList = new ArrayList<Partner>();
        for (PartnerAssociation partnerAssociation: partnerAssociationList) {
	        for (int i=0;i<partnerListSize;i++) {
    	        partnerList.add(createPartner(partnerAssociation));
        	}
        }
        return partnerList;
    }

}
