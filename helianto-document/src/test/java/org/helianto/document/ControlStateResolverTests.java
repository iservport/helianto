package org.helianto.document;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.Entity;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlStateResolverTests {
	
	@SuppressWarnings("serial")
	@Test
	public void control() {
		Controlable late = new Controlable() {
			public Date getNextCheckDate() { return new Date(1000L); }
			public Entity getEntity() { return null; }
			public long getInternalNumber() {return 0; }
			public void setInternalNumber(long internalNumber) { }
			public String getInternalNumberKey() { return null; }
		};
		ControlStateResolver resolver = new ControlStateResolver(late);
		assertTrue(resolver.isLate());
		Controlable notLate = new Controlable() {
			public Date getNextCheckDate() { return new Date(new Date().getTime()+1000000L); }
			public Entity getEntity() { return null; }
			public long getInternalNumber() {return 0; }
			public void setInternalNumber(long internalNumber) { }
			public String getInternalNumberKey() { return null; }
		};
		resolver = new ControlStateResolver(notLate);
	}
	

}
