package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.Address;
import org.helianto.partner.Phone;

/**
 * <code>Phone</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PhoneTests extends TestCase {
    
    /**
     * Test <code>Phone</code> static factory method.
     */
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
    
    
