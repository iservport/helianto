/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.web.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.helianto.web.util.CalendarAdapter;
import org.helianto.web.util.CalendarFactory;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CalendarFactoryTests {
	
	@Test
	public void resetDay() {
		calendarFactory.resetDay(calendar);
		assertEquals(3380400000L, calendar.getTimeInMillis());
	}
	
	@Test
	public void resetWeek() {
		calendarFactory.resetWeek(calendar);
		assertEquals(3294000000L, calendar.getTimeInMillis());
		// must not change if called twice
		calendarFactory.resetWeek(calendar);
		assertEquals(3294000000L, calendar.getTimeInMillis());
	}
	
	@Test
	public void resetMonth() {
		calendarFactory.resetMonth(calendar);
		assertEquals(2689200000L, calendar.getTimeInMillis());
		// must not change if called twice
		calendarFactory.resetMonth(calendar);
		assertEquals(2689200000L, calendar.getTimeInMillis());
	}
	
	@Test
	public void resetYear() {
		calendarFactory.resetYear(calendar);
		assertEquals(10800000L, calendar.getTimeInMillis());
		// must not change if called twice
		calendarFactory.resetYear(calendar);
		assertEquals(10800000L, calendar.getTimeInMillis());
	}
	
	@Test
	public void display() {
		CalendarAdapter adapter = calendarFactory.calendarAdapterFactory(calendar);
		assertEquals(calendar.getTime(), adapter.getDate());
		assertEquals("09", adapter.getDayOfMonthAsString());
		assertEquals("07", adapter.getWeekOfYearAsString());
		assertEquals("02", adapter.getMonthAsString());
		assertEquals("Feb", adapter.getMonthAsShortName());
		assertEquals("1", adapter.getMonthAsBit());
		assertEquals("70", adapter.getYearAsString());
		assertEquals("2/9/70", adapter.getShortDateAsString());
		assertEquals("10:01 PM", adapter.getShortTimeAsString());
		assertEquals("2/9/70 10:01 PM", adapter.getShortDateTimeAsString());
	}

	@Test
	public void calendarList() {
		Date date = new Date(1000L);
		List<CalendarAdapter> calendarList = calendarFactory
		    .calendarAdapterListFactory(date, 2);
		assertEquals(14, calendarList.size());
		assertEquals(-334800000L, calendarList.get(0).getDate().getTime());
		assertEquals(788400000L, calendarList.get(13).getDate().getTime());
	}
	
	@Test
	public void calendarListMonth() {
		Date date = new Date(1000L);
		List<CalendarAdapter> calendarList = calendarFactory
		    .setStart(CalendarFactory.START_OF_MONTH)
		    .calendarAdapterListFactory(date, 2);
		assertEquals(14, calendarList.size());
		assertEquals(-2754000000L, calendarList.get(0).getDate().getTime());
		assertEquals(-1630800000L, calendarList.get(13).getDate().getTime());
	}
	
	@Test
	public void calendarListYear() {
		Date date = new Date(1000L);
		List<CalendarAdapter> calendarList = calendarFactory
		    .setStart(CalendarFactory.START_OF_YEAR)
		    .calendarAdapterListFactory(date, 2);
		assertEquals(14, calendarList.size());
		assertEquals(-31784400000L, calendarList.get(0).getDate().getTime());
		assertEquals(-30661200000L, calendarList.get(13).getDate().getTime());
	}
	
	@Test
	public void calendarListSemester() {
		Date date = new Date(1000L);
		List<CalendarAdapter> calendarList = calendarFactory
		    .setStart(CalendarFactory.START_OF_YEAR)
		    .setResetWeekBeforeStart(false)
		    .setGroupSize(CalendarFactory.MONTH_GROUP_SIZE)
		    .calendarAdapterListFactory(date, 1);
		assertEquals(6, calendarList.size());
		assertEquals(-31525200000L, calendarList.get(0).getDate().getTime());
		assertEquals(-18478800000L, calendarList.get(5).getDate().getTime());
	}
	
	private CalendarFactory calendarFactory;
	private Calendar calendar;
	
	@Before
	public void setUp() {
		calendarFactory = new CalendarFactory(Locale.US);
		calendar = Calendar.getInstance();
		calendar.setTime(new Date(40L*86400000L+3600000L+61001L)); //1 month 1 week 2 days 1 hour 1 min 1 sec 1 mils
	}

}
