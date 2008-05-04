package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;


/**
 * <code>Cause</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CauseTests extends TestCase {
    
    /**
     * Test <code>Cause</code> static factory method.
     */
    public void testCauseFactoryClass() {
        Entity entity = new Entity();

		Cause cause = Cause.causeFactory(entity, Long.MAX_VALUE);

        assertTrue(cause instanceof Cause);
		assertSame(entity, cause.getEntity());
        assertEquals(Long.MAX_VALUE, cause.getInternalNumber());
        
    }
    
    /**
     * Test <code>Cause</code> equals() method.
     */
    public void testCauseEquals() {
        Entity entity = new Entity();
        
		Cause cause = Cause.causeFactory(entity, Long.MAX_VALUE);
        Cause copy = (Cause) DomainTestSupport.minimalEqualsTest(cause);
        
        copy.setEntity(null);
        copy.setInternalNumber(Long.MAX_VALUE);
        assertFalse(cause.equals(copy));

        copy.setEntity(entity);
        copy.setInternalNumber(0);
        assertFalse(cause.equals(copy));

        copy.setEntity(entity);
        copy.setInternalNumber(Long.MAX_VALUE);

        assertTrue(cause.equals(copy));
    }
    
}
