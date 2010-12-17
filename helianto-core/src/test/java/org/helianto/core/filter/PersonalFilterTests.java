package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Identity;
import org.helianto.core.PersonalEntity;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PersonalFilterTests {
	
	private AbstractPersonalFilterAdapter<Person> personFilter;
	private Identity identity;
	private String keyField = "", filterField = "";
	
	@Test
	public void empty() {
		assertEquals("alias.identity.id = 1 ", personFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		keyField = "A";
		assertEquals("alias.identity.id = 1 AND alias.keyField = 'A' ", personFilter.createCriteriaAsString());
	}
	
	@Test
	public void filter() {
		filterField = "A";
		assertEquals("alias.identity.id = 1 AND alias.filterField = 'A' ", personFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		identity = new Identity("IDENTITY");
		identity.setId(1);
		Person person = new Person();
		personFilter = new AbstractPersonalFilterAdapter<Person>(person) {
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
	class Person implements PersonalEntity {
		public Identity getIdentity() { return identity; }
	}

}
