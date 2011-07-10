package org.helianto.core;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.ControlStateResolver;
import org.helianto.core.Controllable;
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
		Controllable late = new Controllable() {
			public Date getNextCheckDate() { return new Date(1000L); }
			public Entity getEntity() { return null; }
			public long getInternalNumber() {return 0; }
			public void setInternalNumber(long internalNumber) { }
			public String getInternalNumberKey() { return null; }
			public char getResolution() { return ' '; }
			public int getComplete() { return 0; }
			public void reset() { }
		};
		ControlStateResolver resolver = new ControlStateResolver(late);
		assertTrue(resolver.isLate());
		Controllable notLate = new Controllable() {
			public Date getNextCheckDate() { return new Date(new Date().getTime()+1000000L); }
			public Entity getEntity() { return null; }
			public long getInternalNumber() {return 0; }
			public void setInternalNumber(long internalNumber) { }
			public String getInternalNumberKey() { return null; }
			public char getResolution() { return ' '; }
			public int getComplete() { return 0; }
			public void reset() { }
		};
		resolver = new ControlStateResolver(notLate);
	}
	

}
