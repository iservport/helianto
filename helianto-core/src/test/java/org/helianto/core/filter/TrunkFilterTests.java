package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.TrunkEntity;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class TrunkFilterTests {
	
	private AbstractTrunkFilterAdapter<Trunk> trunkFilter;
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
		Trunk trunk = new Trunk();
		trunkFilter = new AbstractTrunkFilterAdapter<Trunk>(trunk) {
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
	class Trunk implements TrunkEntity {
		public Entity getEntity() { return entity; }
	}

}
