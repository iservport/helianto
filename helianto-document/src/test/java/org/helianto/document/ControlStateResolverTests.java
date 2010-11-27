package org.helianto.document;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ControlStateResolverTests {
	
	@Test
	public void control() {
		Controlable late = new Controlable() {
			public Date getNextCheckDate() { return new Date(1000L); }
		};
		ControlStateResolver resolver = new ControlStateResolver(late);
		assertTrue(resolver.isLate());
		Controlable notLate = new Controlable() {
			public Date getNextCheckDate() { return new Date(new Date().getTime()+1000000L); }
		};
		resolver = new ControlStateResolver(notLate);
	}
	

}
