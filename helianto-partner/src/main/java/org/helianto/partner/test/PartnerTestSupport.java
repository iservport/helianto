package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.partner.Partner;
import org.helianto.partner.PrivateEntity;

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
        return PartnerTestSupport.createPartner(PrivateEntityTestSupport.createPartnerRegistry(entity));
    }

    /**
     * Test support method to create a <code>Partner</code>.
     * 
     * @param privateEntity 
     */
    public static Partner createPartner(PrivateEntity privateEntity) {
        Partner partner = new Partner(privateEntity);
        return partner;
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param partnerListSize
     */
    public static List<Partner> createPartnerList(int partnerListSize) {
        List<PrivateEntity> privateEntityList = PrivateEntityTestSupport.createPartnerRegistryList(partnerListSize);

        return createPartnerList(privateEntityList);
    }

    /**
     * Test support method to create a <code>Partner</code> list.
     *
     * @param privateEntityList
     */
    public static List<Partner> createPartnerList(List<PrivateEntity> privateEntityList) {
        List<Partner> partnerList = new ArrayList<Partner>();
        for (PrivateEntity privateEntity: privateEntityList) {
   	        partnerList.add(createPartner(privateEntity));
        }
        return partnerList;
    }

}
