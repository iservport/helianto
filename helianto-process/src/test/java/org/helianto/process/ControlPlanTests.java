package org.helianto.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
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

        ControlPlan controlPlan = new ControlPlan(entity, "CODE");

        assertTrue(controlPlan instanceof ControlPlan);
		assertSame(entity, controlPlan.getEntity());
        assertEquals("CODE", controlPlan.getDocCode());
        
    }
    
    /**
     * Test <code>ControlPlan</code> equals() method.
     */
	@Test
    public void controlPlanEquals() {
    	Entity entity = new Entity();

        ControlPlan controlPlan = new ControlPlan(entity, "CODE");

        ControlPlan copy = (ControlPlan) DomainTestSupport.minimalEqualsTest(controlPlan);
        
        copy.setEntity(null);
        copy.setDocCode("CODE");
        assertFalse(controlPlan.equals(copy));

        copy.setEntity(entity);
        copy.setDocCode("");
        assertFalse(controlPlan.equals(copy));

        copy.setEntity(entity);
        copy.setDocCode("CODE");

        assertTrue(controlPlan.equals(copy));
    }
    
}
