package org.helianto.core;

import java.util.Date;

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
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
        
    }
    
    /**
     * Test <code>Credential</code> static factory method.
     */
    public void testCredentialFactoryNoIdentity() {
        Credential credential = Credential.credentialFactory("TEST");
        
        assertEquals("", credential.getIdentity().getPrincipal());
        assertSame("TEST", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertFalse(credential.isPasswordDirty());
        assertNull(credential.getExpired());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
        
    }
    
    /**
     * Test <code>Credential</code> static factory method.
     */
    public void testCredentialFactoryNoIdentityNoPassword() {
        Credential credential = Credential.credentialFactory();
        
        assertEquals("", credential.getIdentity().getPrincipal());
        assertEquals(Credential.DEFAULT_PASSWORD_SIZE, credential.getPassword().length());
        for (int i =0; i<Credential.DEFAULT_PASSWORD_SIZE;i++) {
            assertTrue(Credential.ALLOWED_CHARS_IN_PASSWORD.indexOf(credential.getPassword().charAt(i))!=-1);
        }
        assertEquals("", credential.getVerifyPassword());
        assertFalse(credential.isPasswordDirty());
        assertNull(credential.getExpired());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
        
    }
    
    /**
     * Test <code>Credential</code> static factory method.
     */
    public void testPasswordFactory() {
        String password = Credential.passwordFactory();
        
        assertEquals(Credential.DEFAULT_PASSWORD_SIZE, password.length());
        for (int i =0; i<Credential.DEFAULT_PASSWORD_SIZE;i++) {
            assertTrue(Credential.ALLOWED_CHARS_IN_PASSWORD.indexOf(password.charAt(i))>-1);
        }
        
    }
    
    /**
     * Test <code>Credential</code> password verification.
     */
    public void testVerifyPasswordSuccess() {
        Credential credential = new Credential();
        String password = String.valueOf(new Date().getTime());
        credential.setPassword(password);
        credential.setVerifyPassword(password);
        
        assertTrue(Credential.verifyPassword(credential));
        assertEquals(password, credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertEquals(ActivityState.ACTIVE.getValue(), credential.getCredentialState());
        assertFalse(credential.isPasswordDirty());
    }
    
    /**
     * Test <code>Credential</code> password verification.
     */
    public void testVerifyPasswordError() {
        Credential credential = new Credential();
        String password = String.valueOf(new Date().getTime());
        credential.setPassword(password);
        credential.setVerifyPassword(password+"1");
        
        assertFalse(Credential.verifyPassword(credential));
        assertEquals("", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertEquals(ActivityState.SUSPENDED.getValue(), credential.getCredentialState());
        assertTrue(credential.isPasswordDirty());
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
    
    
