package org.helianto.core.filter.classic;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.classic.AbstractDateRangeFilter;
import org.helianto.core.filter.classic.DateFilterMode;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractDateRangeFilterTests {
	
	private AbstractDateRangeFilter filter;
	
	@Test
	public void updateRangePast() {
		long toDateInMilis = 60*1000*86400L;
		filter.setToDate(new Date(toDateInMilis));
//		System.out.println(filter.getToDate());
		filter.setInterval(-30);
		filter.setDateFilterMode(DateFilterMode.TO_DATE_MINUS_INTERVAL);
		long fromDateInMilis = 30*1000*86400L;
		// we chose a start date with time set to 21:00, but updateRangePast
		// will normalize the time to 23:59:59 before calculations
		// from 21:00 to 23:59:59 we need more 3*1000*3600-1000 milis
//		System.out.println(new Date(fromDateInMilis+3*1000*3600-1000));
		filter.doFilter(new CriteriaBuilder());
//		System.out.println(filter.getFromDate());
		assertEquals(fromDateInMilis+3*1000*3600-1000, filter.getFromDate().getTime());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateRangePastError() {
		filter.setInterval(30);
		filter.setIntervalIntegrityEnforced(true);
		filter.setDateFilterMode(DateFilterMode.TO_DATE_MINUS_INTERVAL);
		filter.doFilter(new CriteriaBuilder());
	}
	
	@Test
	public void updateRangeFuture() {
		long fromDateInMilis = 60*1000*86400L;
		filter.setFromDate(new Date(fromDateInMilis));
//		System.out.println(filter.getFromDate());
		filter.setInterval(+30);
		filter.setDateFilterMode(DateFilterMode.FROM_DATE_PLUS_INTERVAL);
		long toDateInMilis = 90*1000*86400L;
		// we chose a start date with time set to 21:00, but updateRangePast
		// will normalize the time to 23:59:59 before calculations
		// from 21:00 to 23:59:59 we need more 3*1000*3600-1000 milis
//		System.out.println(new Date(toDateInMilis+3*1000*3600-1000));
//		System.out.println(filter.getToDate());
		filter.doFilter(new CriteriaBuilder());
		assertEquals(toDateInMilis+3*1000*3600-1000, filter.getToDate().getTime());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateRangeFutureError() {
		filter.setInterval(-30);
		filter.setIntervalIntegrityEnforced(true);
		filter.setDateFilterMode(DateFilterMode.FROM_DATE_PLUS_INTERVAL);
		filter.doFilter(new CriteriaBuilder());
	}
	
	@Before
	@SuppressWarnings("serial")
	public void setUp() {
		filter = new AbstractDateRangeFilter() {
			@Override protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }
		};
		filter.setDateFieldName("test");
	}

}
