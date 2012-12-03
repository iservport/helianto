package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.TrunkEntity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractUserFilterAdapter;
import org.helianto.user.domain.User;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserBackedFilterTests {
	
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
		User user = new User(entity, new Identity("PRINCIPAL"));
		Trunk trunk = new Trunk();
		userFilter = new AbstractUserFilterAdapter<Trunk>(trunk, user) {
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
