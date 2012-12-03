package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PrivateSequence;

/**
 * Class to support <code>InternalEnumeratorDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InternalEnumeratorTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>InternalEnumerator</code>.
     * @param entity optional Entity 
     * @param typeName optional String 
     */
    public static PrivateSequence createInternalEnumerator(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String typeName;
        try {
            typeName = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            typeName = DomainTestSupport.getNonRepeatableStringValue(testKey++, 12);
        }
        PrivateSequence internalEnumerator =  new PrivateSequence(entity, typeName);
        return internalEnumerator;
    }

    /**
     * Test support method to create a <code>InternalEnumerator</code> list.
     *
     * @param internalEnumeratorListSize
     */
    public static List<PrivateSequence> createInternalEnumeratorList(int internalEnumeratorListSize) {
        return createInternalEnumeratorList(internalEnumeratorListSize, 1);
    }

    /**
     * Test support method to create a <code>InternalEnumerator</code> list.
     *
     * @param internalEnumeratorListSize
     * @param entityListSize
     */
    public static List<PrivateSequence> createInternalEnumeratorList(int internalEnumeratorListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createInternalEnumeratorList(internalEnumeratorListSize, entityList);
    }

    /**
     * Test support method to create a <code>InternalEnumerator</code> list.
     *
     * @param internalEnumeratorListSize
     * @param entityList
     */
    public static List<PrivateSequence> createInternalEnumeratorList(int internalEnumeratorListSize, List<Entity> entityList) {
        List<PrivateSequence> internalEnumeratorList = new ArrayList<PrivateSequence>();
        for (Entity entity: entityList) {
	        for (int i=0;i<internalEnumeratorListSize;i++) {
    	        internalEnumeratorList.add(createInternalEnumerator(entity));
        	}
        }
        return internalEnumeratorList;
    }

}
