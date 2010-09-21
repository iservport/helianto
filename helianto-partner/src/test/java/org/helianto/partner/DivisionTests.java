package org.helianto.partner;

import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DivisionTests {
	
	@Test
	public void partner() {
		Entity entity = new Entity();
		PrivateEntity registry = new PrivateEntity(entity);
		Partner customer = new Customer(registry);
		Division division = new Division(customer);
		assertSame(registry, division.getPrivateEntity());
	}

	@Test(expected=IllegalArgumentException.class)
	public void partnerError() {
		Entity entity = new Entity();
		PrivateEntity registry = new PrivateEntity(entity);
		Partner other = new Division(registry);
		Division division = new Division(other);
		assertSame(registry, division.getPrivateEntity());
	}

}
