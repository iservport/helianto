package org.helianto.partner.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
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

	@Test
    public void equality() {
		Entity entity = new Entity(new Operator("DEFAULT"));
        PrivateEntity partnerRegistry = new PrivateEntity(entity, "TEST");
        
        Division division = new Division();
        Division other = new Division();
        
        assertTrue(division.equals(other));
        
        division.setPrivateEntity(partnerRegistry);
        assertFalse(division.equals(other));
        other.setPrivateEntity(partnerRegistry);
        assertTrue(division.equals(other));
        assertEquals(division.hashCode(), other.hashCode());
        assertFalse(division.equals(new Partner(partnerRegistry)));
    }

}
