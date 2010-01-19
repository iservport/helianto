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


package org.helianto.web.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.web.test.AbstractEditAggregateFormActionTests;
import org.junit.Test;
import org.springframework.webflow.execution.RequestContext;


/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFormActionTests extends AbstractEditAggregateFormActionTests<Entity, Operator, EntityFormAction, NamespaceMgr>{
	
	@Override
	public Entity createTestInstance() {
		return EntityTestSupport.createEntity();
	}
	
	@Test
	public void doStoreTargetNew() throws Exception {
		Entity detachedTarget = createTestInstance();
		Operator parent = getParent(createTestInstance());
		detachedTarget.setOperator(parent);
		detachedTarget.setAlias("user");
		Entity managedTarget = createTestInstance();
		
		getTestMgr().createAndPersistEntity(parent, "user");
		expectLastCall().andReturn(managedTarget);
		replay(getTestMgr());
		
		assertSame(managedTarget, getStoredTarget(getFormAction(), detachedTarget));
		verify(getTestMgr());
	}
	
	@Test
	public void doStoreTarget() throws Exception {
		Entity detachedTarget = createTestInstance();
		detachedTarget.setId(1);
		Entity managedTarget = createTestInstance();
		
		expect(doStoreTarget(getTestMgr(), detachedTarget));
		expectLastCall().andReturn(managedTarget);
		replay(getTestMgr());
		
		assertSame(managedTarget, getStoredTarget(getFormAction(), detachedTarget));
		verify(getTestMgr());
	}
	
	@Override
	public Entity doPrepareTarget(NamespaceMgr testMgr, Entity target) {
		return testMgr.prepareEntity(target);
	}
	
	@Override
	public Entity getPreparedTarget(EntityFormAction formAction, RequestContext context, Entity target) throws Exception {
		return formAction.doPrepareTarget(context, target);
	}
	
	@Override
	public Operator getParent(Entity target) {
		return target.getOperator();
	}
	
	@Override
	public Operator getManagedParent(EntityFormAction formAction, Entity target) throws Exception {
		return formAction.getManagedParent(target);
	}
	
	@Override
	public Entity doStoreTarget(NamespaceMgr testMgr, Entity detachedTarget) {
		return testMgr.storeEntity(detachedTarget);
	}
	
	@Override
	public Entity getStoredTarget(EntityFormAction formAction, Entity detachedTarget) throws Exception {
		return formAction.doStoreTarget(detachedTarget);
	}
	
	@Override
	public EntityFormAction createFormActionInstance() {
		return new EntityFormAction();
	}

	@Override
	public String getParentAttributeName() {
		return "operator";
	}

	@Override
	public String getTargetAttributeName() {
		return "entity";
	}

	@Override
	public Class<NamespaceMgr> getTestMgrClass() {
		return NamespaceMgr.class;
	}

	@Override
	public void injectTestMgr(EntityFormAction formAction, NamespaceMgr testMgr) {
		formAction.setNamespaceMgr(testMgr);
	}

}
