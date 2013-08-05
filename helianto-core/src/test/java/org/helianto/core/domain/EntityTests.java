package org.helianto.core.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class EntityTests {
	
	@Test
	public void constructor() {
		assertNotNull(new Entity());
	}
	
	@Test
	public void operatorConstructor() {
		Operator operator = new Operator();
		Entity entity = new Entity(operator);
		assertSame(operator, entity.getOperator());
	}

}
