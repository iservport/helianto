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

import java.beans.PropertyEditor;
import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractEditAggregateFormAction;
import org.helianto.core.Entity;
import org.helianto.core.UserGroup;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store user groups.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userGroupAction")
public class UserGroupFormAction extends AbstractEditAggregateFormAction<UserGroup, Entity> {

	@Override
	protected UserGroup doPrepareTarget(RequestContext context, UserGroup target) throws Exception {
		return userMgr.prepareUserGroup(target);
	}

	@Override
	protected UserGroup doStoreTarget(UserGroup detachedTarget) throws Exception {
		return userMgr.storeUserGroup(detachedTarget);
	}

	@Override
	public UserGroup doCreateTarget(RequestContext context, Entity parent) throws Exception {
		return UserGroup.userGroupFactory(parent, null);
	}

	@Override
	protected Entity getManagedParent(UserGroup managedTarget) {
		return managedTarget.getEntity();
	}

	@Override
	public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {
		internalTargetPropertyEditorSetter(targetPropertyEditor, UserGroup.class);
	}

	@Override
	protected List<UserGroup> getAggregateList(RequestContext context, Entity parent) {
		return parent.getUserList();
	}

	@Override
	public String getTargetAttributeName() {
		return "user";
	}

	@Override
	public String getParentAttributeName() {
		return "entity";
	}

	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
