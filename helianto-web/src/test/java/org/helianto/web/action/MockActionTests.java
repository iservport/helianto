package org.helianto.web.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;


/**
 * 
 * @author mauriciofernandesdecastro
 */
public class MockActionTests {
	
	private MockAction<String> mockAction;
	private MutableAttributeMap attributes;
	private PublicUserDetails userDetails;
	
	@Test
	public void constructor() {
		assertTrue(mockAction instanceof AbstractAction<?>);
		assertEquals("TARGET", mockAction.targetName);
		mockAction = new MockAction<String>("TEST");
		assertEquals("TEST", mockAction.targetName);
	}
	
	@Test
	public void create() {
		mockAction.createdTarget = "CREATED";
		mockAction.create(attributes, userDetails);
		assertEquals("CREATED", attributes.get("TARGET"));
		assertSame(attributes, mockAction.receivedAttributes);
		assertSame(userDetails, mockAction.receivedUserDetails);
	}
	
	@Test
	public void prepare() {
		attributes.put("TARGET", "PREPARED");
		mockAction.prepare(attributes);
		assertEquals("PREPARED", mockAction.receivedInPrepare);
	}
	
	@Test
	public void store() {
		attributes.put("TARGET", "STORED");
		mockAction.store(attributes);
		assertEquals("STORED", mockAction.receivedInStore);
	}
	
	@Before
	public void setUp() {
		mockAction = new MockAction<String>();
		attributes = new LocalAttributeMap();
		userDetails = new PublicUserDetails() {
			public void setUser(User user) { }
			public User getUser() { return null; }
			public Entity getEntity() { return null; }
			public Operator getOperator() { return null; }
		};
	}

}
