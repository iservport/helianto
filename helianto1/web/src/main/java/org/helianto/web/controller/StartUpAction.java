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

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Operator;
import org.helianto.core.security.AbstractUserDetails;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.service.ServerMgr;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Action visible before login.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class StartUpAction extends MultiAction {
	
	private ServerMgr serverMgr;
	
	/**
	 * Check if there is at least one operator available.
	 */
	public Event startUp(RequestContext context) {
		List<Operator> operatorList = serverMgr.findOperator();
		if (logger.isDebugEnabled()) {
			logger.debug("Operator list found: "+operatorList.size());
		}
		if (operatorList.size()==0) {
			return new Event(this, "install");
		}
		return new Event(this, "login");
	}
	
	/**
	 * Do pre-login tasks
	 * 
	 */
	public Event preLogin(RequestContext context) {
		return success();
	}
	
	/**
	 * Do post-login tasks
	 * 
	 */
	public Event postLogin(RequestContext context) {
		PublicUserDetails secureUser = AbstractUserDetails.retrievePublicUserDetailsFromSecurityContext();
		if (secureUser!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("User in secure context: "+secureUser.getUser());
			}
			return success();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("User not in secure context");
		}
		return error();
	}
	
	// collabs
	
	@Resource
	public void setServerMgr(ServerMgr serverMgr) {
		this.serverMgr = serverMgr;
	}
	
	public static Log logger = LogFactory.getLog(StartUpAction.class);

}
