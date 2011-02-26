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
import org.helianto.core.Unit;
import org.helianto.core.filter.classic.UnitFilter;
import org.helianto.core.service.UnitMgr;

/**
 * Presentation logic to select units.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 */
public class UnitFilterFormAction extends AbstractFilterOnlyFormAction<UnitFilter, Unit> {

	@Override
	public UnitFilter doCreateFilter() throws Exception {
		return UnitFilter.unitFilterFactory(getAuthorizedUser());
	}

	@Override
	protected List<Unit> doApplyFilter(UnitFilter filter) {
		return unitMgr.findUnits(filter);
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
