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

import java.util.Date;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.base.AbstractEventFilterAdapter;
import org.helianto.core.form.EventForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class EventFilterAdapterTests {
	
	private static final String C0="alias.entity.id = 1 ";
	private static final String C1="(alias.issueDate >= '1969-12-24 23:59:59' AND alias.issueDate < '1969-12-31 21:00:01' ) ";
	private static final String C2="alias.resolution = 'R' ";
	
	@Test
	public void empty() {
		assertEquals("", filter.createCriteriaAsString());
	}
	
	@Test
	public void entity() {
		Entity entity = EntityTestSupport.createEntity(1);
    	Mockito.when(form.getEntity()).thenReturn(entity);
		assertEquals(C0, filter.createCriteriaAsString());
	}
	
	@Test
	public void toIssueDate() {
    	Mockito.when(form.getIssueDate()).thenReturn(new Date(1000l));
		assertEquals(C1, filter.createCriteriaAsString());
	}
	
	@Test
	public void resolution() {
    	Mockito.when(form.getResolution()).thenReturn('R');
		assertEquals(C2, filter.createCriteriaAsString());
	}
	
	// locals
	private EventForm form;
	private AbstractEventFilterAdapter<EventForm> filter;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
    	form = Mockito.mock(EventForm.class);
		filter = new AbstractEventFilterAdapter<EventForm>(form) {
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
		};
    }
    
    @After
    public void tearDown() {
    	Mockito.reset(form);
    }
    
}
