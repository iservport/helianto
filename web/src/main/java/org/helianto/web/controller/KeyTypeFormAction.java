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
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to select KeyTypes.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("keyTypeAction")
public class KeyTypeFormAction extends AbstractEditAggregateFormAction<KeyType, Operator> {

	@Override
	public KeyType doCreateTarget(RequestContext context, Operator parent) throws Exception {
		return KeyType.keyTypeFactory(parent, "");
	}

	@Override
	protected KeyType doPrepareTarget(RequestContext context, KeyType target) throws Exception {
		return target;
	}

	@Override
	protected Operator getManagedParent(KeyType managedTarget) {
		return managedTarget.getOperator();
	}

	@Override
	protected KeyType doStoreTarget(KeyType detachedTarget) throws Exception {
		return namespaceMgr.storeKeyType(detachedTarget);
	}

	@Override
	protected String getKeyField() {
		return "keyCode";
	}

	@Override
	public String getTargetAttributeName() {
		return "keyType";
	}

	@Override
	public String getParentAttributeName() {
		return "operator";
	}

	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

//	private static Log logger = LogFactory.getLog(KeyTypeFilterFormAction.class);

}
