package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.Unit;


/**
 * Class to support <code>UnitDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UnitTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Unit</code>.
     * @param entity optional Entity 
     * @param unitCode optional String 
     */
    public static Unit createUnit(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String unitCode;
        try {
            unitCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            unitCode = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        Unit unit = Unit.unitFactory(entity, unitCode);
        return unit;
    }

    /**
     * Test support method to create a <code>Unit</code> list.
     *
     * @param unitListSize
     */
    public static List<Unit> createUnitList(int unitListSize) {
        return createUnitList(unitListSize, 1);
    }

    /**
     * Test support method to create a <code>Unit</code> list.
     *
     * @param unitListSize
     * @param entityListSize
     */
    public static List<Unit> createUnitList(int unitListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createUnitList(unitListSize, entityList);
    }

    /**
     * Test support method to create a <code>Unit</code> list.
     *
     * @param unitListSize
     * @param entityList
     */
    public static List<Unit> createUnitList(int unitListSize, List<Entity> entityList) {
        List<Unit> unitList = new ArrayList<Unit>();
        for (Entity entity: entityList) {
	        for (int i=0;i<unitListSize;i++) {
    	        unitList.add(createUnit(entity));
        	}
        }
        return unitList;
    }

}
