package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>KeyType</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeTests {
     
    /**
     * Test <code>KeyType</code> static factory method.
     */
	@Test
    public void keyTypeFactory() {
        Operator operator = new Operator();
        String keyCode = DomainTestSupport.STRING_TEST_VALUE;
        
        KeyType keyType = KeyType.keyTypeFactory(operator, keyCode);
        
        assertSame(operator, keyType.getOperator());
        assertEquals(keyCode, keyType.getKeyCode());
        
    }
    
    /**
     * Test <code>KeyType</code> equals() method.
     */
	@Test
    public void keyTypeEquals() {
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
    
    
