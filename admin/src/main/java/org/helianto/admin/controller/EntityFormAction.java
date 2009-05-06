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


package org.helianto.admin.controller;

import javax.annotation.Resource;

import org.helianto.controller.AbstractTargetFormAction;
import org.helianto.core.Entity;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("entityAction")
public class EntityFormAction extends AbstractTargetFormAction<Entity> {

	@Override
	protected Entity doPrepareTarget(RequestContext context, Entity target) throws Exception {
		return target;
	}

	@Override
	protected Entity doStoreTarget(Entity detachedTarget) throws Exception {
		return namespaceMgr.storeEntity(detachedTarget);
	}

	@Override
	public String getTargetAttributeName() {
		return "entity";
	}

	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

}
