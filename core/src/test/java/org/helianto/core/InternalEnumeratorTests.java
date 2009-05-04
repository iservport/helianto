package org.helianto.core;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Entity;

import org.helianto.core.InternalEnumerator;

/**
 * <code>InternalEnumerator</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InternalEnumeratorTests extends TestCase {
    
    /**
     * Test <code>InternalEnumerator</code> static factory method.
     */
    public void testInternalEnumeratorFactory() {
        Entity entity = new Entity();
        String typeName = DomainTestSupport.STRING_TEST_VALUE;
        
        InternalEnumerator internalEnumerator = InternalEnumerator.internalEnumeratorFactory(entity, typeName);
        
        assertSame(entity, internalEnumerator.getEntity());
        assertEquals(typeName, internalEnumerator.getTypeName());
        
    }
    
    /**
     * Test <code>InternalEnumerator</code> equals() method.
     */
    public void testInternalEnumeratorEquals() {
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
    
    
