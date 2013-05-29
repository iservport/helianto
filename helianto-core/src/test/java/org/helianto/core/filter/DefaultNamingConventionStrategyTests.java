package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.naming.internal.DefaultNamingConventionStrategy;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DefaultNamingConventionStrategyTests {
	
	DefaultNamingConventionStrategy defaultNamingConventionStrategy;
	
	@Test
	public void conventionalName() {
		assertEquals("someClass", defaultNamingConventionStrategy.getConventionalName(SomeClass.class));
	}
	
	@Before
	public void setUp() {
		defaultNamingConventionStrategy = new DefaultNamingConventionStrategy();
	}
	
	class SomeClass { }

}
