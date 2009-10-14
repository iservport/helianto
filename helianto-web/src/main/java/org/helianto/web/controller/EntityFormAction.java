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

import org.helianto.controller.AbstractEditAggregateFormAction;
import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("entityAction")
public class EntityFormAction extends AbstractEditAggregateFormAction<Entity, Operator> {

	@Override
	protected Entity doPrepareTarget(RequestContext context, Entity target) throws Exception {
		return namespaceMgr.prepareEntity(target);
	}

	@Override
	protected Operator getManagedParent(Entity managedTarget) {
		return managedTarget.getOperator();
	}

	@Override
	public Entity doCreateTarget(RequestContext context, Operator parent) throws Exception {
		return Entity.entityFactory(parent, "");
	}

	/**
	 * If it is a first time store, re-create the entity to assure for default
	 * user group creation.
	 */
	@Override
	protected Entity doStoreTarget(Entity detachedTarget) throws Exception {
		if (detachedTarget.getId()==0) {
			return namespaceMgr.createAndPersistEntity(detachedTarget.getOperator(), detachedTarget.getAlias());
		} 
		return namespaceMgr.storeEntity(detachedTarget);
	}

	@Override
	public String getTargetAttributeName() {
		return "entity";
	}

	@Override
	public String getParentAttributeName() {
		return "operator";
	}

	/**
	 * Overriden to customize key field shown in a 
	 * standard integrity violation message.
	 */
	protected String getKeyField() {
		return "alias";
	}
	
	@Resource(name="entityFormValidator")
	@Override
	public void setValidator(Validator validator) {
		super.setValidator(validator);
	}

	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
//	private static Log logger = LogFactory.getLog(EntityFormAction.class);

}
