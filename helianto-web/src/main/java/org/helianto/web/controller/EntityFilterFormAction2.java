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
import org.helianto.core.UserState;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.SecureUserDetails;
import org.helianto.core.service.UserMgr;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;


/**
 * Presentation logic to select entities.
 * <p>
 * Entities are selected if the current authorized user (secure user)
 * has also privileges to access them.
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@Component("entityFilterAction2")
public class EntityFilterFormAction2 extends AbstractFilterOnlyFormAction<UserFilter, UserGroup> {
	
	/**
	 * Subclasses should override to customize secure user retrieval.
	 */
	protected PublicUserDetails getPublicUserDetails() {
		return (PublicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	protected boolean doPreProcess(UserFilter filter, RequestContext context) throws Exception {
		SecureUserDetails secureUser = (SecureUserDetails) getPublicUserDetails();
        context.getConversationScope().put("secureUser", getPublicUserDetails());
        filter.setUserState(UserState.ACTIVE);
		if (secureUser.getCredential().isExpired()) {
			return false;
		}
		return true;
	}

	@Override
	protected List<UserGroup> doApplyFilter(UserFilter filter) {
		return userMgr.findUsers(filter);
	}

	@Override
	public UserFilter doCreateFilter() throws Exception {
		PublicUserDetails secureUser = getPublicUserDetails();
		if (secureUser!=null) {
			UserFilter filter = UserFilter.userFilterFactory(getAuthorizedUser().getIdentity());
			return filter;
		}
		throw new IllegalArgumentException("Not a secure user!");
	}

	@Override
	protected boolean postProcessSelectTarget(RequestContext context, UserGroup target) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("XXXXXXXXXXXXXXXXXX"+auth);
//		UserDetails userDetails = userDetailsService.loadUserByUsername(target.getUserKey());
//		SecurityContextHolder.getContext().setAuthentication(auth);
		getFormObjectScope().getScope(context).put(getTargetListAttributeName(), null);
		return true;
	}

	@Override
	public String getTargetAttributeName() {
		return "user";
	}
	
	// collabs
	
	private UserMgr userMgr;
	private UserDetailsService userDetailsService;

	@Resource
	public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
	
	@Resource
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
