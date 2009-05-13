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

import org.helianto.core.Entity;
import org.helianto.core.UserGroup;
import org.helianto.core.service.UserMgr;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.web.test.AbstractEditAggregateFormActionTests;
import org.springframework.webflow.execution.RequestContext;


/**
 * @author Mauricio Fernandes de Castro
 */
public class UserFormActionTests extends AbstractEditAggregateFormActionTests<UserGroup, Entity, UserFormAction, UserMgr>{
	
	@Override
	protected UserGroup createTestInstance() {
		return UserGroupTestSupport.createUserGroup();
	}
	
	@Override
	protected UserGroup doPrepareTarget(UserMgr testMgr, UserGroup target) {
		return testMgr.prepareUserGroup(target);
	}
	
	@Override
	protected UserGroup getPreparedTarget(UserFormAction formAction, RequestContext context, UserGroup target) throws Exception {
		return formAction.doPrepareTarget(context, target);
	}
	
	@Override
	protected Entity getParent(UserGroup target) {
		return target.getEntity();
	}
	
	@Override
	protected Entity getManagedParent(UserFormAction formAction, UserGroup target) throws Exception {
		return formAction.getManagedParent(target);
	}
	
	@Override
	protected UserGroup doStoreTarget(UserMgr testMgr, UserGroup detachedTarget) {
		return testMgr.storeUserGroup(detachedTarget, false);
	}
	
	@Override
	protected UserGroup getStoredTarget(UserFormAction formAction, UserGroup detachedTarget) throws Exception {
		return formAction.doStoreTarget(detachedTarget);
	}
	
	@Override
	protected UserFormAction createFormActionInstance() {
		return new UserFormAction();
	}

	@Override
	protected String getParentAttributeName() {
		return "entity";
	}

	@Override
	protected String getTargetAttributeName() {
		return "user";
	}

	@Override
	protected Class<UserMgr> getTestMgrClass() {
		return UserMgr.class;
	}

	@Override
	protected void injectTestMgr(UserFormAction formAction, UserMgr testMgr) {
		formAction.setUserMgr(testMgr);
	}

}
