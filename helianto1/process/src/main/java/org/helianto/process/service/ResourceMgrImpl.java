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

package org.helianto.process.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.Unit;
import org.helianto.core.filter.SelectionStrategy;
import org.helianto.core.service.SequenceMgr;
import org.helianto.process.Resource;
import org.helianto.process.ResourceAssociation;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceGroupFilter;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.ResourceType;
import org.helianto.process.dao.ResourceGroupDao;
import org.helianto.process.dao.ResourceParameterDao;
import org.helianto.process.dao.ResourceParameterValueDao;

/**
 * Default implementation for <code>ResourceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceMgrImpl implements ResourceMgr {
	
    public List<Node> prepareTree(ResourceGroupFilter resourceGroupFilter) {
    	List<ResourceGroup> resourceGroupList = findResourceGroups(resourceGroupFilter);
    	ResourceRootNode root = new ResourceRootNode(resourceGroupList);
    	List<Node> resourceTree = sequenceMgr.prepareTree(root);
    	return resourceTree;
    }

	public List<ResourceGroup> findResourceGroups(ResourceGroupFilter resourceGroupFilter) {
		String criteria = resourceGroupSelectionStrategy.createCriteriaAsString(resourceGroupFilter, "resourceGroup");
		List<ResourceGroup> resourceGroupList = resourceGroupDao.findResourceGroups(criteria);
		if (logger.isDebugEnabled() && resourceGroupList!=null) {
			logger.debug("Found resource group list of size "+resourceGroupList.size());
		}
		return resourceGroupList;
	}
    
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
		ResourceGroup resourceGroup = ResourceGroup.resourceGroupFactory(entity, rootEquipentCode);
		resourceGroup.setResourceType(ResourceType.EQUIPMENT);
		return resourceGroup;
    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup) {
        return ResourceGroup.resourceGroupFactory(parentGroup, "");
    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup, String resourceCode) {
        return ResourceGroup.resourceGroupFactory(parentGroup, resourceCode);
    }
    
	public ResourceGroup prepareResourceGroup(ResourceGroup resourceGroup) {
		ResourceGroup managedResourceGroup = resourceGroupDao.mergeResourceGroup(resourceGroup);
		managedResourceGroup.getChildAssociations();
		resourceGroupDao.evictResourceGroup(resourceGroup);
		return managedResourceGroup;
	}

    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup) {
    	return resourceGroupDao.mergeResourceGroup(resourceGroup);
    }
    
	public void removeResourceAssociation(ResourceAssociation resourceAssociation, boolean removeOrphan) {
		// TODO remove resource association
	}

	public void removeResource(Resource resource) {
		// TODO remove resource
	}

    //

	public ResourceAssociation storeResourceAssociation(ResourceAssociation resourceAssociation) {
		return resourceGroupDao.mergeResourceAssociation(resourceAssociation);
	}
	
	//

    public ResourceParameter createResourceParameter(Entity entity) {
        return ResourceParameter.resourceParameterFactory(entity, "");
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode, Unit unit) {
        return ResourceParameter.resourceParameterFactory(unit, parameterCode);
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode) {
        return ResourceParameter.resourceParameterFactory(entity, parameterCode);
    }

    public ResourceParameter createResourceParameter(ResourceParameter parent, String parameterCode) {
        return ResourceParameter.resourceParameterFactory(parent, parameterCode);
    }
    
    public ResourceParameter storeResourceParameter(ResourceParameter resourceParameter) {
    	return resourceParameterDao.mergeResourceParameter(resourceParameter);
    }
    
    //
    
    public ResourceParameterValue createParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue createSuppressedParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue) {
    	resourceParameterValueDao.persistResourceParameterValue(resourceParameterValue);
    }
    
    // collaborators

    private ResourceGroupDao resourceGroupDao;
    private SelectionStrategy<ResourceGroupFilter> resourceGroupSelectionStrategy;
    private ResourceParameterDao resourceParameterDao;
    private ResourceParameterValueDao resourceParameterValueDao;
    private SequenceMgr sequenceMgr;
    
    @javax.annotation.Resource(name="resourceGroupSelectionStrategy")
    public void setResourceGroupSelectionStrategy(SelectionStrategy<ResourceGroupFilter> resourceGroupSelectionStrategy) {
        this.resourceGroupSelectionStrategy = resourceGroupSelectionStrategy;
    }

    @javax.annotation.Resource
    public void setResourceGroupDao(ResourceGroupDao resourceGroupDao) {
        this.resourceGroupDao = resourceGroupDao;
    }

    @javax.annotation.Resource
    public void setResourceParameterDao(ResourceParameterDao resourceParameterDao) {
        this.resourceParameterDao = resourceParameterDao;
    }

    @javax.annotation.Resource
    public void setResourceParameterValueDao(ResourceParameterValueDao resourceParameterValueDao) {
        this.resourceParameterValueDao = resourceParameterValueDao;
    }

    @javax.annotation.Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
        this.sequenceMgr = sequenceMgr;
    }

	static final Log logger = LogFactory.getLog(ResourceMgrImpl.class);

}
