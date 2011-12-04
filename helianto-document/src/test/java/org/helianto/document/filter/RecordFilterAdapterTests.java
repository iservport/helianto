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

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractControlFilterAdapter;
import org.helianto.document.base.AbstractRecord;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class RecordFilterAdapterTests {
	
	@Test
	public void empty() {
		assertEquals("alias.entity.id = 1 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		filter.getForm().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void complete() {
		filter.getForm().setComplete(50);
		assertEquals("alias.entity.id = 1 AND alias.complete = 50 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void completeZero() {
		filter.getForm().setComplete(0);
		assertEquals("alias.entity.id = 1 AND alias.complete = 0 ", filter.createCriteriaAsString());
	}
	
	@Test
	public void resolution() {
		filter.getForm().setResolution('R');
		assertEquals("alias.entity.id = 1 AND alias.resolution = 'R' ", filter.createCriteriaAsString());
	}
	
	// locals
	
	private AbstractControlFilterAdapter<AbstractRecord> filter;
	private AbstractRecord form;
	private Entity entity;
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		form = new AbstractRecord() {
			public Entity getEntity() { return entity; }
		};
		filter = new AbstractControlFilterAdapter<AbstractRecord>(form) {
			@Override
			public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { 
				super.doFilter(mainCriteriaBuilder);
			}
		};
		((AbstractRecord) form).setComplete(-1);
		((AbstractRecord) form).setNextCheckDate(null);
		((AbstractRecord) form).setResolution(' ');
	}
	
}
