package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.number.Sequenceable;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SequenceFilterTests {
	
	private AbstractSequenceFilterAdapter<Sequence> trunkFilter;
	private Entity entity;
	private long internalNumber;
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", trunkFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		internalNumber = 1;
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", trunkFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		Sequence sequence = new Sequence();
		trunkFilter = new AbstractSequenceFilterAdapter<Sequence>(sequence) {
			public void reset() { }
			@Override
			protected void doFilter(CriteriaBuilder mainCriteriaBuilder) { }
		};
	}
	
	/**
	 * Test class.
	 * 
	 * @author mauriciofernandesdecastro
	 */
	@SuppressWarnings("serial")
	class Sequence implements Sequenceable {
		public Entity getEntity() { return entity; }
		public long getInternalNumber() { return internalNumber; }
		public void setInternalNumber(long internalNumber) { }
		public String getInternalNumberKey() { return "KEY"; }
	}

}
