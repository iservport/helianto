package org.helianto.partner.domain;

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
public class SegmentAliasTests {

	@Test
	public void equalTest() {
		PrivateSegment segment = new PrivateSegment();
		assertFalse(segment.equals(null));
		
		PrivateSegment other = new PrivateSegment();
		assertTrue(segment.equals(other));
		
		Entity entity = EntityTestSupport.createEntity();
		segment.setEntity(entity);
		assertFalse(segment.equals(other));
		segment.setSegmentAlias("ALIAS");
		assertFalse(segment.equals(other));
		other.setEntity(entity);
		assertFalse(segment.equals(other));
		other.setSegmentAlias("ALIAS");
		assertTrue(segment.equals(other));
		assertEquals(segment.hashCode(), other.hashCode());
	}

}
