package org.helianto.web.util;

import java.io.Serializable;
import java.util.Date;

/**
 * Apply adapter pattern to a Calendar instance to produce a user GUI
 * friendly interface. 
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CalendarAdapter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Date date;
	private String dayOfMonthAsString = "01";
	private String weekOfYearAsString = "01";
	private String monthAsString = "01";
	private String monthAsBit = "0";
	private String yearAsString = "00";
	private String shortDateTimeAsString = "01/01/00 00:00:00";
	private String shortDateAsString = "01/01/00";
	private String shortTimeAsString = "00:00:00";

	/**
	 * Default constructor.
	 */
	public CalendarAdapter() {
		this(new Date());
	}
	
	/**
	 * Date constructor.
	 */
	public CalendarAdapter(Date date) {
		this.date = date;
	}
	
	/**
	 * Internal date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Display date.
	 */
	public String getDayOfMonthAsString() {
		return this.dayOfMonthAsString;
	}
	public void setDayOfMonthAsString(String dayOfMonthAsString) {
		this.dayOfMonthAsString = dayOfMonthAsString;
	}
	
	/**
	 * Display week.
	 */
	public String getWeekOfYearAsString() {
		return this.weekOfYearAsString;
	}
	public void setWeekOfYearAsString(String weekOfYearAsString) {
		this.weekOfYearAsString = weekOfYearAsString;
	}

	/**
	 * Display month.
	 */
	public String getMonthAsString() {
		return this.monthAsString;
	}
	public void setMonthAsString(String monthAsString) {
		this.monthAsString = monthAsString;
	}

	/**
	 * Display "0" or "1" for even or odd months.
	 */
	public String getMonthAsBit() {
		return this.monthAsBit;
	}
	public void setMonthAsBit(String monthAsBit) {
		this.monthAsBit = monthAsBit;
	}

	/**
	 * Display year.
	 */
	public String getYearAsString() {
		return this.yearAsString;
	}
	public void setYearAsString(String yearAsString) {
		this.yearAsString = yearAsString;
	}

	/**
	 * Display short date and time.
	 */
	public String getShortDateTimeAsString() {
		return this.shortDateTimeAsString;
	}
	public void setShortDateTimeAsString(String shortDateTimeAsString) {
		this.shortDateTimeAsString = shortDateTimeAsString;
	}

	/**
	 * Display short date.
	 */
	public String getShortDateAsString() {
		return this.shortDateAsString;
	}
	public void setShortDateAsString(String shortDateAsString) {
		this.shortDateAsString = shortDateAsString;
	}

	/**
	 * Display short time.
	 */
	public String getShortTimeAsString() {
		return this.shortTimeAsString;
	}	
	public void setShortTimeAsString(String shortTimeAsString) {
		this.shortTimeAsString = shortTimeAsString;
	}

	@Override
	public boolean equals(Object other) {
		if (other!=null && other instanceof CalendarAdapter) {
			return this.getDate().equals(((CalendarAdapter) other).getDate());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getDate().hashCode();
	}
	

}

