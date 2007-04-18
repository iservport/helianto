package org.helianto.core;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;

import org.helianto.core.Operator;

import org.helianto.core.KeyType;

/**
 * <code>KeyType</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeTests extends TestCase {
    
    /**
     * Test <code>KeyType</code> static factory method.
     */
    public void testKeyTypeFactory() {
        Operator operator = new Operator();
        String keyCode = DomainTestSupport.STRING_TEST_VALUE;
        
        KeyType keyType = KeyType.keyTypeFactory(operator, keyCode);
        
        assertSame(operator, keyType.getOperator());
        assertEquals(keyCode, keyType.getKeyCode());
        
    }
    
    /**
     * Test <code>KeyType</code> equals() method.
     */
    public void testKeyTypeEquals() {
        Operator operator = new Operator();
        String keyCode = DomainTestSupport.STRING_TEST_VALUE;
        
        KeyType keyType = KeyType.keyTypeFactory(operator, keyCode);
        KeyType copy = (KeyType) DomainTestSupport.minimalEqualsTest(keyType);
        
        copy.setOperator(null);
        copy.setKeyCode(keyCode);
        assertFalse(keyType.equals(copy));

        copy.setOperator(operator);
        copy.setKeyCode(null);
        assertFalse(keyType.equals(copy));

        copy.setOperator(operator);
        copy.setKeyCode(keyCode);

        assertTrue(keyType.equals(copy));
    }

}
    
    
