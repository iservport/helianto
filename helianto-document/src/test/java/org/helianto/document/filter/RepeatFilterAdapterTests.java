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
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.document.AbstractRepeatable;
import org.helianto.document.TrackingMode;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class RepeatFilterAdapterTests {
	
	private AbstractRepeatFilterAdapter<AbstractRepeatable> repeatableFilter;
	private Entity entity;
	
	@Test
	public void empty() {
		repeatableFilter.getFilter().setIssueDate(null);
		assertEquals("alias.entity.id = 1 ", repeatableFilter.createCriteriaAsString());
	}
	
	@Test
	public void selection() {
		repeatableFilter.getFilter().setInternalNumber(1);
		assertEquals("alias.entity.id = 1 AND alias.internalNumber = 1 ", repeatableFilter.createCriteriaAsString());
	}
	
	@Test
	public void trackingMode() {
		repeatableFilter.getFilter().setTrackingMode(TrackingMode.NOT_TRACKED.getValue());
		assertEquals("alias.entity.id = 1 AND alias.trackingMode = 'N' ", repeatableFilter.createCriteriaAsString());
	}
	
	@Test
	public void frequency() {
		repeatableFilter.getFilter().setFrequency(30);
		assertEquals("alias.entity.id = 1 AND alias.frequency = 30 AND alias.frequencyType = 5 ", repeatableFilter.createCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = new Entity(new Operator("DEFAULT"), "ENTITY");
		entity.setId(1);
		repeatableFilter = new AbstractRepeatFilterAdapter<AbstractRepeatable>(new RepeatableStub()) {
			public void reset() { }
			@Override
			public void doFilter(CriteriaBuilder mainCriteriaBuilder) { 
				super.doFilter(mainCriteriaBuilder);
			}
		};
		// clean filter
		repeatableFilter.getFilter().setTrackingMode(' ');
	}
	
	@SuppressWarnings("serial")
	class RepeatableStub extends AbstractRepeatable {
		public String getInternalNumberKey() { return "KEY"; }
		public Entity getEntity() { return entity; }
	}
	
}
