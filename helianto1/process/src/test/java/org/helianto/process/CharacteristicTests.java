package org.helianto.process;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;


/**
 * <code>Characteristic</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CharacteristicTests extends TestCase {
    
    /**
     * Test <code>Characteristic</code> static factory method.
     */
    public void testPartCharacteristicFactoryClass() {
        Part part = new Part();

        Characteristic characteristic = Characteristic.characteristicFactory(part, Integer.MAX_VALUE);

        assertTrue(characteristic instanceof Characteristic);
		assertSame(part, characteristic.getDocument());
        assertEquals(Integer.MAX_VALUE, characteristic.getSequence());
        
    }
    
    /**
     * Test <code>Characteristic</code> static factory method.
     */
    public void testProcessCharacteristicFactoryClass() {
    	Process process = new Process();

        Characteristic characteristic = Characteristic.characteristicFactory(process, Integer.MAX_VALUE);

        assertTrue(characteristic instanceof Characteristic);
		assertSame(process, characteristic.getDocument());
        assertEquals(Integer.MAX_VALUE, characteristic.getSequence());
        
    }
    
    /**
     * Test <code>Characteristic</code> equals() method.
     */
    public void testCharacteristicEquals() {
        Part part = new Part();

        Characteristic characteristic = Characteristic.characteristicFactory(part, Integer.MAX_VALUE);

        Characteristic copy = (Characteristic) DomainTestSupport.minimalEqualsTest(characteristic);
        
        copy.setDocument(null);
        copy.setSequence(Integer.MAX_VALUE);
        assertFalse(characteristic.equals(copy));

        copy.setDocument(part);
        copy.setSequence(0);
        assertFalse(characteristic.equals(copy));

        copy.setDocument(part);
        copy.setSequence(Integer.MAX_VALUE);

        assertTrue(characteristic.equals(copy));
    }
    
}
