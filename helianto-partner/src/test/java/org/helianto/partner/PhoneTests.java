package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Phone</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PhoneTests {
    
    /**
     * Test <code>Phone</code> static factory method.
     */
	@Test
    public void testPhoneFactory() {
        Address address = new Address();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Phone phone = Phone.phoneFactory(address, sequence);
        
        assertSame(address, phone.getAddress());
        assertEquals(sequence, phone.getSequence());
        
    }
    
    /**
     * Test <code>Phone</code> equals() method.
     */
	@Test
    public void testPhoneEquals() {
        Address address = new Address();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Phone phone = Phone.phoneFactory(address, sequence);
        Phone copy = (Phone) DomainTestSupport.minimalEqualsTest(phone);
        
        copy.setAddress(null);
        copy.setSequence(sequence);
        assertFalse(phone.equals(copy));

        copy.setAddress(address);
        copy.setSequence(0);
        assertFalse(phone.equals(copy));

        copy.setAddress(address);
        copy.setSequence(sequence);

        assertTrue(phone.equals(copy));
    }

}
    
    
