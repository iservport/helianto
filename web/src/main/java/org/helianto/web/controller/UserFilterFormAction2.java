package org.helianto.web.controller;
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

import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractFilterOnlyFormAction;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;


/**
 * Presentation logic to select users.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userFilterAction2")
public class UserFilterFormAction2 extends AbstractFilterOnlyFormAction<UserFilter, UserGroup> {
	
	@Override
	protected boolean doPreProcess(UserFilter filter, RequestContext context) throws Exception {
		filter.setDiscriminator('U');
		return true;
	}

	@Override
	public UserFilter doCreateFilter() throws Exception {
		return UserFilter.userFilterFactory(getAuthorizedUser());
	}

	@Override
	protected boolean doResetFilter(UserFilter filter, RequestContext context) throws Exception {
		String principal = filter.getOptionalAlias().concat("@").concat(filter.getDomain());
		context.getRequestScope().put("principal", principal);
		filter.setDomain("");
		return true;
	}

	@Override
	protected List<UserGroup> doApplyFilter(UserFilter filter) {
		return userMgr.findUsers(filter);
	}

	@Override
	public String getTargetAttributeName() {
		return "user";
	}
	
	// collabs
	
	private UserMgr userMgr;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}

}
