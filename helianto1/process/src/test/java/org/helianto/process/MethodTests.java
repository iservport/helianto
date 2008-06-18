package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;


/**
 * <code>Method</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MethodTests extends TestCase {
    
    /**
     * Test <code>MeasurementTechnique</code> static factory method.
     */
    public void testMethodFactoryClass() {
    	ProcessDocument document = new ProcessDocument();
    	Characteristic characteristic = new Characteristic();

    	Method method = Method.methodFactory(document, characteristic);

        assertTrue(method instanceof Method);
		assertSame(document, method.getDocument());
        assertEquals(characteristic, method.getCharacteristic());
        
    }
    
    /**
     * Test <code>Method</code> equals() method.
     */
    public void testMethodEquals() {
    	ProcessDocument document = new ProcessDocument();
    	Characteristic characteristic = new Characteristic();

    	Method method = Method.methodFactory(document, characteristic);

    	Method copy = (Method) DomainTestSupport.minimalEqualsTest(method);
        
        copy.setDocument(null);
        copy.setCharacteristic(characteristic);
        assertFalse(method.equals(copy));

        copy.setDocument(document);
        copy.setCharacteristic(null);
        assertFalse(method.equals(copy));

        copy.setDocument(document);
        copy.setCharacteristic(characteristic);

        assertTrue(method.equals(copy));
    }
    
}
