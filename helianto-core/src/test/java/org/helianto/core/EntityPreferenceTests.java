package org.helianto.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.base.AbstractKeyStringValue;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.junit.Test;

/**
 * <code>EntityPreference</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EntityPreferenceTests {
	
	@Test
	public void constructor() {
		EntityPreference entityPreference = new EntityPreference();
		assertTrue(entityPreference instanceof AbstractKeyStringValue);
		assertTrue(entityPreference instanceof Comparable<?>);
	}
	
	@Test
	public void factory() {
		Entity entity = new Entity();
		KeyType keyType = new KeyType();
		EntityPreference entityPreference = EntityPreference.entityPreferenceFactory(entity, keyType);
		assertSame(entity, entityPreference.getEntity());
		assertSame(entity, entityPreference.getKeyOwner());
		assertSame(keyType, entityPreference.getKeyType());
	}
	
	@Test
	public void equalsPreference() {
		EntityPreference entityPreference = new EntityPreference();
		EntityPreference other = new EntityPreference();
		assertTrue(entityPreference.equals(other));
		
		AbstractKeyStringValue otherClass = new AbstractKeyStringValue() {
			private static final long serialVersionUID = 1L;
			@Override protected Object getKeyOwner() { return null; }
		};
		assertFalse(entityPreference.equals(otherClass));
	}

}
