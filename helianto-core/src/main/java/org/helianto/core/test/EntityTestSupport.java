package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;



import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.test.OperatorTestSupport;


/**
 * Class to support <code>EntityDao</code> tests.
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
    public static Entity createEntity(long id) {
        Entity entity = EntityTestSupport.createEntity();
        entity.setId(id);
        return entity;
    }

    /**
     * Test support method to create an <code>Entity</code>.
     */
    public static Entity createEntity() {
        Entity entity = EntityTestSupport.createEntity(OperatorTestSupport.createOperator());
        return entity;
    }

    /**
     * Test support method to create an <code>Entity</code>.
     * 
     * @param operator
     */
    public static Entity createEntity(Operator operator) {
        Entity entity = EntityTestSupport.createEntity(operator, DomainTestSupport.getNonRepeatableStringValue(testKey++, 20));
        return entity;
    }

    /**
     * Test support method to create an <code>Entity</code>.
     * 
     * @param operator
     * @param alias
     */
    public static Entity createEntity(Operator operator, String alias) {
        Entity entity = new Entity(operator, alias);
        return entity;
    }

    /**
     * Test support method to create a <code>Entity</code> list.
     *
     * @param entityListSize
     */
    public static List<Entity> createEntityList(int entityListSize) {
        return createEntityList(entityListSize, 1);
    }

    /**
     * Test support method to create a <code>Entity</code> list.
     *
     * @param entityListSize
     * @param operatorListSize
     */
    public static List<Entity> createEntityList(int entityListSize, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);

        return createEntityList(entityListSize, operatorList);
    }

    /**
     * Test support method to create a <code>Entity</code> list.
     *
     * @param entityListSize
     * @param operatorList
     */
    public static List<Entity> createEntityList(int entityListSize, List<Operator> operatorList) {
        List<Entity> entityList = new ArrayList<Entity>();
        for (Operator operator: operatorList) {
            for (int i=0;i<entityListSize;i++) {
                entityList.add(createEntity(operator));
            }
        }
        return entityList;
    }

}
