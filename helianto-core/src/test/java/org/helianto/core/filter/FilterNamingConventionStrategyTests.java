package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterNamingConventionStrategyTests {
	
	FilterNamingConventionStrategy filterObjectAliasStrategy;
	
	@Test
	public void objectAlias() {
		SomeClassFilter filter = new SomeClassFilter();
		assertEquals("someClass", filterObjectAliasStrategy.getObjectName(filter));
	}
	
	@Before
	public void setUp() {
		filterObjectAliasStrategy = new FilterNamingConventionStrategy();
	}
	
	class SomeClassFilter implements Filter {
		public String createCriteriaAsString() { return null; }
		public String getObjectAlias() { return null; }
		public boolean isSelection() { return false; }
		public void reset() { }
		
	}

}
