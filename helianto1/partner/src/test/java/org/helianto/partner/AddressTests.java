package org.helianto.partner;

import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.Address;
import org.helianto.partner.PartnerAssociation;

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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Address address = Address.addressFactory(partnerAssociation, sequence);
        
        assertSame(partnerAssociation, address.getPartnerAssociation());
        assertEquals(sequence, address.getSequence());
        assertEquals(AddressType.MAIN.getValue(), address.getAddressType());
        assertEquals(PrivacyLevel.PUBLIC.getValue(), address.getPrivacyLevel());
        assertTrue(partnerAssociation.getAddresses().contains(address));
        
    }
    
    /**
     * Test <code>Address</code> equals() method.
     */
    public void testAddressEquals() {
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        Address address = Address.addressFactory(partnerAssociation, sequence);
        Address copy = (Address) DomainTestSupport.minimalEqualsTest(address);
        
        copy.setPartnerAssociation(null);
        copy.setSequence(sequence);
        assertFalse(address.equals(copy));

        copy.setPartnerAssociation(partnerAssociation);
        copy.setSequence(0);
        assertFalse(address.equals(copy));

        copy.setPartnerAssociation(partnerAssociation);
        copy.setSequence(sequence);

        assertTrue(address.equals(copy));
    }

}
    
    
