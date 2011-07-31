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

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.base.AbstractEvent;
import org.helianto.document.filter.AbstractEventFilterAdapter;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EventFilterAdapterTests {
	
	private AbstractEventFilterAdapter<AbstractEvent> eventFilter;
	private Entity entity;
	
	@Test
	public void empty() {
		eventFilter.getForm().setIssueDate(null);
		assertEquals("alias.entity.id = 1 ", eventFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		eventFilter.getForm().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", eventFilter.createCriteriaAsString());
	}
	
	@Test
	public void toIssueDate() {
		eventFilter.getForm().setIssueDate(new Date(1000l));
		assertEquals("alias.entity.id = 1 AND (alias.issueDate >= '1969-12-24 23:59:59' AND alias.issueDate < '1969-12-31 21:00:01' ) ", eventFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		eventFilter = new AbstractEventFilterAdapter<AbstractEvent>(new AbstractEvent() {
			public String getInternalNumberKey() { return "KEY"; }
			public Entity getEntity() { return entity; }
			}) {
			public void reset() { }
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { 
				super.doFilter(mainCriteriaBuilder);
			}
		};
	}
	
}
