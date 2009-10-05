package org.helianto.core.security;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnonymousUserDetailsTests {
	
	@Test
	public void anonymous() {
		PublicUserDetails pud = new AnonymousUserDetails();
		assertEquals("ANONYMOUS".toLowerCase(), pud.getUser().getIdentity().getPrincipal());
	}

}
