package org.helianto.process;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.process.Unit;


/**
 * <code>Unit</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UnitTests extends TestCase {
    
    /**
     * Test <code>Unit</code> static factory method.
     */
    public void testUnitFactory() {
        Entity entity = new Entity();
        String unitCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Unit unit = Unit.unitFactory(entity, unitCode);
        
        assertSame(entity, unit.getEntity());
        assertEquals(unitCode, unit.getUnitCode());
        
    }
    
    /**
     * Test <code>Unit</code> equals() method.
     */
    public void testUnitEquals() {
        Entity entity = new Entity();
        String unitCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Unit unit = Unit.unitFactory(entity, unitCode);
        Unit copy = (Unit) DomainTestSupport.minimalEqualsTest(unit);
        
        copy.setEntity(null);
        copy.setUnitCode(unitCode);
        assertFalse(unit.equals(copy));

        copy.setEntity(entity);
        copy.setUnitCode(null);
        assertFalse(unit.equals(copy));

        copy.setEntity(entity);
        copy.setUnitCode(unitCode);

        assertTrue(unit.equals(copy));
    }

}
    
    
