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
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store user associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userAssociationAction")
public class UserAssociationFormAction extends AbstractEditAggregateFormAction<UserAssociation, UserGroup> {

	@Override
	protected UserAssociation doStoreTarget(UserAssociation detachedTarget) throws Exception {
		return userMgr.storeUserAssociation(detachedTarget);
	}

	@Override
	public UserAssociation doCreateTarget(RequestContext context, UserGroup parent) throws Exception {
		PublicUserDetails secureUser = (PublicUserDetails) context.getConversationScope().get("secureUser");
		// Create a new user having the same privileges as the current user
		// TODO allow privileges to be narrowed
		String principal = (String) context.getRequestScope().get("principal");
		if (logger.isDebugEnabled()) {
			logger.debug("Ready to create user with principal ".concat(principal));
		}
		return userMgr.createUserAssociation(secureUser.getUser(), principal);
	}

	@Override
	protected UserAssociation doPrepareTarget(RequestContext context, UserAssociation target) throws Exception {
		return target;
	}

	@Override
	protected UserGroup getManagedParent(UserAssociation managedTarget) {
		return managedTarget.getParent();
	}

	@Override
	public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {
		internalTargetPropertyEditorSetter(targetPropertyEditor, UserAssociation.class);
	}

	@Override
	protected List<UserAssociation> getAggregateList(RequestContext context,
			UserGroup parent) {
		return null;
	}

	@Override
	public String getParentAttributeName() {
		return "user";
	}

	@Override
	public String getTargetAttributeName() {
		return "userAssociation";
	}

	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
