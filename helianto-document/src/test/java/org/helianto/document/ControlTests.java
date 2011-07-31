package org.helianto.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.base.AbstractPrivateControl;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlTests {

	@SuppressWarnings("serial")
	@Test
	public void equality() {
		Entity entity = EntityTestSupport.createEntity();
		AbstractPrivateControl control = new AbstractPrivateControl() {
			public String getInternalNumberKey() { return "KEY"; }
		};
		assertFalse(control.equals(null));
		
		AbstractPrivateControl other = new AbstractPrivateControl() {
			public String getInternalNumberKey() { return "KEY"; }
		};
		assertTrue(control.equals(other));
		
		control.setInternalNumber(Long.MAX_VALUE);
		assertFalse(control.equals(other));
		control.setEntity(entity);
		assertFalse(control.equals(other));
		other.setInternalNumber(Long.MAX_VALUE);
		assertFalse(control.equals(other));
		other.setEntity(entity);
		assertTrue(control.equals(other));
		assertEquals(control.hashCode(), other.hashCode());
		other.setInternalNumber(Long.MIN_VALUE);
		assertFalse(control.equals(other));
		other.setEntity(new Entity());
		assertFalse(control.equals(other));		
	}
	
}
