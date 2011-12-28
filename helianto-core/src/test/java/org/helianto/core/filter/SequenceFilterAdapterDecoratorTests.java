package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractSequence;
import org.helianto.core.filter.base.AbstractSequenceFilterAdapterDecorator;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class SequenceFilterAdapterDecoratorTests {
	
	private Entity entity;
	
	@SuppressWarnings("serial")
	@Test
	public void decorated() {
		AbstractSequenceFilterAdapterDecorator<AbstractSequence> decoratedFilter = 
			new AbstractSequenceFilterAdapterDecorator<AbstractSequence>(new AbstractSequence(entity, 0) {}) {
			@Override public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
				super.doFilter(mainCriteriaBuilder);
				appendEqualFilter("field1", "value1", mainCriteriaBuilder);
			}
		};
		AbstractSequenceFilterAdapterDecorator<AbstractSequence> sequenceFilter = 
			new AbstractSequenceFilterAdapterDecorator<AbstractSequence>(new AbstractSequence(entity, 0) {}, decoratedFilter) {
			@Override public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
				super.doFilter(mainCriteriaBuilder);
				appendEqualFilter("field2", "value2", mainCriteriaBuilder);
			}
		};
		assertEquals("alias.entity.id = 1 AND alias.field1 = 'value1' AND alias.field2 = 'value2' ", sequenceFilter.createCriteriaAsString());
	}
	
	
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
	}
	
}
