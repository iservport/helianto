package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class RootFilterTests {
	
	private AbstractRootFilterAdapter<AbstractRoot> rootFilter;
	private Operator operator;
	private String keyField = "", filterField = "";
	
	@Test
	public void empty() {
		assertEquals("alias.operator.id = 1 ", rootFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		keyField = "A";
		assertEquals("alias.operator.id = 1 AND alias.keyField = 'A' ", rootFilter.createCriteriaAsString());
	}
	
	@Test
	public void filter() {
		filterField = "A";
		assertEquals("alias.operator.id = 1 AND alias.filterField = 'A' ", rootFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		operator = new Operator("DEFAULT");
		operator.setId(1);
		rootFilter = new AbstractRootFilterAdapter<AbstractRoot>(new AbstractRoot(operator) {}) {
			public void reset() { }
			@Override
			public boolean isSelection() { return keyField.length()>0; };
			@Override
			protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("keyField", keyField, mainCriteriaBuilder);
			}
			@Override
			public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("filterField", filterField, mainCriteriaBuilder);
			}
		};
	}
	
}
