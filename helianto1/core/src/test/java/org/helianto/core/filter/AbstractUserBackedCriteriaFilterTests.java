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

import java.io.Serializable;

import junit.framework.TestCase;

import org.helianto.core.User;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractUserBackedCriteriaFilterTests extends TestCase {
	
	private int resetTest = 0;
	
	public class UserBackedCriteriaFilterStub extends AbstractUserBackedCriteriaFilter {

		private static final long serialVersionUID = 1L;

		public void reset() {
			resetTest++;
		}
		
	}
	
	public void testConstructor() {
		UserBackedFilter userBackedCriteriaFilter = new UserBackedCriteriaFilterStub();
		assertTrue(userBackedCriteriaFilter instanceof UserBackedFilter);
		assertTrue(userBackedCriteriaFilter instanceof Serializable);
	}
	
	public void testUser() {
		UserBackedFilter userBackedCriteriaFilter = new UserBackedCriteriaFilterStub();
		User user = new User();
		userBackedCriteriaFilter.setUser(user);
		assertSame(user, userBackedCriteriaFilter.getUser());
	}
	
	public void testFactoryMethodInternal() {
		User user = new User();
		UserBackedFilter userBackedCriteriaFilter = 
			AbstractUserBackedCriteriaFilter.filterFactory(this, UserBackedCriteriaFilterStub.class, user);
		assertTrue(userBackedCriteriaFilter instanceof UserBackedCriteriaFilterStub);
		assertSame(user, userBackedCriteriaFilter.getUser());
	}

	public void testFactoryMethodExternal() {
		User user = new User();
		UserBackedFilter userBackedCriteriaFilter = 
			IdentityFilter.filterFactory(IdentityFilter.class, user);
		assertTrue(userBackedCriteriaFilter instanceof IdentityFilter);
		assertSame(user, userBackedCriteriaFilter.getUser());
	}
	
	public void testReset() {
		UserBackedFilter userBackedCriteriaFilter = new UserBackedCriteriaFilterStub();
		int beforeTest = resetTest;
		userBackedCriteriaFilter.reset();
		assertEquals(beforeTest+1, resetTest);
	}

}
