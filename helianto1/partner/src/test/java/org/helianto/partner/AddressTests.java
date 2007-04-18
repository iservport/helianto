package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.Address;
import org.helianto.partner.PartnerRegistry;

/**
 * <code>Address</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AddressTests extends TestCase {
    
    /**
     * Test <code>Address</code> static factory method.
     */
    public void testAddressFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Address address = Address.addressFactory(partnerRegistry, sequence);
        
        assertSame(partnerRegistry, address.getPartnerRegistry());
        assertEquals(sequence, address.getSequence());
        assertEquals(AddressType.MAIN.getValue(), address.getAddressType());
        assertEquals(PrivacyLevel.PUBLIC.getValue(), address.getPrivacyLevel());
        assertTrue(partnerRegistry.getAddresses().contains(address));
        
    }
    
    /**
     * Test <code>Address</code> equals() method.
     */
    public void testAddressEquals() {
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
    
    
