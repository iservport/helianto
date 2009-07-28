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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.helianto.core.IdentityFilter;
import org.helianto.core.User;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserBackedCriteriaFilterTests {
	
	private int resetTest = 0;
	
	public class UserBackedCriteriaFilterStub extends AbstractUserBackedCriteriaFilter {

		private static final long serialVersionUID = 1L;

		public void reset() {
			resetTest++;
		}

		@Override
		protected void doFilter(CriteriaBuilder mainCriteriaBuilder) { }

		@Override
		protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }

		@Override
		public String getObjectAlias() { return ""; }

		@Override
		public boolean isSelection() { return false; }
		
	}
	
	@Test
	public void constructor() {
		UserBackedFilter userBackedCriteriaFilter = new UserBackedCriteriaFilterStub();
		assertTrue(userBackedCriteriaFilter instanceof UserBackedFilter);
		assertTrue(userBackedCriteriaFilter instanceof Serializable);
	}
	
	@Test
	public void user() {
		UserBackedFilter userBackedCriteriaFilter = new UserBackedCriteriaFilterStub();
		User user = new User();
		userBackedCriteriaFilter.setUser(user);
		assertSame(user, userBackedCriteriaFilter.getUser());
	}
	
	@Test
	public void factoryMethodInternal() {
		User user = new User();
		UserBackedFilter userBackedCriteriaFilter = 
			AbstractUserBackedCriteriaFilter.filterFactory(this, UserBackedCriteriaFilterStub.class, user);
		assertTrue(userBackedCriteriaFilter instanceof UserBackedCriteriaFilterStub);
		assertSame(user, userBackedCriteriaFilter.getUser());
	}

	@Test
	public void factoryMethodExternal() {
		User user = new User();
		UserBackedFilter userBackedCriteriaFilter = 
			IdentityFilter.filterFactory(IdentityFilter.class, user);
		assertTrue(userBackedCriteriaFilter instanceof IdentityFilter);
		assertSame(user, userBackedCriteriaFilter.getUser());
	}
	
	@Test
	public void reset() {
		UserBackedFilter userBackedCriteriaFilter = new UserBackedCriteriaFilterStub();
		int beforeTest = resetTest;
		userBackedCriteriaFilter.reset();
		assertEquals(beforeTest+1, resetTest);
	}

}
