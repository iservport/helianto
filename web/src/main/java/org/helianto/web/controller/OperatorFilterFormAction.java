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

import org.helianto.controller.AbstractFilterOnlyFormAction;
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;


/**
 * Presentation logic to select namespace operators.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("operatorFilterAction")
public class OperatorFilterFormAction extends AbstractFilterOnlyFormAction<OperatorFilter, Operator> {

	/**
     * Form object name.
     * 
     * <p>The default form object name is overriden here
     * because we need to avoid a name clash with another 
     * filter (the entity filter) on the same flow.</p>
     */
	@Override
	public String getFormObjectName() {
		return "parentFilter";
	}

	/**
	 * Return empty filter because a full operator list will be required.
	 */
	@Override
	public OperatorFilter doCreateFilter() throws Exception {
		return OperatorFilter.filterFactory(null);
	}

	/**
	 * Display a full list of operators.
	 */
	@Override
	protected List<Operator> doApplyFilter(OperatorFilter filter) {
		return namespaceMgr.findOperator();
	}

	/**
	 * After the search, let's make sure there is at least one operator in context.
	 */
	@Override
	protected boolean afterApplyFilter(RequestContext context, OperatorFilter filter, List<Operator> targetList) {
		Operator operator = (Operator) get(context);
		if (operator==null) {
			put(context, targetList.get(0));
		}
		return true;
	}

	@Override
	public String getTargetAttributeName() {
		return "operator";
	}

	/**
     * Target list attribute name.
     * 
     * <p>This is not usually overriden. Here, it is
     * required because we need to avoid a name clash with another 
     * list (the entity list) on the same flow.</p>
     */
	@Override
	public String getTargetListAttributeName() {
		return "operatorList";
	}

	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

}
