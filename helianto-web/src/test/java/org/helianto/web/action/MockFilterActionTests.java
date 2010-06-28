package org.helianto.web.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractListFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;


/**
 * 
 * @author mauriciofernandesdecastro
 */
public class MockFilterActionTests {
	
	private MockFilterAction<String> mockFilterAction;
	private MutableAttributeMap attributes;
	private PublicUserDetails userDetails;
	private ListFilter createdFilter;
	private List<String> targetList;
	
	@Test
	public void constructor() {
		assertTrue(mockFilterAction instanceof AbstractFilterAction<?>);
		assertEquals("TARGET", mockFilterAction.targetName);
		mockFilterAction = new MockFilterAction<String>("TEST");
		assertEquals("TEST", mockFilterAction.targetName);
	}
	
	@Test
	public void create() {
		mockFilterAction.createdTarget = "CREATED";
		mockFilterAction.create(attributes, userDetails);
		assertEquals("CREATED", attributes.get("TARGET"));
		assertSame(attributes, mockFilterAction.receivedAttributes);
		assertSame(userDetails, mockFilterAction.receivedUserDetails);
	}
	
	@Test
	public void filter() {
		mockFilterAction.createdFilter = createdFilter;
		mockFilterAction.targetList = targetList;
		mockFilterAction.filter(attributes, userDetails);
		assertEquals(createdFilter, attributes.get("TARGETFilter"));
		assertSame(attributes, mockFilterAction.receivedAttributes);
		assertSame(userDetails, mockFilterAction.receivedUserDetails);
		assertSame(targetList, createdFilter.getList());
	}
	
	@Test
	public void prepare() {
		attributes.put("TARGET", "PREPARED");
		mockFilterAction.prepare(attributes);
		assertEquals("PREPARED", mockFilterAction.receivedInPrepare);
	}
	
	@Test
	public void store() {
		attributes.put("TARGET", "STORED");
		mockFilterAction.store(attributes);
		assertEquals("STORED", mockFilterAction.receivedInStore);
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		mockFilterAction = new MockFilterAction<String>();
		attributes = new LocalAttributeMap();
		userDetails = new PublicUserDetails() {
			public void setUser(User user) { }
			public User getUser() { return null; }
		};
		createdFilter = new AbstractListFilter() {
			@Override protected void doFilter(CriteriaBuilder mainCriteriaBuilder) { }
			@Override protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }
			public String getObjectAlias() { return "ALIAS"; }
			public void reset() { }
		};
		targetList = new ArrayList<String>();
	}

}
