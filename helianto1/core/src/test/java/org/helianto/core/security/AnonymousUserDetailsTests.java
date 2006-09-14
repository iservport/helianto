package org.helianto.core.security;

import junit.framework.TestCase;

public class AnonymousUserDetailsTests extends TestCase {
	
	public void testAnonymous() {
		PublicUserDetails pud = new AnonymousUserDetails();
		assertEquals("ANONYMOUS", pud.getUser().getIdentity().getPrincipal());
	}

}
