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

import org.helianto.web.util.CalendarAdapter;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CalendarAdapterTests {
	
	@Test
	public void constructor() {
		adapter = new  CalendarAdapter();
		assertTrue(adapter instanceof Serializable);
		assertTrue(adapter.getDate() instanceof Date);
	}
	
	@Test
	public void constructorDate() {
		adapter = new  CalendarAdapter(calendar.getTime());
		assertEquals(3459661001L, adapter.getDate().getTime());
	}

	private CalendarAdapter adapter;
	private Calendar calendar;
	
	@Before
	public void setUp() {
		calendar = Calendar.getInstance();
		calendar.setTime(new Date(40L*86400000L+3600000L+61001L)); //1 month 1 week 2 days 1 hour 1 min 1 sec 1 mils
	}

}
