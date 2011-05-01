package org.helianto.web.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.base.AbstractListFilter;
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
	private Filter createdFilter;
	private SimpleModel<Object> createdModel;
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
		mockFilterAction.createdModel = createdModel;
		mockFilterAction.targetList = targetList;
		mockFilterAction.filter(attributes, userDetails);
		assertEquals(createdFilter, attributes.get("TARGETFilter"));
		assertSame(attributes, mockFilterAction.receivedAttributes);
		assertSame(userDetails, mockFilterAction.receivedUserDetails);
		assertSame(targetList, createdModel.getList());
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
			@SuppressWarnings("unused")
			public void setUser(User user) { }
			public User getUser() { return null; }
			public Entity getEntity() { return null; }
			public Operator getOperator() { return null; }
		};
		createdFilter = new AbstractListFilter() {
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			public String getObjectAlias() { return "ALIAS"; }
			public void reset() { }
		};
		createdModel = new SimpleModel<Object>(createdFilter, userDetails.getUser());
		targetList = new ArrayList<String>();
	}

}
