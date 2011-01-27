package org.helianto.core.criteria;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SelectFromBuilderTests {
	
	@Test
	public void plain() {
		SelectFromBuilder builder = new SelectFromBuilder(Entity.class);
		assertEquals("select entity from Entity entity ", builder.createSelectFrom().getAsString());
	}

	@Test
	public void multiple() {
		SelectFromBuilder builder = new SelectFromBuilder(Entity.class);
		assertEquals("select entity.a, entity.b from Entity entity ", builder.createSelectFrom("a", "b").getAsString());
	}

	@Test
	public void join() {
		SelectFromBuilder builder = new SelectFromBuilder(User.class);
		assertEquals("select user from User user inner " +
				"join user.parentAssociations as parentAssociation ", builder.createSelectFrom().appendInnerJoin("parentAssociations", "parentAssociation").getAsString());
	}

}
