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

import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractFilterOnlyFormAction;
import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.Operator;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to filter entities related to a single namespace.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("entityFilterAction")
public class EntityFilterFormAction extends AbstractFilterOnlyFormAction<EntityFilter, Entity> {

	@Override
	public EntityFilter doCreateFilter() throws Exception {
		EntityFilter entityFilter = EntityFilter.entityFilterFactory(null);
		return entityFilter;
	}

	/**
	 * Inject the operator in the context inside the filter before searching.
	 */
	@Override
	protected boolean beforeApplyFilter(RequestContext context, EntityFilter filter) {
		Object operator = context.getFlowScope().getRequired("operator");
		filter.setOperator((Operator) operator);
		return true;
	}

	@Override
	protected List<Entity> doApplyFilter(EntityFilter filter) {
		return namespaceMgr.findEntities(filter);
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
