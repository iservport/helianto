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

import org.helianto.controller.AbstractEditTargetFormAction;
import org.helianto.core.Identity;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to identity selection.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("identityAction")
public class IdentityFormAction extends AbstractEditTargetFormAction<Identity> {

	@Override
	protected Identity doCreateTarget(RequestContext context) throws Exception {
		return Identity.identityFactory("");
	}

	@Override
	protected Identity doPrepareTarget(RequestContext context, Identity target) throws Exception {
		return target;
	}

	@Override
	protected Identity doStoreTarget(Identity detachedTarget) throws Exception {
		return userMgr.storeIdentity(detachedTarget);
	}

	@Override
	protected String getKeyField() {
		return "principal";
	}
	
	@Override
	public String getTargetAttributeName() {
		return "identity";
	}
	
	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

//	private static final Logger logger = LoggerFactory.getLogger(IdentityFormAction.class);

}
