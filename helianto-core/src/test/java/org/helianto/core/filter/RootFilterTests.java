package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Operator;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRoot;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;
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
			protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("keyField", keyField, mainCriteriaBuilder);
			}
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("filterField", filterField, mainCriteriaBuilder);
			}
		};
	}
	
}
