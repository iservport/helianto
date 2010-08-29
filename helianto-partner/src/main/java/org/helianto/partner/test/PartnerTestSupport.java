package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
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
     */
    public static Partner createPartner() {
        return PartnerTestSupport.createPartner(EntityTestSupport.createEntity());
    }

    /**
     * Test support method to create a <code>Partner</code>.
     * 
     * @param entity 
     */
    public static Partner createPartner(Entity entity) {
        return PartnerTestSupport.createPartner(PartnerRegistryTestSupport.createPartnerRegistry(entity));
    }

    /**
     * Test support method to create a <code>Partner</code>.
     * 
     * @param partnerRegistry 
     */
    public static Partner createPartner(PartnerRegistry partnerRegistry) {
        Partner partner = new Partner(partnerRegistry);
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
