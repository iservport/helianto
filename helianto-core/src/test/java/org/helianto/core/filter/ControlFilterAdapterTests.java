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

package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractEventControlInternalFilterAdapter;
import org.helianto.core.form.EventControlInternalForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ControlFilterAdapterTests {
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		Mockito.when(filter.getForm().getInternalNumber()).thenReturn(1L);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void frequency() {
		Mockito.when(filter.getForm().getFrequency()).thenReturn(30);
		assertEquals("alias.entity.id = 1 AND alias.frequency = 30 ", filter.createCriteriaAsString());
	}
	
	// locals
	
	private AbstractEventControlInternalFilterAdapter<EventControlInternalForm> filter;
	private EventControlInternalForm form;
	private Entity entity;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		form = Mockito.mock(EventControlInternalForm.class);
		filter = new AbstractEventControlInternalFilterAdapter<EventControlInternalForm>(form) {
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { 
				super.doFilter(mainCriteriaBuilder);
			}
		};
		Mockito.when(filter.getForm().getEntity()).thenReturn(entity);
	}
	
	@After
	public void tearDown() {
		Mockito.reset(form);
	}
	
}
