package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractSequence;
import org.helianto.core.filter.classic.AbstractSequenceFilterAdapterDecorator;
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
		sequenceFilter.getForm().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", sequenceFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		sequenceFilter = new AbstractSequenceFilterAdapterDecorator<AbstractSequence>(new AbstractSequence(entity, 0) {}) {
		};
	}
	
}
