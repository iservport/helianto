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
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store provinces.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("provinceAction")
public class ProvinceFormAction extends
		AbstractEditAggregateFormAction<Province, Operator> {

	@Override
	public Province doCreateTarget(RequestContext context, Operator parent) throws Exception {
		return Province.provinceFactory(parent);
	}

	@Override
	protected Province doPrepareTarget(RequestContext context, Province target) throws Exception {
		return target;
	}

	@Override
	protected Operator getManagedParent(Province managedTarget) {
		return managedTarget.getOperator();
	}

	@Override
	protected Province doStoreTarget(Province detachedTarget) throws Exception {
		return namespaceMgr.storeProvince(detachedTarget);
	}

	@Override
	public String getTargetAttributeName() {
		return "province";
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
	
//	private static Log logger = LogFactory.getLog(ProvinceFormAction.class);

}
