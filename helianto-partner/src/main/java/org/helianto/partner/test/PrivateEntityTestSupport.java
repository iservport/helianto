package org.helianto.partner.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;

import org.helianto.partner.PrivateEntity;

/**
 * Class to support <code>PrivateEntity</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PrivateEntityTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>PartnerAssociation</code>.
     * @param entity optional Entity 
     * @param partnerAlias optional String 
     */
    public static PrivateEntity createPartnerRegistry(Object... args) {
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
        PrivateEntity partnerRegistry = new PrivateEntity(entity, partnerAlias);
        partnerRegistry.setPartnerName("Name of "+partnerAlias);
        return partnerRegistry;
    }

    /**
     * Test support method to create a <code>PartnerAssociation</code> list.
     *
     * @param partnerRegistryListSize
     */
    public static List<PrivateEntity> createPartnerRegistryList(int partnerRegistryListSize) {
        return createPartnerRegistryList(partnerRegistryListSize, 1);
    }

    /**
     * Test support method to create a <code>PartnerAssociation</code> list.
     *
     * @param partnerRegistryListSize
     * @param entityListSize
     */
    public static List<PrivateEntity> createPartnerRegistryList(int partnerRegistryListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createPartnerRegistryList(partnerRegistryListSize, entityList);
    }

    /**
     * Test support method to create a <code>PartnerAssociation</code> list.
     *
     * @param partnerRegistryListSize
     * @param entityList
     */
    public static List<PrivateEntity> createPartnerRegistryList(int partnerRegistryListSize, List<Entity> entityList) {
        List<PrivateEntity> partnerRegistryList = new ArrayList<PrivateEntity>();
        for (Entity entity: entityList) {
	        for (int i=0;i<partnerRegistryListSize;i++) {
    	        partnerRegistryList.add(createPartnerRegistry(entity));
        	}
        }
        return partnerRegistryList;
    }

}
