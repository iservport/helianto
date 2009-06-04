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

import java.beans.PropertyEditor;
import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractEditAggregateFormAction;
import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.UserGroup;
import org.helianto.core.UserRole;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to select user roles.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userRoleAction")
public class UserRoleFormAction extends AbstractEditAggregateFormAction<UserRole, UserGroup> {

	@Override
	public UserRole doCreateTarget(RequestContext context, UserGroup parent) throws Exception {
		return UserRole.userRoleFactory(parent);
	}

	@Override
	protected UserRole doPrepareTarget(RequestContext context, UserRole target) throws Exception {
		return target;
	}

	@Override
	protected UserGroup getManagedParent(UserRole managedTarget) {
		return managedTarget.getUserGroup();
	}

	/* called inside editTarget, used to load reference data into context */
	@Override
	protected void referenceData(RequestContext context, UserRole target) throws Exception {
		Operator operator = (Operator) context.getFlowScope().get("operator");
		context.getFlowScope().put("serviceNameMap", namespaceMgr.loadServiceNameMap(operator));
	}

	/* any item selected from maps in reference data must have a corresponding property editor */
	@Override
	protected void registerPropertyEditors(PropertyEditorRegistry registry) {
		super.registerPropertyEditors(registry);
		registry.registerCustomEditor(Service.class, "target.service", servicePropertyEditor);
	}

	@Override
	protected List<UserRole> getAggregateList(RequestContext context, UserGroup parent) {
		return parent.getRoleList();
	}

	@Override
	protected UserRole doStoreTarget(UserRole detachedTarget) throws Exception {
		return namespaceMgr.storeUserRole(detachedTarget);
	}

	@Override
	protected String getKeyField() {
		return "serviceExtension";
	}

	@Override
	public String getTargetAttributeName() {
		return "userRole";
	}

	@Override
	public String getParentAttributeName() {
		return "user";
	}

	// collabs
	
	private NamespaceMgr namespaceMgr;
	private PropertyEditor servicePropertyEditor;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

	@Resource(name="servicePropertyEditor")
	public void setServicePropertyEditor(PropertyEditor servicePropertyEditor) {
		this.servicePropertyEditor = servicePropertyEditor;
	}

//	private static Log logger = LogFactory.getLog(ServiceFormAction.class);

}
