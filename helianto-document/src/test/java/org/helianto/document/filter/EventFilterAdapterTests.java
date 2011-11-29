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

package org.helianto.document.filter;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.Event;
import org.helianto.document.base.AbstractEvent;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EventFilterAdapterTests {
	
	private static final String C1="(alias.issueDate >= '1969-12-24 23:59:59' AND alias.issueDate < '1969-12-31 21:00:01' ) ";
	
	@Test
	public void empty() {
		assertEquals("", filter.createCriteriaAsString());
	}
	
	@Test
	public void toIssueDate() {
		((AbstractEvent) form).setIssueDate(new Date(1000l));
		assertEquals(C1, filter.createCriteriaAsString());
	}
	
	// locals
	private Event form;
	private AbstractEventFilterAdapter<Event> filter;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		form = new AbstractEvent() {};
		filter = new AbstractEventFilterAdapter<Event>(form) {
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
		};
		((AbstractEvent) form).setIssueDate(null);
	}
	
}
