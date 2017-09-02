package org.helianto.core.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextGroupTests {

	@Test
	public void domain() {
		ContextGroup contextGroup = new ContextGroup("DEAFULT", "ALL", "All users");
		assertEquals("ALL", contextGroup.getContextGroupCode());
		assertEquals("All users", contextGroup.getContextGroupName());
		assertEquals(contextGroup, new ContextGroup("DEAFULT", "ALL", "All users"));
	}

}
