package org.helianto.web.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * A concrete calendar factory. 
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CalendarFactory extends AbstractCalendarAdapterFactory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final char START_OF_WEEK  = 'W';
	public static final char START_OF_MONTH = 'M';
	public static final char START_OF_YEAR  = 'Y';
	
	/**
	 * Default constructor.
	 */
	public CalendarFactory() {
		super();
	}

	/**
	 * Locale constructor.
	 * 
	 * @param locale
	 */
	public CalendarFactory(Locale locale) {
		super(locale);
	}

	/**
	 * Reset calendar to 00:00:00 000
	 * 
	 * @param calendar
	 */
	protected void resetDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * Reset calendar to Sunday at 00:00:00 000.
	 * 
	 * @param calendar
	 */
	protected void resetWeek(Calendar calendar) {
		resetDay(calendar);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek!=Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, 1 - dayOfWeek);
		}
	}
	
	/**
	 * Reset calendar to first day of month at 00:00:00 000.
	 * 
	 * @param calendar
	 */
	protected void resetMonth(Calendar calendar) {
		resetDay(calendar);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth!=1) {
			calendar.add(Calendar.DATE, 1 - dayOfMonth);
		}
	}
	
	/**
	 * Reset calendar to first day of year at 00:00:00 000.
	 * 
	 * @param calendar
	 */
	protected void resetYear(Calendar calendar) {
		resetDay(calendar);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		if (dayOfYear!=1) {
			calendar.add(Calendar.DATE, 1 - dayOfYear);
		}
	}
	
	public CalendarAdapter calendarAdapterFactory(Date date) {
		Calendar calendar = GregorianCalendar.getInstance(getLocale());
		calendar.setTime(date);
		return calendarAdapterFactory(calendar);
	}
	
	/**
	 * Internal calendar adapter factory method.
	 */
	protected CalendarAdapter calendarAdapterFactory(Calendar calendar) {
		CalendarAdapter adapter = new CalendarAdapter(calendar.getTime());
		adapter.setDayOfMonthAsString(String.format("%1$td", calendar));
		adapter.setWeekOfYearAsString(String.format("%02d", calendar.get(Calendar.WEEK_OF_YEAR)));
		adapter.setMonthAsString(String.format("%1$tm", calendar));
		adapter.setMonthAsBit(String.format("%01d", calendar.get(Calendar.MONTH)%2));
		adapter.setYearAsString(String.format("%1$ty", calendar));
		adapter.setShortDateTimeAsString(DateFormat
		    .getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, getLocale())
		    .format(calendar.getTime()));
		adapter.setShortDateAsString(DateFormat
		    .getDateInstance(DateFormat.SHORT, getLocale())
		    .format(calendar.getTime()));
		adapter.setShortTimeAsString(DateFormat
	        .getTimeInstance(DateFormat.SHORT, getLocale())
	        .format(calendar.getTime()));
		return adapter;
	}

	public List<org.helianto.web.util.CalendarAdapter> calendarAdapterListFactory(Date date, int weeks) {
		return calendarAdapterListFactory(date, weeks, START_OF_WEEK);
	}

	public List<org.helianto.web.util.CalendarAdapter> calendarAdapterListFactory(Date date, int weeks, char type) {
		Calendar calendar = GregorianCalendar.getInstance(getLocale());
		calendar.setTime(date);
		if      (type==START_OF_YEAR)  resetYear(calendar);
		else if (type==START_OF_MONTH) resetMonth(calendar);
		resetWeek(calendar);
		List<CalendarAdapter> calendarAdapterList = new ArrayList<CalendarAdapter>();
		for (int w = 0; w<weeks; w++) {
			for (int d = 1; d<8; d++) {
				calendarAdapterList.add(calendarAdapterFactory(calendar));
				calendar.add(Calendar.DATE, 1);
			}
		}
		return calendarAdapterList;
	}
	
}

