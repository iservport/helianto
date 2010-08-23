package org.helianto.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>InternalEnumerator</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class InternalEnumeratorTests {
    
	@Test
    public void equality() {
        Entity entity = new Entity();
        String typeName = DomainTestSupport.STRING_TEST_VALUE;
        
        InternalEnumerator internalEnumerator = new InternalEnumerator(entity, typeName);
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
    
    
