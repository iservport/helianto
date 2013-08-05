package org.helianto.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.test.EntityTestSupport;
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
	public void entityConstructor() {
		PublicEntity publicEntity = new PublicEntity(entity);
		assertSame(entity, publicEntity.getEntity());
	}
	
	@Test
	public void equality() {
		PublicEntity publicEntity = new PublicEntity();
		PublicEntity other = new PublicEntity();
		
		assertTrue(publicEntity.equals(other));
		publicEntity.setEntity(entity);
		assertFalse(publicEntity.equals(other));
		other.setEntity(entity);
		assertTrue(publicEntity.equals(other));
		assertEquals(publicEntity.hashCode(), other.hashCode());
		other.setEntity(new Entity());
		assertFalse(publicEntity.equals(other));
	}
	
	private Entity entity;
	
	@Before
	public void setUp() {
		entity = EntityTestSupport.createEntity(1);
	}

}
