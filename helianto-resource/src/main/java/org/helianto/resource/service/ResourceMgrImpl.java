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

package org.helianto.resource.service;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.resource.ResourceMgr;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.filter.ResourceGroupFormFilterAdapter;
import org.helianto.resource.form.ResourceGroupForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation for <code>ResourceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("resourceMgr")
public class ResourceMgrImpl implements ResourceMgr {
	
	public List<ResourceGroup> findResourceGroups(ResourceGroupForm form) {
		Filter filter = new ResourceGroupFormFilterAdapter(form);
		List<ResourceGroup> resourceGroupList = (List<ResourceGroup>) resourceGroupDao.find(filter);
		if (logger.isDebugEnabled() && resourceGroupList!=null) {
			logger.debug("Found resource group list of size {}", resourceGroupList.size());
		}
		return resourceGroupList;
	}
    
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
		ResourceGroup resourceGroup = new ResourceGroup(entity, rootEquipentCode);
		resourceGroup.setResourceTypeAsEnum(ResourceType.EQUIPMENT);
		return resourceGroup;
    }
    
    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup) {
    	resourceGroupDao.saveOrUpdate(resourceGroup);
    	return resourceGroup;
    }
    
    // collaborators

    private FilterDao<ResourceGroup> resourceGroupDao;
    
    @javax.annotation.Resource(name="resourceGroupDao")
    public void setResourceGroupDao(FilterDao<ResourceGroup> resourceGroupDao) {
        this.resourceGroupDao = resourceGroupDao;
    }

	static final Logger logger = LoggerFactory.getLogger(ResourceMgrImpl.class);

}
