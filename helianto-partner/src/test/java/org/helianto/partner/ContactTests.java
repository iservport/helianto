package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Identity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Address</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ContactTests {
    
    /**
     * Test <code>Contact</code> static factory method.
     */
	@Test
    public void testContactFactory() {
        PrivateEntity partnerRegistry = new PrivateEntity();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        Identity identity = new Identity();
        
        Contact contact = Contact.contactFactory(partnerRegistry, sequence, identity);
        
        assertSame(partnerRegistry, contact.getPartnerRegistry());
        assertEquals(sequence, contact.getSequence());
        assertTrue(partnerRegistry.getAddresses().contains(contact));
        assertSame(identity, contact.getIdentity());
        
    }
    
}
    
    
