package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.def.AddressType;
import org.helianto.core.def.PrivacyLevel;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;
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
        PrivateEntity partnerRegistry = new PrivateEntity();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        PrivateAddress address = new PrivateAddress(partnerRegistry, sequence);
        
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
        PrivateEntity privateEntity = new PrivateEntity();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        
        PrivateAddress address = new PrivateAddress(privateEntity, sequence);
        PrivateAddress copy = (PrivateAddress) DomainTestSupport.minimalEqualsTest(address);
        
        copy.setPrivateEntity(null);
        copy.setSequence(sequence);
        assertFalse(address.equals(copy));

        copy.setPrivateEntity(privateEntity);
        copy.setSequence(0);
        assertFalse(address.equals(copy));

        copy.setPrivateEntity(privateEntity);
        copy.setSequence(sequence);

        assertTrue(address.equals(copy));
    }

}
    
    
