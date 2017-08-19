package org.helianto.core.domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextGroupTests {

	@Test
	public void domain() {
		Operator context = new Operator("DEAFULT");
		ContextGroup contextGroup = new ContextGroup("DEAFULT", "ALL", "All users");
		assertEquals("ALL", contextGroup.getContextGroupCode());
		assertEquals("All users", contextGroup.getContextGroupName());
		assertEquals(contextGroup, new ContextGroup("DEAFULT", "ALL", "All users"));
	}

}
