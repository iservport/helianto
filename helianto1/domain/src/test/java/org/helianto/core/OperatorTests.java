package org.helianto.core;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.core.Operator;

/**
 * <code>Operator</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorTests extends TestCase {
    
    /**
     * Test <code>Operator</code> static factory method.
     */
    public void testOperatorFactory() {
        String operatorName = DomainTestSupport.STRING_TEST_VALUE;
        
        Operator operator = Operator.operatorFactory(operatorName);
        
        assertEquals(operatorName, operator.getOperatorName());
        
    }
    
    /**
     * Test <code>Operator</code> equals() method.
     */
    public void testOperatorEquals() {
        String operatorName = DomainTestSupport.STRING_TEST_VALUE;
        
        Operator operator = Operator.operatorFactory(operatorName);
        Operator copy = (Operator) DomainTestSupport.minimalEqualsTest(operator);
        
        copy.setOperatorName(null);
        assertFalse(operator.equals(copy));

        copy.setOperatorName(operatorName);

        assertTrue(operator.equals(copy));
    }

}
    
    
