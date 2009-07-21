package org.helianto.web.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Apply adapter pattern to a Calendar instance to produce a user GUI
 * friendly interface. 
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CalendarAdapter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Locale locale;
	private Calendar calendar;

	/**
	 * Default constructor.
	 */
	public CalendarAdapter() {
		this.locale = Locale.getDefault();
		this.calendar = GregorianCalendar.getInstance();
	}
	
	/**
	 * Locale constructor.
	 * 
	 * @param locale
	 */
	public CalendarAdapter(Locale locale) {
		this.locale = locale;
		this.calendar = GregorianCalendar.getInstance(getLocale());
	}
	
	/**
	 * Date constructor.
	 * 
	 * @param locale
	 * @param date
	 */
	public CalendarAdapter(Locale locale, Date date) {
		this(locale);
		getCalendar().setTime(date);
	}
	
	/**
	 * The internal calendar instance.
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}
	
	/**
	 * The internal locale instance.
	 */
	public Locale getLocale() {
		return this.locale;
	}
	
	/**
	 * Reset a given calendar to 00:00:00 000
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
	 * Reset internal calendar to 00:00:00 000.
	 */
	public CalendarAdapter resetDay() {
		resetDay(getCalendar());
		return this;
	}
	
	/**
	 * Reset internal calendar to Sunday at 00:00:00 000.
	 */
	public CalendarAdapter resetWeek() {
		resetDay(getCalendar());
		int dayOfWeek = ((GregorianCalendar) getCalendar()).get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek!=Calendar.SUNDAY) {
			getCalendar().add(Calendar.DATE, 1 - dayOfWeek);
		}
		return this;
	}
	
	/**
	 * Reset internal calendar to first day of month at 00:00:00 000.
	 */
	public CalendarAdapter resetMonth() {
		resetDay(getCalendar());
		int dayOfMonth = ((GregorianCalendar) getCalendar()).get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth!=1) {
			getCalendar().add(Calendar.DATE, 1 - dayOfMonth);
		}
		return this;
	}
	
	/**
	 * Reset internal calendar to first day of year at 00:00:00 000.
	 */
	public CalendarAdapter resetYear() {
		resetDay(getCalendar());
		int dayOfYear = ((GregorianCalendar) getCalendar()).get(Calendar.DAY_OF_YEAR);
		if (dayOfYear!=1) {
			getCalendar().add(Calendar.DATE, 1 - dayOfYear);
		}
		return this;
	}
	
	/**
	 * Reset internal calendar to first day of year at 00:00:00 000.
	 * 
	 * @param days
	 */
	public CalendarAdapter advance(int days) {
		getCalendar().add(Calendar.DATE, days);
		return this;
	}
	
	/**
	 * Display date.
	 */
	public String getDayOfMonthAsString() {
		return String.format("%1$td", getCalendar());
	}
	
	/**
	 * Display week.
	 */
	public String getWeekOfYearAsString() {
		return String.format("%02d", this.getCalendar().get(Calendar.WEEK_OF_YEAR));
	}
	
	/**
	 * Display month.
	 */
	public String getMonthAsString() {
		return String.format("%1$tm", getCalendar());
	}
	
	/**
	 * Display "0" or "1" for even or odd months.
	 */
	public String getMonthAsBit() {
		return String.valueOf(this.getCalendar().get(Calendar.MONTH)%2);
	}
	
	/**
	 * Display year.
	 */
	public String getYearAsString() {
		return String.format("%1$ty", getCalendar());
	}
	
	/**
	 * Display short date and time.
	 */
	public String getShortDateTimeAsString() {
		return DateFormat
		    .getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, getLocale())
		    .format(getCalendar().getTime());
	}
	
	/**
	 * Display short date.
	 */
	public String getShortDateAsString() {
		return DateFormat
		    .getDateInstance(DateFormat.SHORT, getLocale())
		    .format(getCalendar().getTime());
	}
	
	/**
	 * Display short time.
	 */
	public String getShortTimeAsString() {
		return DateFormat
	        .getTimeInstance(DateFormat.SHORT, getLocale())
	        .format(getCalendar().getTime());
	}
	
	@Override
	public boolean equals(Object other) {
		return this.getCalendar().equals(((CalendarAdapter) other).getCalendar());
	}

	@Override
	public int hashCode() {
		return getCalendar().hashCode();
	}
	

}

