package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextEventTests {

	@Test
	public void isEqual() {
		
		ContextEvent contextEvent = new ContextEvent();
		assertFalse(contextEvent.equals(null));
		
		ContextEvent other = new ContextEvent();
		assertTrue(contextEvent.equals(other));
		
		Operator operator = new Operator("DEFAULT");
		
		contextEvent.setOperator(operator);
		assertFalse(contextEvent.equals(other));
		contextEvent.setPublicNumber(Long.MAX_VALUE);
		assertFalse(contextEvent.equals(other));
		other.setOperator(operator);
		assertFalse(contextEvent.equals(other));
		other.setPublicNumber(Long.MAX_VALUE);
		assertTrue(contextEvent.equals(other));
		assertEquals(contextEvent.hashCode(), other.hashCode());
		
	}

}
