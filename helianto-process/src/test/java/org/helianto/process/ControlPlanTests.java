package org.helianto.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;


/**
 * <code>ControlPlan</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ControlPlanTests {
    
    /**
     * Test <code>ControlPlan</code> static factory method.
     */
	@Test
    public void controlPlanFactoryClass() {
    	Entity entity = new Entity();

        ControlPlan controlPlan = ControlPlan.controlPlanFactory(entity, Integer.MAX_VALUE);

        assertTrue(controlPlan instanceof ControlPlan);
		assertSame(entity, controlPlan.getEntity());
        assertEquals(Integer.MAX_VALUE, controlPlan.getInternalNumber());
        
    }
    
    /**
     * Test <code>ControlPlan</code> equals() method.
     */
	@Test
    public void controlPlanEquals() {
    	Entity entity = new Entity();

        ControlPlan controlPlan = ControlPlan.controlPlanFactory(entity, Integer.MAX_VALUE);

        ControlPlan copy = (ControlPlan) DomainTestSupport.minimalEqualsTest(controlPlan);
        
        copy.setEntity(null);
        copy.setInternalNumber(Integer.MAX_VALUE);
        assertFalse(controlPlan.equals(copy));

        copy.setEntity(entity);
        copy.setInternalNumber(0);
        assertFalse(controlPlan.equals(copy));

        copy.setEntity(entity);
        copy.setInternalNumber(Integer.MAX_VALUE);

        assertTrue(controlPlan.equals(copy));
    }
    
}
