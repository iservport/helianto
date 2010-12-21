package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class TrunkFilterTests {
	
	private AbstractTrunkFilterAdapter<AbstractTrunk> trunkFilter;
	private Entity entity;
	private String keyField = "", filterField = "";
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", trunkFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		keyField = "A";
		assertEquals("alias.entity.id = 1 AND alias.keyField = 'A' ", trunkFilter.createCriteriaAsString());
	}
	
	@Test
	public void filter() {
		filterField = "A";
		assertEquals("alias.entity.id = 1 AND alias.filterField = 'A' ", trunkFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		trunkFilter = new AbstractTrunkFilterAdapter<AbstractTrunk>(new AbstractTrunk(entity) {}) {
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
