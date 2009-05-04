package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>ControlPlan</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ControlPlanTests extends TestCase {
    
    /**
     * Test <code>ControlPlan</code> static factory method.
     */
    public void testControlPlanFactoryClass() {
    	Entity entity = new Entity();

        ControlPlan controlPlan = ControlPlan.controlPlanFactory(entity, Integer.MAX_VALUE);

        assertTrue(controlPlan instanceof ControlPlan);
		assertSame(entity, controlPlan.getEntity());
        assertEquals(Integer.MAX_VALUE, controlPlan.getInternalNumber());
        
    }
    
    /**
     * Test <code>ControlPlan</code> equals() method.
     */
    public void testControlPlanEquals() {
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
