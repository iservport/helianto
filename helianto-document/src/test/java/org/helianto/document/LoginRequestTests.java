package org.helianto.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class LoginRequestTests {
	
	@Test
	public void loginRequestEquals() {
		LoginRequest loginRequest = new LoginRequest();
		assertFalse(loginRequest.equals(null));
		
		LoginRequest other = new LoginRequest();
		assertTrue(loginRequest.equals(other));
		
		Entity entity = EntityTestSupport.createEntity();
		loginRequest.setEntity(entity);
		assertFalse(loginRequest.equals(other));
		loginRequest.setInternalNumber(Long.MAX_VALUE);
		assertFalse(loginRequest.equals(other));
		other.setEntity(entity);
		assertFalse(loginRequest.equals(other));
		other.setInternalNumber(Long.MAX_VALUE);
		assertTrue(loginRequest.equals(other));
		other.setInternalNumber(Long.MIN_VALUE);
		assertFalse(loginRequest.equals(other));
		other.setEntity(new Entity());
		assertFalse(loginRequest.equals(other));
	}
	
	@Test
	public void constructor() {
		Entity entity = EntityTestSupport.createEntity();
		LoginRequest loginRequest = new LoginRequest(entity, Long.MAX_VALUE);
		assertSame(entity, loginRequest.getEntity());
		assertEquals(Long.MAX_VALUE, loginRequest.getInternalNumber());
	}
	
	@Test
	public void internalNumberKey() {
		LoginRequest loginRequest = new LoginRequest();
		assertEquals("LOGINREQ", loginRequest.getInternalNumberKey());
	}
	
	@Test
	public void confirmation() {
		LoginRequest loginRequest = new LoginRequest();
		assertFalse(loginRequest.validatePrincipal());
		loginRequest.setPrincipal("ONE");
		assertFalse(loginRequest.validatePrincipal());
		loginRequest.setPrincipalConfirmation("one");
		assertTrue(loginRequest.validatePrincipal());
		loginRequest.setPrincipal("TWO");
		assertFalse(loginRequest.validatePrincipal());
	}

}
