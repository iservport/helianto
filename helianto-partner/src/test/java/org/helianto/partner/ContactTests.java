package org.helianto.partner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Identity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.partner.domain.Contact;
import org.helianto.partner.domain.PrivateEntity;
import org.junit.Test;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ContactTests {
    
    /**
     * Test <code>Contact</code> static factory method.
     */
	@Test
    public void constructor() {
        PrivateEntity partnerRegistry = new PrivateEntity();
        int sequence = DomainTestSupport.INT_TEST_VALUE;
        Identity identity = new Identity();
        
        Contact contact = new Contact(partnerRegistry, sequence, identity);
        
        assertSame(partnerRegistry, contact.getPartnerRegistry());
        assertEquals(sequence, contact.getSequence());
        assertSame(identity, contact.getOwner());
        
    }
    
}
    
    
