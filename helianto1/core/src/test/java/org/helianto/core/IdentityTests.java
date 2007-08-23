package org.helianto.core;

import java.util.Date;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

/**
 * <code>Identity</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class IdentityTests extends TestCase {
    
    /**
     * Test <code>Identity</code> static factory method.
     */
    public void testIdentityFactory() {
        String principal = DomainTestSupport.STRING_TEST_VALUE;
        
        Identity identity = Identity.identityFactory(principal);
        
        assertEquals(principal.toLowerCase(), identity.getPrincipal());
        
    }
    
    /**
     * Test <code>Identity</code> static factory method.
     */
    public void testIdentityFactoryFull() {
        Identity identity = Identity.identityFactory("PRINCIPAL", "OPTIONAL_ALIAS");
        
        assertEquals("PRINCIPAL".toLowerCase(), identity.getPrincipal());
        assertEquals("OPTIONAL_ALIAS", identity.getOptionalAlias());
        assertNotNull(identity.getCreated());
        assertTrue(identity.getCreated() instanceof Date);
        assertEquals(IdentityType.NOT_ADDRESSABLE.getValue(), identity.getIdentityType());
        assertEquals(Notification.BY_REQUEST.getValue(), identity.getNotification());
        assertEquals(Appellation.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getAppellation());
        assertEquals(Gender.NOT_SUPPLIED.getValue(),
                identity.getPersonalData().getGender());
    }

    /**
     * Test <code>Identity</code> equals() method.
     */
    public void testIdentityEquals() {
        String principal = DomainTestSupport.STRING_TEST_VALUE;
        
        Identity identity = Identity.identityFactory(principal);
        Identity copy = (Identity) DomainTestSupport.minimalEqualsTest(identity);
        
        copy.setPrincipal(null);
        assertFalse(identity.equals(copy));

        copy.setPrincipal(principal);

        assertTrue(identity.equals(copy));
    }

}
    
    
