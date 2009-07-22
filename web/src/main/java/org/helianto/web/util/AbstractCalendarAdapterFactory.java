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

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Base class to Locale dependant CalendarAdapter factories.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractCalendarAdapterFactory {

	private Locale locale;

	/**
	 * Default constructor.
	 */
	public AbstractCalendarAdapterFactory() {
		this.locale = Locale.getDefault();
	}
	
	/**
	 * Locale constructor.
	 * 
	 * @param locale
	 */
	public AbstractCalendarAdapterFactory(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * The internal locale instance.
	 */
	public Locale getLocale() {
		return this.locale;
	}
	
	/**
	 * Calendar adapter factory method.
	 */
	public abstract CalendarAdapter calendarAdapterFactory(Date date);
	
	/**
	 * Calendar adapter list factory method.
	 */
	public abstract List<CalendarAdapter> calendarAdapterListFactory(Date date, int weeks);
	
}
