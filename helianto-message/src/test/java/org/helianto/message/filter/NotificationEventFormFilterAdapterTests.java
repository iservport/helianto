package org.helianto.message.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.document.base.AbstractEvent;
import org.helianto.document.base.AbstractRecord;
import org.helianto.message.form.NotificationEventForm;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class NotificationEventFormFilterAdapterTests {
	
	private static final String O0 = "order by alias.issueDate DESC ";
	private static final String C1 = "alias.entity.id = 1 ";
	private static final String C2 = "alias.internalNumber = 9 ";
	private static final String C3 = "(alias.issueDate >= '1969-12-24 23:59:59' " +
			"AND alias.issueDate < '1969-12-31 21:00:01' ) ";
	

	@Test
	public void empty() {
		assertEquals(C1+O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		form.setInternalNumber(9);
		assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void issueDate() {
		((AbstractEvent) form).setIssueDate(new DateTime(1000L, DateTimeZone.UTC).toDate());
		assertEquals(C1+"AND "+C3+O0, filter.createCriteriaAsString());
	}
	
	// locals
	
	private NotificationEventFormFilterAdapter filter;
	private NotificationEventForm form;
	
	@Before
	public void setUp() {
		Entity entity = EntityTestSupport.createEntity(1);
		form = new NotificationEventFormStub();
		((AbstractEvent) form).setEntity(entity);
		filter = new NotificationEventFormFilterAdapter(form);
	}
	
	/**
	 * Test stub.
	 * 
	 * @author mauriciofernandesdecastro
	 */
	private class NotificationEventFormStub extends AbstractRecord implements NotificationEventForm {

		private static final long serialVersionUID = 1L;
		
		public NotificationEventFormStub() {
			super();
			setIssueDate(null);
		}

	};


}
