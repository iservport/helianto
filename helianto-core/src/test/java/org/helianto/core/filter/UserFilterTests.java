package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.TrunkEntity;
import org.helianto.core.User;
import org.helianto.core.criteria.CriteriaBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserFilterTests {
	
	private AbstractUserFilterAdapter<Trunk> userFilter;
	private Entity entity;
	private String keyField = "", filterField = "";
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", userFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		keyField = "A";
		assertEquals("alias.entity.id = 1 AND alias.keyField = 'A' ", userFilter.createCriteriaAsString());
	}
	
	@Test
	public void filter() {
		filterField = "A";
		assertEquals("alias.entity.id = 1 AND alias.filterField = 'A' ", userFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		User user = new User(entity);
		Trunk trunk = new Trunk();
		userFilter = new AbstractUserFilterAdapter<Trunk>(trunk, user) {
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
		public Entity getEntity() { return null; }
	}

}
