package org.helianto.message.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.message.form.NotificationEventForm;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class NotificationEventFormFilterAdapterTests {
	
	private static final String O0 = "order by alias.issueDate DESC ";
	private static final String C1 = "alias.entity.id = 1 ";
	private static final String C2 = "alias.internalNumber = 9 ";
	private static final String C3 = "alias.issueDate >= '1969-12-31 21:00:01' ";
	

	@Test
	public void empty() {
		assertEquals(C1+O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void key() {
		Mockito.when(form.getInternalNumber()).thenReturn(9L);
		assertEquals(C1+"AND "+C2, filter.createCriteriaAsString());
	}
	
	@Test
	public void issueDate() {
		Mockito.when(form.getDateFieldName()).thenReturn("issueDate");
		Mockito.when(form.getFromDate()).thenReturn(new DateTime(1000L).toDate());
		assertEquals(C1+"AND "+C3+O0, filter.createCriteriaAsString());
	}
	
	// locals
	
	private NotificationEventFormFilterAdapter filter;
	private NotificationEventForm form;
	
	@Before
	public void setUp() {
		form = Mockito.mock(NotificationEventForm.class);
		filter = new NotificationEventFormFilterAdapter(form);
		Mockito.when(form.getEntityId()).thenReturn(1);
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}
	
}
