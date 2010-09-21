package org.helianto.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ServiceEventTests {
	
	@Test
	public void equality() {
		Service service = new Service(new Operator("DEFAULT"), "SERVICE");
		ServiceEvent serviceEvent = new ServiceEvent();
		ServiceEvent other = new ServiceEvent();
		
		assertTrue(serviceEvent.equals(other));
		serviceEvent.setService(service);
		assertFalse(serviceEvent.equals(other));
		serviceEvent.setEventCode("EVENT");
		assertFalse(serviceEvent.equals(other));
		other.setService(service);
		assertFalse(serviceEvent.equals(other));
		other.setEventCode("EVENT");
		assertTrue(serviceEvent.equals(other));
		assertEquals(serviceEvent.hashCode(), other.hashCode());
		serviceEvent.setService(new Service(new Operator("DEFAULT"), "OTHER"));
		assertFalse(serviceEvent.equals(other));
		serviceEvent.setEventCode("OTHER");
		assertFalse(serviceEvent.equals(other));
	}

}
