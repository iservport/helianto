package org.helianto.message.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.AbstractControl;
import org.helianto.message.AbstractFollowUp;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FollowUpFilterAdapterTests {
	
	String C1 = "alias.control.entity.id = 0 ";
	String C2 = "AND alias.notificationOption = 'N' ";
	String C3 = "AND alias.control.id = 1 ";
	
	@Test
	public void empty() {
		assertEquals(C1, filter.createCriteriaAsString());
	}
	
	@Test
	public void notificationOption() {
		target.setNotificationOption('N');
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void parent() {
		@SuppressWarnings("serial")
		AbstractControl parent = new AbstractControl() {
			public String getInternalNumberKey() { return ""; }
		};
		parent.setId(1);
		filter.setParent(parent);
		assertEquals(C1+C3, filter.createCriteriaAsString());
	}
	
	private AbstractFollowUp target;
	private AbstractFollowUpFilterAdapter<AbstractFollowUp> filter;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		final Entity entity = EntityTestSupport.createEntity();
		target = new AbstractFollowUp() {
			public Entity getEntity() { return entity; }
			public String getInternalNumberKey() { return "KEY"; }
		};
		filter = new AbstractFollowUpFilterAdapter<AbstractFollowUp>(target) { };
	}

}
