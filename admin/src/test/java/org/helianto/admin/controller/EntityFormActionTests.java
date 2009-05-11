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


package org.helianto.admin.controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.test.MockRequestContext;


/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFormActionTests {
	
	@Test
	public void testDoPrepareTarget() throws Exception {
		Entity target = EntityTestSupport.createEntity();
		assertSame(target, formAction.doPrepareTarget(context, target));
	}
	
	@Test
	public void testGetManagedParent() {
		Entity target = EntityTestSupport.createEntity();
		assertSame(target.getOperator(), formAction.getManagedParent(target));
	}
	
	@Test
	public void testDoCreateTarget() throws Exception {
		Operator parent = OperatorTestSupport.createOperator();
		Entity target = formAction.doCreateTarget(context, parent);
		assertSame(parent, target.getOperator());
		assertEquals("", target.getAlias());
	}
	
	@Test
	public void testDoStoreTarget() throws Exception {
		Entity detachedTarget = EntityTestSupport.createEntity();
		Entity managedTarget = EntityTestSupport.createEntity();
		
		expect(namespaceMgr.storeEntity(detachedTarget)).andReturn(managedTarget);
		replay(namespaceMgr);
		
		assertSame(managedTarget, formAction.doStoreTarget(detachedTarget));
		verify(namespaceMgr);
	}
	
	@Test
	public void testGetTargetAttributeName() {
		assertEquals("entity", formAction.getTargetAttributeName());
	}
	
	@Test
	public void testGetParentAttributeName() {
		assertEquals("operator", formAction.getParentAttributeName());
	}
	
	private RequestContext context;
	private EntityFormAction formAction;
	private NamespaceMgr namespaceMgr;
	
	@Before
	public void setUp() {
		context = new MockRequestContext();
		formAction = new EntityFormAction();
		namespaceMgr = createMock(NamespaceMgr.class);
		formAction.setNamespaceMgr(namespaceMgr);
	}
	
	@After
	public void tearDown() {
		reset(namespaceMgr);
	}

}
