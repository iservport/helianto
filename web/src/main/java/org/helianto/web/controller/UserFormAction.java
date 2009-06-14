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

import javax.annotation.Resource;

import org.helianto.controller.AbstractEditAggregateFormAction;
import org.helianto.core.CreateIdentity;
import org.helianto.core.UserAssociation;
import org.helianto.core.User;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store users.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userAction")
public class UserFormAction extends AbstractEditAggregateFormAction<User, UserAssociation> {

	@Override
	protected User doPrepareTarget(RequestContext context, User target) throws Exception {
		return (User) userMgr.prepareUserGroup(target);
	}

	/**
	 * Associate an identity to the user before storing.
	 */
	@Override
	protected void preProcessStoreTarget(RequestContext context, User detachedTarget) throws Exception {
		ParameterMap parameters = context.getRequestParameters();
		if (detachedTarget.getUserKey().length()>0 && parameters.contains("createIdentity")) {
			detachedTarget.setCreateIdentity(CreateIdentity.AUTO);
		}
	}

	@Override
	protected User doStoreTarget(User detachedTarget) throws Exception {
		return (User) userMgr.storeUserGroup(detachedTarget);
	}

	@Override
	public User doCreateTarget(RequestContext context, UserAssociation parent) throws Exception {
		return User.userFactory(parent.getParent().getEntity());
	}

	@Override
	protected UserAssociation getManagedParent(User managedTarget) {
		return managedTarget.getParentAssociations().iterator().next();
	}

	@Override
	public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {
		internalTargetPropertyEditorSetter(targetPropertyEditor, User.class);
	}

//	@Override
//	protected List<User> getAggregateList(RequestContext context, Entity parent) {
//		return parent.getUserList();
//	}
//
	@Override
	protected String getKeyField() {
		return "userKey";
	}

	@Override
	public String getTargetAttributeName() {
		return "user";
	}

	@Override
	public String getParentAttributeName() {
		return "userAssociation";
	}

	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
