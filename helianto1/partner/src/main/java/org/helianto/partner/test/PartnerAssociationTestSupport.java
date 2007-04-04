package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;

import org.helianto.partner.PartnerAssociation;

/**
 * Class to support <code>PartnerAssociationDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerAssociationTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>PartnerAssociation</code>.
     * @param entity optional Entity 
     * @param partnerAlias optional String 
     */
    public static PartnerAssociation createPartnerAssociation(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
String partnerAlias;
        try {
            partnerAlias = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            partnerAlias = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        PartnerAssociation partnerAssociation = PartnerAssociation.partnerAssociationFactory(entity, partnerAlias);
        return partnerAssociation;
    }

    /**
     * Test support method to create a <code>PartnerAssociation</code> list.
     *
     * @param partnerAssociationListSize
     */
    public static List<PartnerAssociation> createPartnerAssociationList(int partnerAssociationListSize) {
        return createPartnerAssociationList(partnerAssociationListSize, 1);
    }

    /**
     * Test support method to create a <code>PartnerAssociation</code> list.
     *
     * @param partnerAssociationListSize
     * @param entityListSize
     */
    public static List<PartnerAssociation> createPartnerAssociationList(int partnerAssociationListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createPartnerAssociationList(partnerAssociationListSize, entityList);
    }

    /**
     * Test support method to create a <code>PartnerAssociation</code> list.
     *
     * @param partnerAssociationListSize
     * @param entityList
     */
    public static List<PartnerAssociation> createPartnerAssociationList(int partnerAssociationListSize, List<Entity> entityList) {
        List<PartnerAssociation> partnerAssociationList = new ArrayList<PartnerAssociation>();
        for (Entity entity: entityList) {
	        for (int i=0;i<partnerAssociationListSize;i++) {
    	        partnerAssociationList.add(createPartnerAssociation(entity));
        	}
        }
        return partnerAssociationList;
    }

}
