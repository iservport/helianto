package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;


/**
 * <code>Unit</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UnitTests {
    
    /**
     * Test <code>Unit</code> static factory method.
     */
	@Test
    public void constructor() {
        Entity entity = new Entity();
        String unitCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Unit unit = new Unit(entity, unitCode);
        
        assertSame(entity, unit.getEntity());
        assertEquals(unitCode, unit.getUnitCode());
        assertEquals('1', unit.getPriority());
        
    }
    
    /**
     * Test <code>Unit</code> equals() method.
     */
	@Test
    public void unitEquals() {
        Entity entity = new Entity();
        String unitCode = DomainTestSupport.STRING_TEST_VALUE;
        
        Unit unit = new Unit(entity, unitCode);
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
    
    
