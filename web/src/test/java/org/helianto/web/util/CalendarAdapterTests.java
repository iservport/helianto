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
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.helianto.web.util.CalendarAdapter;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CalendarAdapterTests {
	
	@Test
	public void testConstructor() {
		adapter = new  CalendarAdapter();
		assertTrue(adapter instanceof Serializable);
		assertTrue(adapter.getLocale() instanceof Locale);
		assertTrue(adapter.getCalendar() instanceof Calendar);
	}
	
	@Test
	public void testConstructorLocale() {
		adapter = new  CalendarAdapter(Locale.CANADA);
		assertEquals(Locale.CANADA, adapter.getLocale());
	}

	@Test
	public void testConstructorDate() {
		adapter = new  CalendarAdapter(Locale.CANADA, calendar.getTime());
		assertEquals(Locale.CANADA, adapter.getLocale());
		assertEquals(3459661001L, adapter.getCalendar().getTimeInMillis());
		assertEquals("09/02/70 10:01 PM", adapter.getShortDateTimeAsString());
	}

	@Test
	public void testResetDay() {
		assertEquals(3380400000L, adapter.resetDay().getCalendar().getTimeInMillis());
		assertEquals("2/9/70 12:00 AM", adapter.getShortDateTimeAsString());
	}
	
	@Test
	public void testResetWeek() {
		assertEquals(3294000000L, adapter.resetWeek().getCalendar().getTimeInMillis());
		// must not change if called twice
		assertEquals(3294000000L, adapter.resetWeek().getCalendar().getTimeInMillis());
		assertEquals("2/8/70 12:00 AM", adapter.getShortDateTimeAsString());
	}
	
	@Test
	public void testResetMonth() {
		assertEquals(2689200000L, adapter.resetMonth().getCalendar().getTimeInMillis());
		// must not change if called twice
		assertEquals(2689200000L, adapter.resetMonth().getCalendar().getTimeInMillis());
		assertEquals("2/1/70 12:00 AM", adapter.getShortDateTimeAsString());
	}
	
	@Test
	public void testResetYear() {
		assertEquals(10800000L, adapter.resetYear().getCalendar().getTimeInMillis());
		// must not change if called twice
		assertEquals(10800000L, adapter.resetYear().getCalendar().getTimeInMillis());
		assertEquals("1/1/70 12:00 AM", adapter.getShortDateTimeAsString());
	}
	
	@Test
	public void testAdvance() {
		assertEquals(3546061001L, adapter.advance(1).getCalendar().getTimeInMillis());
		assertEquals("2/10/70 10:01 PM", adapter.getShortDateTimeAsString());
	}

	@Test
	public void testDisplay() {
		assertEquals("09", adapter.getDayOfMonthAsString());
		assertEquals("07", adapter.getWeekOfYearAsString());
		assertEquals("02", adapter.getMonthAsString());
		assertEquals("1", adapter.getMonthAsBit());
		assertEquals("70", adapter.getYearAsString());
		assertEquals("2/9/70", adapter.getShortDateAsString());
		assertEquals("10:01 PM", adapter.getShortTimeAsString());
	}

	private CalendarAdapter adapter;
	private Calendar calendar;
	
	@Before
	public void setUp() {
		calendar = Calendar.getInstance();
		calendar.setTime(new Date(40L*86400000L+3600000L+61001L)); //1 month 1 week 2 days 1 hour 1 min 1 sec 1 mils
		adapter = new  CalendarAdapter(Locale.US, calendar.getTime());
	}

}
