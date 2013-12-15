package org.helianto.core.filter.internal;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.DateForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class DateFilterAdapterTests {

    public static String ORDER = "order by alias.fieldName ";
    public static String C1 = "alias.fieldName >= '1969-12-31 21:00:01' ";
    public static String C2 = "alias.fieldName < '1969-12-31 21:00:02' ";

	@Test
	public void empty() {
		assertEquals(ORDER, dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void fieldName() {
		Mockito.when(form.getDateFieldName()).thenReturn("");
		Mockito.when(form.getFromDate()).thenReturn(new Date(1000l));
		assertEquals("", dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void from() {
		Mockito.when(form.getFromDate()).thenReturn(new Date(1000l));
		assertEquals(C1+ORDER, dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void to() {
		Mockito.when(form.getToDate()).thenReturn(new Date(2000l));
		assertEquals(C2+ORDER, dateFilter.createCriteriaAsString());
	}
	
	@Test
	public void fromTo() {
		Mockito.when(form.getFromDate()).thenReturn(new Date(1000l));
		Mockito.when(form.getToDate()).thenReturn(new Date(2000l));
		assertEquals(C1+"AND "+C2+ORDER, dateFilter.createCriteriaAsString());
	}
	
	private AbstractDateFilterAdapter<DateForm> dateFilter;
	private DateForm form;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		form = Mockito.mock(DateForm.class);
		dateFilter = new AbstractDateFilterAdapter<DateForm>(form) {
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
		};
		Mockito.when(form.getDateFieldName()).thenReturn("fieldName");
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
