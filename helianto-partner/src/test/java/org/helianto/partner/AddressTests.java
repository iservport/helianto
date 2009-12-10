package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Address</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AddressTests {
    
    /**
     * Test <code>Address</code> static factory method.
     */
	@Test
    public void addressFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Address address = Address.addressFactory(partnerRegistry, sequence);
        
        assertSame(partnerRegistry, address.getPartnerRegistry());
        assertEquals(sequence, address.getSequence());
        assertEquals(AddressType.MAIN.getValue(), address.getAddressType());
        assertEquals(PrivacyLevel.PUBLIC.getValue(), address.getPrivacyLevel());
        
    }
    
    /**
     * Test <code>Address</code> equals() method.
     */
	@Test
    public void addressEquals() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Address address = Address.addressFactory(partnerRegistry, sequence);
        Address copy = (Address) DomainTestSupport.minimalEqualsTest(address);
        
        copy.setPartnerRegistry(null);
        copy.setSequence(sequence);
        assertFalse(address.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setSequence(0);
        assertFalse(address.equals(copy));

        copy.setPartnerRegistry(partnerRegistry);
        copy.setSequence(sequence);

        assertTrue(address.equals(copy));
    }

}
    
    
