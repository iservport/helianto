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


package org.helianto.web.test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.controller.AbstractEditAggregateFormAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;


/**
 * Base class to test edit aggregate form actions.
 * 
 * @param <T> the target object in the form
 * @param <P> the parent object that aggregates the target
 * @param <F> the form action under test
 * @param <M> the service object 
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEditAggregateFormActionTests<T, P, F extends AbstractEditAggregateFormAction<T, P>, M> {
	
	protected abstract T createTestInstance();
	
	protected abstract T doPrepareTarget(M testMgr, T target);
	
	protected abstract T getPreparedTarget(F formAction, RequestContext context, T target) throws Exception;
	
	@Test
	public void testDoPrepareTarget() throws Exception {
		T target = createTestInstance();
		T managedTarget = createTestInstance();
		
		expect(doPrepareTarget(testMgr, target));
		expectLastCall().andReturn(managedTarget);
		replay(testMgr);
		
		assertSame(managedTarget, getPreparedTarget(formAction, context, target));
		verify(testMgr);
	}
	
	protected abstract P getParent(T target);
	
	protected abstract P getManagedParent(F formAction, T target) throws Exception;
	
	@Test
	public void testGetManagedParent() throws Exception {
		T target = createTestInstance();
		assertSame(getParent(target), getManagedParent(formAction, target));
	}
	
	@Test
	public void testDoCreateTarget() throws Exception {
		P parent = getParent(createTestInstance());
		T target = formAction.doCreateTarget(context, parent);
		assertSame(parent, getParent(target));
	}
	
	protected abstract T doStoreTarget(M testMgr, T detachedTarget);
	
	protected abstract T getStoredTarget(F formAction, T detachedTarget) throws Exception;
	
	@Test
	public void testDoStoreTarget() throws Exception {
		T detachedTarget = createTestInstance();
		T managedTarget = createTestInstance();
		
		expect(doStoreTarget(testMgr, detachedTarget));
		expectLastCall().andReturn(managedTarget);
		replay(testMgr);
		
		assertSame(managedTarget, getStoredTarget(formAction, detachedTarget));
		verify(testMgr);
	}
	
	protected abstract String getTargetAttributeName();
	
	@Test
	public void testGetTargetAttributeName() {
		assertEquals(getTargetAttributeName(), formAction.getTargetAttributeName());
	}
	
	protected abstract String getParentAttributeName();
	
	@Test
	public void testGetParentAttributeName() {
		assertEquals(getParentAttributeName(), formAction.getParentAttributeName());
	}
	
	private RequestContext context;
	private F formAction;
	private M testMgr;
	
	protected F getFormAction() {
		return formAction;
	}
	
	protected M getTestMgr() {
		return testMgr;
	}
	
	protected abstract F createFormActionInstance();
	
	protected abstract Class<M> getTestMgrClass();
	
	protected abstract void injectTestMgr(F formAction, M testMgr);
	
	@Before
	public void setUp() {
		context = new MockRequestContext();
		formAction = createFormActionInstance();
		testMgr = createMock(getTestMgrClass());
		injectTestMgr(formAction, testMgr);
	}
	
	@After
	public void tearDown() {
		reset(testMgr);
	}

}
