package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractListFilter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mauriciofernandesdecastro
 */
public class ListFilterTests {
	
	private AbstractListFilter filter;
	List<String> list;
	
	@Test
	public void listEmpty() {
		assertFalse(filter.hasContents());
		assertFalse(filter.hasNext());
		assertFalse(filter.hasPrevious());
		assertEquals(0, filter.getListSize());
		assertEquals(0, filter.getIndex());
	}
	
	@Test
	public void listOne() {
		list.add("ONE");
		assertTrue(filter.hasContents());
		assertEquals(1, filter.getListSize());
		assertEquals(0, filter.getIndex());
		assertFalse(filter.hasNext());
		assertFalse(filter.hasPrevious());
		assertEquals("ONE", filter.getItem());
		assertEquals(0, filter.getIndex());
		assertEquals("ONE", filter.next());
		assertEquals(0, filter.getIndex());
		assertEquals("ONE", filter.previous());
		assertEquals(0, filter.getIndex());
	}
	
	@Test
	public void listTwo() {
		list.add("ONE");
		list.add("TWO");
		assertTrue(filter.hasContents());
		assertEquals(2, filter.getListSize());
		assertEquals(0, filter.getIndex());
		assertTrue(filter.hasNext());
		assertFalse(filter.hasPrevious());
		assertEquals("ONE", filter.getItem());
		assertEquals(0, filter.getIndex());
		assertEquals("TWO", filter.next());
		assertEquals(1, filter.getIndex());
		assertFalse(filter.hasNext());
		assertTrue(filter.hasPrevious());
		assertEquals("ONE", filter.previous());
		assertEquals(0, filter.getIndex());
	}
	
	@Test
	public void listThree() {
		list.add("ONE");
		list.add("TWO");
		list.add("THREE");
		assertTrue(filter.hasContents());
		assertEquals(3, filter.getListSize());
		assertEquals(0, filter.getIndex());
		assertTrue(filter.hasNext());
		assertFalse(filter.hasPrevious());
		assertEquals("ONE", filter.getItem());
		assertEquals(0, filter.getIndex());
		assertEquals("TWO", filter.next());
		assertEquals(1, filter.getIndex());
		assertTrue(filter.hasNext());
		assertTrue(filter.hasPrevious());
		assertEquals("ONE", filter.previous());
		assertEquals(0, filter.getIndex());
		filter.setIndex(1);
		assertEquals("TWO", filter.getItem());
		assertEquals("THREE", filter.next());
		assertEquals(2, filter.getIndex());
		assertFalse(filter.hasNext());
		assertTrue(filter.hasPrevious());
	}
	
	@Test
	public void isClear() {
		list.add("ONE");
		assertEquals("ONE", filter.getItem());
		assertFalse(filter.isClear());
		filter.setIndex(-1);
		assertEquals(-1, filter.getIndex());
		assertTrue(filter.isClear());
		assertNull(filter.getItem());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		list = new ArrayList<String>();
		filter = new AbstractListFilter() {
			public String getObjectAlias() { return "TEST_ALIAS"; }
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
		};
		filter.setList(list);
	}

}
