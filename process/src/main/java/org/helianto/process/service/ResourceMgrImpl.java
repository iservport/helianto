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
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.process.Resource;
import org.helianto.process.ResourceAssociation;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceGroupFilter;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterFilter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.ResourceType;

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
		List<ResourceGroup> resourceGroupList = (List<ResourceGroup>) resourceGroupDao.find(resourceGroupFilter);
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
		ResourceGroup managedResourceGroup = resourceGroupDao.merge(resourceGroup);
		managedResourceGroup.getChildAssociations();
		resourceGroupDao.evict(resourceGroup);
		return managedResourceGroup;
	}

    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup) {
    	return resourceGroupDao.merge(resourceGroup);
    }
    
	public void removeResourceAssociation(ResourceAssociation resourceAssociation, boolean removeOrphan) {
		// TODO remove resource association
	}

	public void removeResource(Resource resource) {
		// TODO remove resource
	}

    //

	public ResourceAssociation storeResourceAssociation(ResourceAssociation resourceAssociation) {
		return resourceAssociationDao.merge(resourceAssociation);
	}
	
	//

    public ResourceParameter createResourceParameter(Entity entity) {
        return ResourceParameter.resourceParameterFactory(entity, "");
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode) {
        return ResourceParameter.resourceParameterFactory(entity, parameterCode);
    }

    public ResourceParameter createResourceParameter(ResourceParameter parent, String parameterCode) {
        return ResourceParameter.resourceParameterFactory(parent, parameterCode);
    }
    
    public ResourceParameter storeResourceParameter(ResourceParameter resourceParameter) {
    	return resourceParameterDao.merge(resourceParameter);
    }
    
    //
    
    public ResourceParameterValue createParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue createSuppressedParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue storeResourceParameterValue(ResourceParameterValue resourceParameterValue) {
    	return resourceParameterValueDao.merge(resourceParameterValue);
    }
    
    // collaborators

    private FilterDao<ResourceGroup, ResourceGroupFilter> resourceGroupDao;
    private BasicDao<ResourceAssociation> resourceAssociationDao;
    private FilterDao<ResourceParameter, ResourceParameterFilter> resourceParameterDao;
    private BasicDao<ResourceParameterValue> resourceParameterValueDao;
    private SequenceMgr sequenceMgr;
    
    @javax.annotation.Resource(name="resourceGroupDao")
    public void setResourceGroupDao(FilterDao<ResourceGroup, ResourceGroupFilter> resourceGroupDao) {
        this.resourceGroupDao = resourceGroupDao;
    }

    @javax.annotation.Resource(name="resourceAssociationDao")
    public void setResourceAssociationDao(BasicDao<ResourceAssociation> resourceAssociationDao) {
        this.resourceAssociationDao = resourceAssociationDao;
    }

    @javax.annotation.Resource(name="resourceParameterDao")
    public void setResourceParameterDao(FilterDao<ResourceParameter, ResourceParameterFilter> resourceParameterDao) {
        this.resourceParameterDao = resourceParameterDao;
    }

    @javax.annotation.Resource(name="resourceParameterValueDao")
    public void setResourceParameterValueDao(BasicDao<ResourceParameterValue> resourceParameterValueDao) {
        this.resourceParameterValueDao = resourceParameterValueDao;
    }

    @javax.annotation.Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
        this.sequenceMgr = sequenceMgr;
    }

	static final Log logger = LogFactory.getLog(ResourceMgrImpl.class);

}
