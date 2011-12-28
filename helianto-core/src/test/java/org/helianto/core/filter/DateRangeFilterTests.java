package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.TrunkEntity;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractDateIntervalFilterAdapter;
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
		dateFilter.setInterval(7);
		assertEquals("alias.entity.id = 1 AND (alias.fieldName >= '1969-12-31 21:00:01' AND alias.fieldName < '1970-01-07 23:59:59' ) ", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void fromZeroInterval() {
		fromDate = new Date(1000l);
		dateFilter.setInterval(0);
		assertEquals("alias.entity.id = 1 AND (alias.fieldName >= '1969-12-31 21:00:01' ) ", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void to() {
		toDate = new Date(2000l);
		assertEquals("alias.entity.id = 1 AND (alias.fieldName >= '1969-12-24 23:59:59' AND alias.fieldName < '1969-12-31 21:00:02' ) ", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void toZeroInterval() {
		toDate = new Date(2000l);
		dateFilter.setInterval(0);
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
			@Override
			public boolean isSelection() { return false; };
			@Override
			protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
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
