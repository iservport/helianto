package org.helianto.core.test;

import org.helianto.core.domain.Entity;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to support <code>Entity</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EntityTestSupport {

    public static Entity entity = createEntity();
    public static int testKey;

    /**
     * Test support method to create an <code>Entity</code>.
     * 
     * @param id
     */
    public static Entity createEntity(int id) {
        Entity entity = EntityTestSupport.createEntity();
        entity.setId(id);
        return entity;
    }

    /**
     * Test support method to create an <code>Entity</code>.
     */
    public static Entity createEntity() {
        Entity entity = EntityTestSupport.createEntity("DEFAULT");
        return entity;
    }

    /**
     * Test support method to create an <code>Entity</code>.
     */
    public static Entity createEntity(String contextName) {
        Entity entity = EntityTestSupport.createEntity(contextName, DomainTestSupport.getNonRepeatableStringValue(testKey++, 20));
        return entity;
    }

    /**
     * Test support method to create an <code>Entity</code>.
     */
    public static Entity createEntity(String contextName, String alias) {
        Entity entity = new Entity(contextName, alias);
        return entity;
    }

    /**
     * Test support method to create a <code>Entity</code> list.
     *
     * @param entityListSize
     */
    public static List<Entity> createEntityList(int entityListSize) {
        List<Entity> entityList = new ArrayList<Entity>();
        for (int i=0;i<entityListSize;i++) {
            entityList.add(createEntity("DEFAULT"));
        }
        return entityList;
    }

}
