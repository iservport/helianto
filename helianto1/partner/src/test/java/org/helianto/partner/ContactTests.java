package org.helianto.partner;

import org.helianto.core.Identity;
import org.helianto.core.test.DomainTestSupport;

import junit.framework.TestCase;


import org.helianto.partner.PartnerAssociation;

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
        PartnerAssociation partnerAssociation = new PartnerAssociation();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        Identity identity = new Identity();
        
        Contact contact = Contact.contactFactory(partnerAssociation, sequence, identity);
        
        assertSame(partnerAssociation, contact.getPartnerAssociation());
        assertEquals(sequence, contact.getSequence());
        assertTrue(partnerAssociation.getAddresses().contains(contact));
        assertSame(identity, contact.getIdentity());
        
    }
    
}
    
    
