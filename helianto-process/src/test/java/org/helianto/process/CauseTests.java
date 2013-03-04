package org.helianto.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.process.domain.classic.Cause;
import org.junit.Test;


/**
 * <code>Cause</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CauseTests {
    
    /**
     * Test <code>Cause</code> static factory method.
     */
	@Test
    public void constructor() {
        Entity entity = new Entity();

		Cause cause = new Cause(entity, Long.MAX_VALUE);

        assertTrue(cause instanceof Cause);
		assertSame(entity, cause.getEntity());
        assertEquals(Long.MAX_VALUE, cause.getInternalNumber());
        
    }
    
    /**
     * Test <code>Cause</code> equals() method.
     */
	@Test
    public void causeEquals() {
        Entity entity = new Entity();
        
		Cause cause = new Cause(entity, Long.MAX_VALUE);
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
