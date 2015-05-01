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

import org.helianto.core.domain.Entity;
import org.helianto.resource.ResourceMgr;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.repository.ResourceGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation for <code>ResourceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("resourceMgr")
public class ResourceMgrImpl implements ResourceMgr {
	
	@Transactional
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
		ResourceGroup resourceGroup = new ResourceGroup(entity, rootEquipentCode);
		resourceGroup.setResourceTypeAsEnum(ResourceType.EQUIPMENT);
		return resourceGroup;
    }
    
	@Transactional
    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup) {
    	return resourceGroupRepository.saveAndFlush(resourceGroup);
    }
    
    // collaborators

    private ResourceGroupRepository resourceGroupRepository;
    
    @javax.annotation.Resource
    public void setResourceGroupRepository(ResourceGroupRepository resourceGroupRepository) {
		this.resourceGroupRepository = resourceGroupRepository;
	}

	static final Logger logger = LoggerFactory.getLogger(ResourceMgrImpl.class);

}
