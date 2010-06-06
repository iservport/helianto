package org.helianto.partner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityTests {
	
	@Test
	public void constructor() {
		assertNotNull(new PublicEntity());
	}

	@Test
	public void operatorConstructor() {
		Operator operator = new Operator();
		PublicEntity publicEntity = new PublicEntity(operator);
		assertSame(operator, publicEntity.getOperator());
	}
	
	@Test
	public void entityConstructor() {
		Operator operator = new Operator();
		Entity entity = new Entity(operator);
		PublicEntity publicEntity = new PublicEntity(entity);
		assertSame(operator, publicEntity.getOperator());
		assertSame(entity, publicEntity.getEntity());
	}
	
	@Test
	public void equality() {
		Operator operator = new Operator();
		operator.setOperatorName("TEST");
		Entity entity = new Entity(operator);
		PublicEntity publicEntity = new PublicEntity();
		PublicEntity other = new PublicEntity();
		
		assertTrue(publicEntity.equals(other));
		publicEntity.setOperator(operator);
		assertFalse(publicEntity.equals(other));
		publicEntity.setEntity(entity);
		assertFalse(publicEntity.equals(other));
		other.setOperator(operator);
		assertFalse(publicEntity.equals(other));
		other.setEntity(entity);
		assertTrue(publicEntity.equals(other));
		assertEquals(publicEntity.hashCode(), other.hashCode());
		other.setOperator(new Operator());
		assertFalse(publicEntity.equals(other));
		other.setEntity(new Entity());
		assertFalse(publicEntity.equals(other));		
	}

}
