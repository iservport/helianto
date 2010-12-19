package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Operator;
import org.helianto.core.RootEntity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class RootFilterTests {
	
	private AbstractRootFilterAdapter<Root> rootFilter;
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
		Root root = new Root();
		rootFilter = new AbstractRootFilterAdapter<Root>(root) {
			public void reset() { }
			@Override
			public boolean isSelection() { return keyField.length()>0; };
			@Override
			protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("keyField", keyField, mainCriteriaBuilder);
			}
			@Override
			protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
				appendEqualFilter("filterField", filterField, mainCriteriaBuilder);
			}
		};
	}
	
	/**
	 * Test class.
	 * 
	 * @author mauriciofernandesdecastro
	 */
	@SuppressWarnings("serial")
	class Root implements RootEntity {
		public Operator getOperator() { return operator; }
	}

}
