package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.def.Appellation;
import org.helianto.core.def.Gender;
import org.helianto.core.def.Notification;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Identity</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityTests {
    
    /**
     * Test <code>Identity</code> static factory method.
     */
	@Test
    public void identityFactory() {
        String principal = DomainTestSupport.STRING_TEST_VALUE;
        
        Identity identity = new Identity(principal);
        
        assertEquals(principal.toLowerCase(), identity.getPrincipal());
        
    }
    
	@Test
    public void identityFactoryFull() {
        Identity identity = new Identity("PRINCIPAL", "OPTIONAL_ALIAS");
        
        assertEquals("PRINCIPAL".toLowerCase(), identity.getPrincipal());
        assertEquals("OPTIONAL_ALIAS", identity.getOptionalAlias());
        assertNotNull(identity.getCreated());
        assertTrue(identity.getCreated() instanceof Date);
        assertEquals(IdentityType.PERSONAL_EMAIL .getValue(), identity.getIdentityType());
        assertEquals(Notification.AUTOMATIC.getValue(), identity.getNotification());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getGender());
    }

	@Test
    public void identityEquals() {
        String principal = DomainTestSupport.STRING_TEST_VALUE;
        
        Identity identity = new Identity(principal);
        Identity copy = (Identity) DomainTestSupport.minimalEqualsTest(identity);
        
        copy.setPrincipal(null);
        assertFalse(identity.equals(copy));

        copy.setPrincipal(principal);

        assertTrue(identity.equals(copy));
    }

}
    
    
