package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.test.UserGroupTestSupport;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserRequestTests {
	
	@Test
	public void loginRequestEquals() {
		UserRequest userRequest = new UserRequest();
		assertFalse(userRequest.equals(null));
		
		UserRequest other = new UserRequest();
		assertTrue(userRequest.equals(other));
		
		UserGroup userGroup = UserGroupTestSupport.createUserGroup();
		userRequest.setUserGroup(userGroup);
		assertFalse(userRequest.equals(other));
		userRequest.setInternalNumber(Long.MAX_VALUE);
		assertFalse(userRequest.equals(other));
		other.setUserGroup(userGroup);
		assertFalse(userRequest.equals(other));
		other.setInternalNumber(Long.MAX_VALUE);
		assertTrue(userRequest.equals(other));
		other.setInternalNumber(Long.MIN_VALUE);
		assertFalse(userRequest.equals(other));
		other.setUserGroup(new UserGroup());
		assertFalse(userRequest.equals(other));
	}
	
	@Test
	public void constructor() {
		UserGroup userGroup = UserGroupTestSupport.createUserGroup();
		UserRequest userRequest = new UserRequest(userGroup, Long.MAX_VALUE);
		assertSame(userGroup, userRequest.getUserGroup());
		assertEquals(Long.MAX_VALUE, userRequest.getInternalNumber());
	}
	
	@Test
	public void internalNumberKey() {
		UserRequest userRequest = new UserRequest();
		assertEquals("LOGINREQ", userRequest.getInternalNumberKey());
	}
	
	@Test
	public void confirmation() {
		UserRequest userRequest = new UserRequest();
		assertFalse(userRequest.validatePrincipal());
		userRequest.setPrincipal("ONE");
		assertFalse(userRequest.validatePrincipal());
		userRequest.setPrincipalConfirmation("one");
		assertTrue(userRequest.validatePrincipal());
		userRequest.setPrincipal("TWO");
		assertFalse(userRequest.validatePrincipal());
	}

}
