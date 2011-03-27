package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.filter.base.AbstractSequence;
import org.helianto.core.filter.base.AbstractSequenceFilterAdapterDecorator;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SequenceFilterAdapterTests {
	
	private AbstractSequenceFilterAdapterDecorator<AbstractSequence> sequenceFilter;
	private Entity entity;
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", sequenceFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		sequenceFilter.getFilter().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", sequenceFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		sequenceFilter = new AbstractSequenceFilterAdapterDecorator<AbstractSequence>(new AbstractSequence(entity, 0) {}) {
			public void reset() { }
		};
	}
	
}
