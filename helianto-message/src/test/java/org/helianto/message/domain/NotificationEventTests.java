package org.helianto.message.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.message.domain.NotificationEvent;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class NotificationEventTests {
	
	@Test
	public void equality() {
		NotificationEvent notificationEvent = new NotificationEvent();
		assertFalse(notificationEvent.equals(null));

		NotificationEvent other = new NotificationEvent();
		assertTrue(notificationEvent.equals(other));
		
		Entity entity = EntityTestSupport.createEntity();
		
		notificationEvent.setEntity(entity);
		assertFalse(notificationEvent.equals(other));
		notificationEvent.setInternalNumber(Long.MAX_VALUE);
		assertFalse(notificationEvent.equals(other));
		other.setEntity(entity);
		assertFalse(notificationEvent.equals(other));
		other.setInternalNumber(Long.MAX_VALUE);
		assertTrue(notificationEvent.equals(other));
		assertEquals(notificationEvent.hashCode(), other.hashCode());
		
		notificationEvent.setEntity(new Entity(new Operator("DEFAULT"), "OTHER"));
		assertFalse(notificationEvent.equals(other));
		notificationEvent.setInternalNumber(Long.MIN_VALUE);
		assertFalse(notificationEvent.equals(other));
	}

}
