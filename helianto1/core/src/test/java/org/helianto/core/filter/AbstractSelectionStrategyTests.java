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

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.core.test.UserTestSupport;

/**
 * 
 * @author mauricio
 */
public class AbstractSelectionStrategyTests extends TestCase {
	
	SelectionStrategy<UserBackedCriteriaFilterStub> selectionStrategy;
	boolean isSelection;
	StringBuilder chain = new StringBuilder();

	
	public void testSelectionChain() {
		isSelection = true;
		selectionStrategy.createCriteriaAsString(new UserBackedCriteriaFilterStub(), "TEST");
		assertEquals("TEST-PRE-TEST-SELECT-TEST-POST", chain.toString());
	}

	public void testFilterChain() {
		isSelection = false;
		selectionStrategy.createCriteriaAsString(new UserBackedCriteriaFilterStub(), "TEST");
		assertEquals("TEST-PRE-TEST-FILTER-TEST-POST", chain.toString());
	}
	
	public void testAppendEntityFromUserBackedFilter() {
		CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
		Entity entity = new Entity();
		((AbstractSelectionStrategy<UserBackedCriteriaFilterStub>) selectionStrategy).appendEntityFilter(entity, criteriaBuilder);
		assertEquals("entity.id = 0 ", criteriaBuilder.getCriteriaAsString());
	}
	
	public void testAppendEqualFilterString() {
		CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
		((AbstractSelectionStrategy<UserBackedCriteriaFilterStub>) selectionStrategy).appendEqualFilter("fieldName", "fieldContent", criteriaBuilder);
		assertEquals("fieldName = 'fieldContent' ", criteriaBuilder.getCriteriaAsString());
	}
	
	public void testAppendEqualFilterInteger() {
		CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
		((AbstractSelectionStrategy<UserBackedCriteriaFilterStub>) selectionStrategy).appendEqualFilter("fieldName", 1, criteriaBuilder);
		assertEquals("fieldName = 1 ", criteriaBuilder.getCriteriaAsString());
	}
	
	public void testAppendLikeFilter() {
		CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
		((AbstractSelectionStrategy<UserBackedCriteriaFilterStub>) selectionStrategy).appendLikeFilter("fieldName", "fieldContent", criteriaBuilder);
		assertEquals("fieldName like '%fieldContent%' ", criteriaBuilder.getCriteriaAsString());
	}
	
	public void setUp() {
		selectionStrategy = new SelectionStrategyStub();
	}

	@SuppressWarnings("serial")
	class UserBackedCriteriaFilterStub extends AbstractUserBackedCriteriaFilter {
		UserBackedCriteriaFilterStub() {
			setUser(UserTestSupport.createUser());
		}
		public void reset() {}
	}

	class SelectionStrategyStub extends AbstractSelectionStrategy<UserBackedCriteriaFilterStub> {
		
		@Override
		protected void preProcessFilter(UserBackedCriteriaFilterStub filter, CriteriaBuilder mainCriteriaBuilder) {
			chain.append(mainCriteriaBuilder.getPrefix()).append("-PRE-");
		}
		
		@Override
		protected boolean isSelection(UserBackedCriteriaFilterStub filter) {
			return isSelection;
		}
		
		@Override
		protected void doSelect(UserBackedCriteriaFilterStub filter, CriteriaBuilder mainCriteriaBuilder) {
			chain.append(mainCriteriaBuilder.getPrefix()).append("-SELECT-");
		}

		@Override
		protected void doFilter(UserBackedCriteriaFilterStub filter, CriteriaBuilder mainCriteriaBuilder) {
			chain.append(mainCriteriaBuilder.getPrefix()).append("-FILTER-");
		}

		@Override
		protected void postProcessFilter(UserBackedCriteriaFilterStub filter, CriteriaBuilder mainCriteriaBuilder) {
			chain.append(mainCriteriaBuilder.getPrefix()).append("-POST");
		}
		
	}
}

