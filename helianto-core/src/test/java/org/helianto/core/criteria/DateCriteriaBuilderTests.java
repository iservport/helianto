package org.helianto.core.criteria;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.filter.DateInterval;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mauriciofernandesdecastro
 */
public class DateCriteriaBuilderTests {

	@Test
	public void appendFromRangeNoDate() {
		criteriaBuilder.appendFromDateRange(form);
		assertEquals("", criteriaBuilder.getCriteriaAsString());
	}

	@Test
	public void appendToRangeNoDate() {
		criteriaBuilder.appendToDateRange(form);
		assertEquals("", criteriaBuilder.getCriteriaAsString());
	}

//	@Test
//	public void appendRangeFromDate() {
//		fromDate = new Date(0);
//		criteriaBuilder.appendFromDateRange(form);
//		assertEquals("PREFIX.field >= '1969-12-31 21:00:00' ", criteriaBuilder.getCriteriaAsString());
//	}
//
//	@Test
//	public void appendRangeToDate() {
//		toDate = new Date(0);
//		criteriaBuilder.appendToDateRange(form);
//		assertEquals("PREFIX.field < '1969-12-31 21:00:00' ", criteriaBuilder.getCriteriaAsString());
//	}
//
//	@Test
//	public void appendRangeFromDateToDate() {
//		fromDate = new Date(0);
//		toDate = new Date(1000000);
//		criteriaBuilder.appendFromDateRange(form);
//		criteriaBuilder.appendToDateRange(form);
//		assertEquals(
//				"(PREFIX.field >= '1969-12-31 21:00:00' AND PREFIX.field < '1969-12-31 21:16:40' ) ",
//				criteriaBuilder.getCriteriaAsString());
//	}
//	
	private DateCriteriaBuilder criteriaBuilder;
	private DateInterval form;
    private Date fromDate;
    private Date toDate;

    @Before
    public void setUp() {
        criteriaBuilder = new DateCriteriaBuilder("PREFIX", "field");
		form = new DateIntervalStub();
    }
    
	public class DateIntervalStub implements DateInterval {

		public Date getFromDate() { return fromDate; }
		public Date getToDate() { return toDate; }
		public int getInterval() { return 10; }
    }
}
