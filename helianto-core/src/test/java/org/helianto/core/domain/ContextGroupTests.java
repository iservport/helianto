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
		ContextGroup contextGroup = new ContextGroup(context, "ALL", "All users");
		assertSame(context, contextGroup.getContext());
		assertEquals("ALL", contextGroup.getContextGroupCode());
		assertEquals("All users", contextGroup.getContextGroupName());
		assertEquals(contextGroup, new ContextGroup(context, "ALL"));
	}

}
