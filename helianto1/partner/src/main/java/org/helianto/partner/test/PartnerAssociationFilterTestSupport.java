package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;

import org.helianto.partner.PartnerAssociationFilter;

/**
 * Class to support <code>PartnerAssociationFilterDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationFilterTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>PartnerAssociationFilter</code>.
     * @param entity optional Entity 
     * @param partnerAssociationFilterAlias optional String 
     */
    public static PartnerAssociationFilter createPartnerAssociationFilter(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
String partnerAssociationFilterAlias;
        try {
            partnerAssociationFilterAlias = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAssociationFilterAlias = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        PartnerAssociationFilter partnerAssociationFilter = PartnerAssociationFilter.partnerAssociationFilterFactory(entity, partnerAssociationFilterAlias);
        return partnerAssociationFilter;
    }

    /**
     * Test support method to create a <code>PartnerAssociationFilter</code> list.
     *
     * @param partnerAssociationFilterListSize
     */
    public static List<PartnerAssociationFilter> createPartnerAssociationFilterList(int partnerAssociationFilterListSize) {
        return createPartnerAssociationFilterList(partnerAssociationFilterListSize, 1);
    }

    /**
     * Test support method to create a <code>PartnerAssociationFilter</code> list.
     *
     * @param partnerAssociationFilterListSize
     * @param entityListSize
     */
    public static List<PartnerAssociationFilter> createPartnerAssociationFilterList(int partnerAssociationFilterListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createPartnerAssociationFilterList(partnerAssociationFilterListSize, entityList);
    }

    /**
     * Test support method to create a <code>PartnerAssociationFilter</code> list.
     *
     * @param partnerAssociationFilterListSize
     * @param entityList
     */
    public static List<PartnerAssociationFilter> createPartnerAssociationFilterList(int partnerAssociationFilterListSize, List<Entity> entityList) {
        List<PartnerAssociationFilter> partnerAssociationFilterList = new ArrayList<PartnerAssociationFilter>();
        for (Entity entity: entityList) {
	        for (int i=0;i<partnerAssociationFilterListSize;i++) {
    	        partnerAssociationFilterList.add(createPartnerAssociationFilter(entity));
        	}
        }
        return partnerAssociationFilterList;
    }

}
