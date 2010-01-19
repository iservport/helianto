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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.service.UserMgr;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.web.test.AbstractEditAggregateFormActionTests;
import org.junit.Test;
import org.springframework.webflow.execution.RequestContext;


/**
 * @author Mauricio Fernandes de Castro
 */
public class UserGroupFormActionTests extends AbstractEditAggregateFormActionTests<UserGroup, Entity, UserGroupFormAction, UserMgr>{
	
	@Override
	public UserGroup createTestInstance() {
		return UserGroupTestSupport.createUserGroup();
	}
	
	@Override
	public UserGroup doPrepareTarget(UserMgr testMgr, UserGroup target) {
		return testMgr.prepareUserGroup(target);
	}
	
	@Override
	public UserGroup getPreparedTarget(UserGroupFormAction formAction, RequestContext context, UserGroup target) throws Exception {
		return formAction.doPrepareTarget(context, target);
	}
	
	@Override
	public Entity getParent(UserGroup target) {
		return target.getEntity();
	}
	
	@Override
	public Entity getManagedParent(UserGroupFormAction formAction, UserGroup target) throws Exception {
		return formAction.getManagedParent(target);
	}
	
	@Override
	@Test
	public void doCreateTarget() throws Exception {
		UserGroup managedTarget = createTestInstance();
		Entity parent = getParent(managedTarget);

		expect(getTestMgr().prepareNewUserGroup(parent));
		expectLastCall().andReturn(managedTarget);
		replay(getTestMgr());

		UserGroup target = getFormAction().doCreateTarget(getContext(), parent);
		assertFalse(target instanceof User);
		assertSame(parent, getParent(target));
	}
	
	@Override
	public UserGroup doStoreTarget(UserMgr testMgr, UserGroup detachedTarget) {
		return testMgr.storeUserGroup(detachedTarget);
	}
	
	@Override
	public UserGroup getStoredTarget(UserGroupFormAction formAction, UserGroup detachedTarget) throws Exception {
		return formAction.doStoreTarget(detachedTarget);
	}
	
	@Override
	public UserGroupFormAction createFormActionInstance() {
		return new UserGroupFormAction();
	}

	@Override
	public String getParentAttributeName() {
		return "entity";
	}

	@Override
	public String getTargetAttributeName() {
		return "userGroup";
	}

	@Override
	public Class<UserMgr> getTestMgrClass() {
		return UserMgr.class;
	}

	@Override
	public void injectTestMgr(UserGroupFormAction formAction, UserMgr testMgr) {
		formAction.setUserMgr(testMgr);
	}

}
