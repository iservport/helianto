package org.helianto.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.PublicSequence;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>InternalEnumerator</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PublicEnumeratorTests {
    
	@Test
    public void equality() {
        Operator operator = new Operator("TEST");
        String typeName = DomainTestSupport.STRING_TEST_VALUE;
        
        PublicSequence publicEnumerator = new PublicSequence(operator, typeName);
        PublicSequence copy = (PublicSequence) DomainTestSupport.minimalEqualsTest(publicEnumerator);
        
        copy.setOperator(null);
        copy.setTypeName(typeName);
        assertFalse(publicEnumerator.equals(copy));

        copy.setOperator(operator);
        copy.setTypeName(null);
        assertFalse(publicEnumerator.equals(copy));

        copy.setOperator(operator);
        copy.setTypeName(typeName);

        assertTrue(publicEnumerator.equals(copy));
    }

}
    
    
