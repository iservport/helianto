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
        assertNotNull(credential.getExpirationDate());
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
        assertNotNull(credential.getExpirationDate());
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
    
    public void testCredentialExpired() {
    	Credential credential = Credential.credentialFactory("");
    	assertNotNull(credential.getExpirationDate());
    	for (int i=0; i<100000; i++) { /* consume some CPU */ }
    	assertTrue(credential.isExpired());
    }
    
    public void testCredentialNeverExpires() {
    	Credential credential = Credential.credentialFactory("");
    	credential.setExpirationDate(null);
    	assertFalse(credential.isExpired());
    }
    
    public void testCredentialExpiredLongTimeAgo() {
    	Date expirationDate = new Date(1000);
    	Credential credential = Credential.credentialFactory("");
    	credential.setExpirationDate(expirationDate);
    	assertTrue(credential.isExpired());
    }
    
    public void testCredentialNotExpired() {
    	Date expirationDate = new Date(Long.MAX_VALUE);
    	Credential credential = Credential.credentialFactory("");
    	credential.setExpirationDate(expirationDate);
    	assertFalse(credential.isExpired());
    }
    
    /**
     * Test <code>Credential</code> password verification.
     */
    public void testVerifyPasswordSuccess() {
        Credential credential = new Credential();
        String password = String.valueOf(new Date().getTime());
        credential.setPassword(password);
        credential.setVerifyPassword(password);
        
        assertTrue(credential.isPasswordVerified());
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
        
        assertFalse(credential.isPasswordVerified());
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
    
    
