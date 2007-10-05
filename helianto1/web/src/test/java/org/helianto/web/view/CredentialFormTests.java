package org.helianto.web.view;

import java.io.Serializable;

import junit.framework.TestCase;

import org.helianto.core.Credential;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CredentialFormTests extends TestCase {

	private CredentialForm credentialForm;
	
    public void testIsSerializable() {
        assertTrue(credentialForm instanceof Serializable);
    }
    
    public void testCredential() {
    	Credential credential = new Credential();
    	credentialForm.setCredential(credential);
        assertSame(credential, credentialForm.getCredential());
    }
    
    public void testSendOption1() {
    	char sendOption = CredentialForm.SEND_CURRENT_PASSWORD;
    	credentialForm.setSendOption(sendOption);
        assertEquals(sendOption, credentialForm.getSendOption());
    }
    
    public void testSendOption2() {
    	char sendOption = CredentialForm.SEND_NEW_PASSWORD;
    	credentialForm.setSendOption(sendOption);
        assertEquals(sendOption, credentialForm.getSendOption());
    }
    
    public void testSendOption3() {
    	char sendOption = CredentialForm.VERIFY_PASSWORD_ONLINE;
    	credentialForm.setSendOption(sendOption);
        assertEquals(sendOption, credentialForm.getSendOption());
    }
    
	@Override
	public void setUp() {
		credentialForm = new CredentialForm();
	}
}
