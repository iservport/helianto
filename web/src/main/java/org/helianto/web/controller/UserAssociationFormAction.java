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

import javax.annotation.Resource;

import org.helianto.controller.AbstractAssociationFormAction;
import org.helianto.core.CreateIdentity;
import org.helianto.core.User;
import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to create user association.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userAssociationAction")
public class UserAssociationFormAction extends
		AbstractAssociationFormAction<UserAssociation, UserGroup, UserGroup> {

	@Override
	protected UserAssociation doCreateTarget(RequestContext context, UserGroup parent) throws Exception {
		return userMgr.prepareNewUserAssociation(parent);
	}

	/**
	 * Associate an identity to the user before storing.
	 */
	@Override
	protected void preProcessStoreTarget(RequestContext context, UserAssociation detachedTarget) throws Exception {
		ParameterMap parameters = context.getRequestParameters();
		if (!detachedTarget.isKeyEmpty() && parameters.contains("createIdentity")) {
			detachedTarget.getChild().setCreateIdentity(CreateIdentity.AUTO);
		}
	}

	@Override
	protected UserAssociation doSelectTarget(RequestContext context)
			throws Exception {
		// TODO Auto-generated method stub
		return super.doSelectTarget(context);
	}

	@Override
	protected UserAssociation doStoreTarget(UserAssociation detachedTarget) throws Exception {
		return userMgr.storeUserAssociation(detachedTarget);
	}

	@Override
	protected String getKeyField() {
		return "child.userKey";
	}

	@Override
	public String getTargetAttributeName() {
		return "userAssociation";
	}

	public String getParentAttributeName() {
		return "userGroup";
	}

	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
