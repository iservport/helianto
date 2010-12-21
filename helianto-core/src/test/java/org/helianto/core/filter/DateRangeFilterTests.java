package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.TrunkEntity;
import org.helianto.core.criteria.CriteriaBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DateRangeFilterTests {

	private AbstractDateIntervalFilterAdapter<Trunk> dateFilter;
	private Entity entity;
	private Date fromDate;
	private Date toDate;
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void from() {
		fromDate = new Date(1000l);
		assertEquals("alias.entity.id = 1 AND (alias.fieldName >= '1969-12-31 21:00:01' ) ", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void to() {
		toDate = new Date(2000l);
		assertEquals("alias.entity.id = 1 AND (alias.fieldName < '1969-12-31 21:00:02' ) ", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void fromTo() {
		fromDate = new Date(1000l);
		toDate = new Date(2000l);
		dateFilter.setInterval(0);
		assertEquals("alias.entity.id = 1 AND (alias.fieldName >= '1969-12-31 21:00:01' AND alias.fieldName < '1969-12-31 21:00:02' ) ", dateFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		Trunk trunk = new Trunk();
		dateFilter = new AbstractDateIntervalFilterAdapter<Trunk>(trunk) {
			public String getDateFieldName() { return "fieldName"; };
			public java.util.Date getFromDate() { return fromDate; };
			public java.util.Date getToDate() { return toDate; };
			public void reset() { }
			@Override
			public boolean isSelection() { return false; };
			@Override
			protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }
			@Override
			public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
				super.doFilter(mainCriteriaBuilder);
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
