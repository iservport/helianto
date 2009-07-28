package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>InternalEnumerator</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InternalEnumeratorTests {
    
    /**
     * Test <code>InternalEnumerator</code> static factory method.
     */
	@Test
    public void internalEnumeratorFactory() {
        Entity entity = new Entity();
        String typeName = DomainTestSupport.STRING_TEST_VALUE;
        
        InternalEnumerator internalEnumerator = InternalEnumerator.internalEnumeratorFactory(entity, typeName);
        
        assertSame(entity, internalEnumerator.getEntity());
        assertEquals(typeName, internalEnumerator.getTypeName());
        
    }
    
    /**
     * Test <code>InternalEnumerator</code> equals() method.
     */
	@Test
    public void internalEnumeratorEquals() {
        Entity entity = new Entity();
        String typeName = DomainTestSupport.STRING_TEST_VALUE;
        
        InternalEnumerator internalEnumerator = InternalEnumerator.internalEnumeratorFactory(entity, typeName);
        InternalEnumerator copy = (InternalEnumerator) DomainTestSupport.minimalEqualsTest(internalEnumerator);
        
        copy.setEntity(null);
        copy.setTypeName(typeName);
        assertFalse(internalEnumerator.equals(copy));

        copy.setEntity(entity);
        copy.setTypeName(null);
        assertFalse(internalEnumerator.equals(copy));

        copy.setEntity(entity);
        copy.setTypeName(typeName);

        assertTrue(internalEnumerator.equals(copy));
    }

}
    
    
