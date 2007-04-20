package org.helianto.core;

import junit.framework.TestCase;

import org.helianto.core.test.DomainTestSupport;

/**
 * <code>Credential</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CredentialTests extends TestCase {
    
    /**
     * Test <code>Credential</code> static factory method.
     */
    public void testCredentialFactory() {
        Identity identity = new Identity();
        
        Credential credential = Credential.credentialFactory(identity, "TEST");
        
        assertSame(identity, credential.getIdentity());
        assertSame("TEST", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertFalse(credential.isPasswordDirty());
        assertNull(credential.getExpired());
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
        
    }
    
    /**
     * Test <code>Credential</code> equals() method.
     */
    public void testCredentialEquals() {
        Identity identity = new Identity();
        
        Credential credential = Credential.credentialFactory(identity, "");
        Credential copy = (Credential) DomainTestSupport.minimalEqualsTest(credential);
        
        copy.setIdentity(null);
        assertFalse(credential.equals(copy));

        copy.setIdentity(identity);

        assertTrue(credential.equals(copy));
    }

}
    
    
