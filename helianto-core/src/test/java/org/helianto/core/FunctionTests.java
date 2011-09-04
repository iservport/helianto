package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Function</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class FunctionTests {
    
	@Test
	public void contructor() {
		Function function = new Function();
		assertTrue(function instanceof Function);
		assertEquals('F', function.getDiscriminator());
	}
    
	@Test
	public void keyContructor() {
		Entity entity = new Entity();
		Function function = new Function(entity, "PRINCIPAL");
		assertSame(entity, function.getEntity());
	}
    
    /**
     * Test <code>User</code> equals() method.
     */
	@Test
    public void userEquals() {
        Entity entity = new Entity();
        
        Function function = new Function(entity, "TEST");
        Function copy = (Function) DomainTestSupport.minimalEqualsTest(function);
        
        copy.setEntity(null);
        copy.setUserKey(null);
        assertFalse(function.equals(copy));

        copy.setEntity(entity);
        copy.setUserKey("TEST");

        assertTrue(function.equals(copy));
    }

}
    
    
