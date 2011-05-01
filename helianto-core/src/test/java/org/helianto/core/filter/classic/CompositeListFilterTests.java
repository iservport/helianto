package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.filter.base.AbstractListFilter;
import org.helianto.core.filter.classic.AbstractCompositeListFilter;
import org.helianto.core.filter.classic.CompositeListFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mauriciofernandesdecastro
 */
public class CompositeListFilterTests {
	
	private AbstractCompositeListFilter filter;
	List<String> list;
	
	@Test
	public void inheritance() {
		assertTrue(filter instanceof AbstractCompositeListFilter);
		assertTrue(filter instanceof CompositeListFilter);
	}
	
	@Test
	public void listEmpty() {
		assertEquals(0, filter.getParentListSize());
		assertEquals(0, filter.getParentIndex());
	}
	
	@Test
	public void listOne() {
		list.add("ONE");
		assertEquals(1, filter.getParentListSize());
		assertEquals(0, filter.getParentIndex());
		assertEquals("ONE", filter.getParentItem());
		assertEquals(0, filter.getParentIndex());
		assertEquals("ONE", filter.nextParent());
		assertEquals(0, filter.getParentIndex());
		assertEquals("ONE", filter.previousParent());
		assertEquals(0, filter.getParentIndex());
	}
	
	@Test
	public void listTwo() {
		list.add("ONE");
		list.add("TWO");
		assertEquals(2, filter.getParentListSize());
		assertEquals(0, filter.getParentIndex());
		assertEquals("ONE", filter.getParentItem());
		assertEquals(0, filter.getParentIndex());
		assertEquals("TWO", filter.nextParent());
		assertEquals(1, filter.getParentIndex());
		assertEquals("ONE", filter.previousParent());
		assertEquals(0, filter.getParentIndex());
	}
	
	@Test
	public void listThree() {
		list.add("ONE");
		list.add("TWO");
		list.add("THREE");
		assertEquals(3, filter.getParentListSize());
		assertEquals(0, filter.getParentIndex());
		assertEquals("ONE", filter.getParentItem());
		assertEquals(0, filter.getParentIndex());
		assertEquals("TWO", filter.nextParent());
		assertEquals(1, filter.getParentIndex());
		assertEquals("ONE", filter.previousParent());
		assertEquals(0, filter.getParentIndex());
		filter.setParentIndex(1);
		assertEquals("TWO", filter.getParentItem());
		assertEquals("THREE", filter.nextParent());
		assertEquals(2, filter.getParentIndex());
	}
	
	@Test
	public void isClear() {
		list.add("ONE");
		assertEquals("ONE", filter.getParentItem());
		assertFalse(filter.isParentClear());
		filter.setParentIndex(-1);
		assertEquals(-1, filter.getParentIndex());
		assertTrue(filter.isParentClear());
		assertNull(filter.getParentItem());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		list = new ArrayList<String>();
		filter = new AbstractCompositeListFilter() {
			public String getObjectAlias() { return "TEST_ALIAS"; }
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
			public void reset() { }
		};
		ListFilter parentFilter = new AbstractListFilter() {
			public String getObjectAlias() { return "PARENT_ALIAS"; }
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
			public void reset() { }
		};
		filter.setParentFilter(parentFilter);
		filter.getParentFilter().setList(list);
	}

}
