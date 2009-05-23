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
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;

/**
 * Presentation logic to select provinces.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("provinceFilterAction")
public class ProvinceFilterFormAction extends
		AbstractFilterOnlyFormAction<ProvinceFilter, Province> {

	@Override
	public ProvinceFilter doCreateFilter() throws Exception {
		return new ProvinceFilter();
	}

	@Override
	protected List<Province> doApplyFilter(ProvinceFilter filter) {
		return namespaceMgr.findProvinces(filter);
	}

	@Override
	public String getTargetAttributeName() {
		return "province";
	}

	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
//	private static Log logger = LogFactory.getLog(ProvinceFilterFormAction.class);

}
