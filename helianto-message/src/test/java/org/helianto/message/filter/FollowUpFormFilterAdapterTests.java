package org.helianto.message.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.base.AbstractPrivateControl;
import org.helianto.message.AbstractFollowUp;
import org.helianto.message.ControlSource;
import org.helianto.message.form.AbstractFollowUpForm;
import org.helianto.message.form.FollowUpForm;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FollowUpFormFilterAdapterTests {
	
	String C1 = "alias.control.id = 10 ";
	String C2 = "AND alias.notificationOption = 'N' ";
	String C3 = "AND alias.decision = 'F' ";
	
	@Test
	public void empty() {
		assertEquals(C1, filter.createCriteriaAsString());
	}
	
	@Test
	public void notificationOption() {
		((AbstractFollowUp) form).setNotificationOption('N');
		assertEquals(C1+C2, filter.createCriteriaAsString());
	}
		
	@Test
	public void decision() {
		((AbstractFollowUp) form).setDecision('F');
		assertEquals(C1+C3, filter.createCriteriaAsString());
	}
		
	private AbstractFollowUpFormFilterAdapter<FollowUpFormStub, ControlSourceStub> filter;
	private FollowUpForm<ControlSourceStub> form;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity(1);
		ControlSourceStub controlSource = new ControlSourceStub() { };
		form = new FollowUpFormStub(controlSource);
		((FollowUpFormStub) form).setEntity(entity);
		filter = new AbstractFollowUpFormFilterAdapter<FollowUpFormStub, ControlSourceStub>((FollowUpFormStub) form) { };
		((AbstractFollowUp) form).setNotificationOption(' ');
		form.reset();
	}
	
	@SuppressWarnings("serial")
	private class FollowUpFormStub extends AbstractFollowUpForm<ControlSourceStub>{
				
		public FollowUpFormStub(ControlSourceStub controlSource) {
			super(controlSource);
		}
		
		public String getControlName() { return "control"; }
		public long getControlId() { return 10; }
	}
	
	@SuppressWarnings("serial")
	private class ControlSourceStub extends AbstractPrivateControl implements ControlSource { }

}
