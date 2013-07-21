package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.def.ActivityState;
import org.helianto.core.def.Encription;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Test;

/**
 * <code>Credential</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CredentialTests {
    
	@Test
    public void passwordConstructor() {
        Identity identity = new Identity();
        
        Credential credential = new Credential(identity, "TEST");
        
        assertSame(identity, credential.getIdentity());
        assertSame("TEST", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertFalse(credential.isPasswordDirty());
        assertNotNull(credential.getExpirationDate());
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
        
    }
    
	@Test
    public void principalConstructor() {
        Credential credential = new Credential("PRINCIPAL", "TEST");
        
        assertEquals("principal", credential.getIdentity().getPrincipal());
        assertSame("TEST", credential.getPassword());
        assertEquals("", credential.getVerifyPassword());
        assertFalse(credential.isPasswordDirty());
        assertNotNull(credential.getExpirationDate());
        assertEquals(ActivityState.INITIAL.getValue(), credential.getCredentialState());
        assertEquals(Encription.PLAIN_PASSWORD.getValue(), credential.getEncription());
        
    }
    
	@Test
    public void passwordFactory() {
        String password = Credential.passwordFactory();
        
        assertEquals(Credential.DEFAULT_PASSWORD_SIZE, password.length());
        for (int i =0; i<Credential.DEFAULT_PASSWORD_SIZE;i++) {
            assertTrue(Credential.ALLOWED_CHARS_IN_PASSWORD.indexOf(password.charAt(i))>-1);
        }
        
    }
    
	@Test
    public void credentialExpired() {
    	Credential credential = new Credential();
    	assertNotNull(credential.getExpirationDate());
    	Date now = new Date();
    	credential.setExpirationDate(new Date(now.getTime()-1));
    	assertTrue(credential.isExpired());
    }
    
	@Test
    public void credentialNeverExpires() {
    	Credential credential = new Credential();
    	credential.setExpirationDate(null);
    	assertFalse(credential.isExpired());
    }
    
	@Test
    public void credentialExpiredLongTimeAgo() {
    	Date expirationDate = new Date(1000);
    	Credential credential = new Credential();
    	credential.setExpirationDate(expirationDate);
    	assertTrue(credential.isExpired());
    }
    
	@Test
    public void credentialNotExpired() {
    	Date expirationDate = new Date(Long.MAX_VALUE);
    	Credential credential = new Credential();
    	credential.setExpirationDate(expirationDate);
    	assertFalse(credential.isExpired());
    }
    
    /**
     * Test <code>Credential</code> password verification.
     */
	@Test
    public void verifyPasswordSuccess() {
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
    
	@Test
    public void verifyPasswordError() {
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
    
	@Test
    public void credentialEquals() {
        Identity identity = new Identity();
        
        Credential credential = new Credential(identity, "");
        Credential copy = (Credential) DomainTestSupport.minimalEqualsTest(credential);
        
        copy.setIdentity(null);
        assertFalse(credential.equals(copy));

        copy.setIdentity(identity);

        assertTrue(credential.equals(copy));
    }

}
    
    
