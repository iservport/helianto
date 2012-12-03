package org.helianto.core.criteria;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SelectBuilderTests {
	
	@Test
	public void plain() {
		SelectBuilder builder = new SelectBuilder(Entity.class);
		assertEquals("select entity ", builder.createSelect().getAsString());
	}

	@Test
	public void multiple() {
		SelectBuilder builder = new SelectBuilder(Entity.class);
		assertEquals("select entity.a, entity.b ", builder.createSelect("a", "b").getAsString());
	}

	@Test
	public void alias() {
		SelectBuilder builder = new SelectBuilder(Entity.class, "abc");
		assertEquals("select abc.a, abc.b ", builder.createSelect("a", "b").getAsString());
	}

}
