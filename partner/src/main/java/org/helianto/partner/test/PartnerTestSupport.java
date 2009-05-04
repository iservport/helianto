package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;

/**
 * Class to support <code>PartnerDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerTestSupport {

    /**
     * Test support method to create a <code>Partner</code>.
     * @param partnerRegistry optional PartnerRegistry 
     */
    public static Partner createPartner(Object... args) {
        PartnerRegistry partnerRegistry;
        try {
            partnerRegistry = (PartnerRegistry) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerRegistry = PartnerRegistryTestSupport.createPartnerRegistry();
        }
        Partner partner = Partner.partnerFactory(partnerRegistry);
        return partner;
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param partnerListSize
     */
    public static List<Partner> createPartnerList(int partnerListSize) {
        List<PartnerRegistry> partnerRegistryList = PartnerRegistryTestSupport.createPartnerRegistryList(partnerListSize);

        return createPartnerList(partnerRegistryList);
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param partnerRegistryList
     */
    public static List<Partner> createPartnerList(List<PartnerRegistry> partnerRegistryList) {
        List<Partner> partnerList = new ArrayList<Partner>();
        for (PartnerRegistry partnerRegistry: partnerRegistryList) {
   	        partnerList.add(createPartner(partnerRegistry));
        }
        return partnerList;
    }

}
