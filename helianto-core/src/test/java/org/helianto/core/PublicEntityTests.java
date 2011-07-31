package org.helianto.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.PublicEntity;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityTests {
	
	@Test
	public void constructor() {
		assertTrue(new PublicEntity() instanceof Serializable);
	}

	@Test
	public void operatorConstructor() {
		assertSame(operator, publicEntity.getOperator());
	}
	
	@Test
	public void entityConstructor() {
		Entity entity = new Entity(operator);
		PublicEntity publicEntity = new PublicEntity(entity);
		assertSame(operator, publicEntity.getOperator());
		assertSame(entity, publicEntity.getEntity());
	}
	
	@Test
	public void equality() {
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
	
	private PublicEntity publicEntity;
	private Operator operator;
	
	@Before
	public void setUp() {
		operator = new Operator("DEFAULT");
		publicEntity = new PublicEntity(operator);
	}

}
