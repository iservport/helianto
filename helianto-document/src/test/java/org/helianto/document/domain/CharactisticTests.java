package org.helianto.document.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CharactisticTests {

	@Test
	public void equality() {
		Characteristic characteristic = new Characteristic();
		assertFalse(characteristic.equals(null));
		
		Characteristic other = new Characteristic();
		assertTrue(characteristic.equals(other));
		
		Entity entity = EntityTestSupport.createEntity(1);
		Document document = new Document(entity, "CODE");
		
		characteristic.setDocument(document);
		assertFalse(characteristic.equals(other));
		characteristic.setSequence(1);
		assertFalse(characteristic.equals(other));
		other.setDocument(document);
		assertFalse(characteristic.equals(other));
		other.setSequence(1);
		assertTrue(characteristic.equals(other));
		assertEquals(characteristic.hashCode(), other.hashCode());
	}

}
