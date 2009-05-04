package org.helianto.partner;

import org.helianto.core.Identity;
import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.PartnerRegistry;

/**
 * <code>Address</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ContactTests extends TestCase {
    
    /**
     * Test <code>Contact</code> static factory method.
     */
    public void testContactFactory() {
        PartnerRegistry partnerRegistry = new PartnerRegistry();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        Identity identity = new Identity();
        
        Contact contact = Contact.contactFactory(partnerRegistry, sequence, identity);
        
        assertSame(partnerRegistry, contact.getPartnerRegistry());
        assertEquals(sequence, contact.getSequence());
        assertTrue(partnerRegistry.getAddresses().contains(contact));
        assertSame(identity, contact.getIdentity());
        
    }
    
}
    
    
