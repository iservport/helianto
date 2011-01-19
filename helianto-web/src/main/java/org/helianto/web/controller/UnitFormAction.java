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

import javax.annotation.Resource;

import org.helianto.controller.AbstractEditTargetFormAction;
import org.helianto.core.Category;
import org.helianto.core.Unit;
import org.helianto.core.service.UnitMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic to store categories.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("unitAction")
public class UnitFormAction extends AbstractEditTargetFormAction<Unit> {

	@Override
	protected Unit doCreateTarget(RequestContext context) throws Exception {
		return new Unit(getAuthorizedEntity(), "");
	}

	@Override
	protected Unit doStoreTarget(Unit detachedTarget) throws Exception {
		return unitMgr.storeUnit(detachedTarget);
	}

	@Override
	protected Unit doPrepareTarget(RequestContext context, Unit target) throws Exception {
		return target;
	}

	@Override
	protected boolean doProcessReturnTarget(Unit detachedTarget, Object returnTarget) throws Exception {
		if (returnTarget instanceof Category) {
			detachedTarget.setCategory((Category) returnTarget);
			return true;
		}
		return false;
	}

	@Resource(name="unitPropertyEditor")
	@Override
	public void setTargetPropertyEditor(PropertyEditor targetPropertyEditor) {
		internalTargetPropertyEditorSetter(targetPropertyEditor, Unit.class);
	}

	@Override
	public String getTargetAttributeName() {
		return "unit";
	}
	
	// collabs
	
	private UnitMgr unitMgr;

	@Resource
    public void setUnitMgr(UnitMgr unitMgr) {
    	this.unitMgr = unitMgr;
    }
    
}
