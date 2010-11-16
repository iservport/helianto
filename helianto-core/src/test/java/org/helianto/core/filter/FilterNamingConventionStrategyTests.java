package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterNamingConventionStrategyTests {
	
	FilterNamingConventionStrategy filterNamingConventionStrategy;
	
	@Test
	public void conventionalName() {
		assertEquals("someClass", filterNamingConventionStrategy.getConventionalName(SomeClassFilter.class));
	}
	
	@Before
	public void setUp() {
		filterNamingConventionStrategy = new FilterNamingConventionStrategy();
	}
	
	class SomeClassFilter implements Filter {
		public String createCriteriaAsString() { return null; }
		public String getObjectAlias() { return null; }
		public boolean isSelection() { return false; }
		public void reset() { }
		public void setObjectAlias(String objectAlias) { }
	}

}
