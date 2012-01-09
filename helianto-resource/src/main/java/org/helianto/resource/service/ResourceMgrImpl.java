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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.SequenceMgr;
import org.helianto.resource.ResourceMgr;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.domain.Resource;
import org.helianto.resource.domain.ResourceAssociation;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.domain.ResourceParameter;
import org.helianto.resource.domain.ResourceParameterValue;
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
	
    public List<Node> prepareTree(Filter resourceGroupFilter) {
    	List<ResourceGroup> resourceGroupList = findResourceGroups(resourceGroupFilter);
    	ResourceRootNode root = new ResourceRootNode(resourceGroupList);
    	List<Node> resourceTree = sequenceMgr.prepareTree(root);
    	return resourceTree;
    }

	public List<ResourceGroup> findResourceGroups(ResourceGroupForm form) {
		Filter filter = new ResourceGroupFormFilterAdapter(form);
		List<ResourceGroup> resourceGroupList = (List<ResourceGroup>) resourceGroupDao.find(filter);
		if (logger.isDebugEnabled() && resourceGroupList!=null) {
			logger.debug("Found resource group list of size {}", resourceGroupList.size());
		}
		return resourceGroupList;
	}
    
	/**
	 * @deprecated
	 */
	public List<ResourceGroup> findResourceGroups(Filter resourceGroupFilter) {
		List<ResourceGroup> resourceGroupList = (List<ResourceGroup>) resourceGroupDao.find(resourceGroupFilter);
		if (logger.isDebugEnabled() && resourceGroupList!=null) {
			logger.debug("Found resource group list of size {}", resourceGroupList.size());
		}
		return resourceGroupList;
	}
    
	public List<ResourceAssociation> findResourceAssociations(Filter resourceAssociationFilter) {
		List<ResourceAssociation> resourceAssociationList = (List<ResourceAssociation>) resourceAssociationDao.find(resourceAssociationFilter);
		if (logger.isDebugEnabled() && resourceAssociationList!=null) {
			logger.debug("Found resource association list of size {}", resourceAssociationList.size());
		}
		return resourceAssociationList;
	}
    
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
		ResourceGroup resourceGroup = new ResourceGroup(entity, rootEquipentCode);
		resourceGroup.setResourceTypeAsEnum(ResourceType.EQUIPMENT);
		return resourceGroup;
    }
    
	/**
	 * @deprecated
	 */
	public ResourceGroup prepareResourceGroup(ResourceGroup resourceGroup) {
		ResourceGroup managedResourceGroup = resourceGroupDao.merge(resourceGroup);
		managedResourceGroup.setChildAssociationList(loadChildAssociationList(managedResourceGroup));
		managedResourceGroup.setParentAssociationList(loadParentAssociationList(managedResourceGroup));
		resourceGroupDao.evict(resourceGroup);
		return managedResourceGroup;
	}
	
	protected final List<ResourceAssociation> loadChildAssociationList(ResourceGroup resourceGroup) {
    	List<ResourceAssociation> childAssociationList = new ArrayList<ResourceAssociation>();
    	childAssociationList.addAll(resourceGroup.getChildAssociations());
    	Collections.sort(childAssociationList);
    	if (logger.isDebugEnabled()) {
    		logger.debug("Loaded {} child association(s).", childAssociationList.size());
    	}
    	return childAssociationList;
	}

	protected final List<ResourceAssociation> loadParentAssociationList(ResourceGroup resourceGroup) {
    	List<ResourceAssociation> parentAssociationList = new ArrayList<ResourceAssociation>();
    	for (ResourceAssociation parentAssociation: resourceGroup.getParentAssociations()) {
    		ResourceGroup parent = parentAssociation.getParent();
        	if (logger.isDebugEnabled() && parent!=null) {
        		logger.debug("Loading parent {} from {}", parent.getResourceCode(), resourceGroup.getResourceCode());
        	}
        	parentAssociationList.add(parentAssociation);
    	}
    	Collections.sort(parentAssociationList);
    	if (logger.isDebugEnabled() && parentAssociationList!=null) {
    		logger.debug("Loaded {} parent association(s).", parentAssociationList.size());
    	}
    	return parentAssociationList;
	}

    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup) {
    	resourceGroupDao.saveOrUpdate(resourceGroup);
    	return resourceGroup;
    }
    
	public void removeResourceAssociation(ResourceAssociation resourceAssociation, boolean removeOrphan) {
		// TODO remove resource association
	}

	public void removeResource(Resource resource) {
		// TODO remove resource
	}

    //

	public ResourceAssociation storeResourceAssociation(ResourceAssociation resourceAssociation) {
		resourceAssociationDao.saveOrUpdate(resourceAssociation);
		return resourceAssociation;
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
    	resourceParameterDao.saveOrUpdate(resourceParameter);
    	return resourceParameter;
    }
    
    //
    
    public ResourceParameterValue createParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue createSuppressedParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceParameterValue.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue storeResourceParameterValue(ResourceParameterValue resourceParameterValue) {
    	resourceParameterValueDao.saveOrUpdate(resourceParameterValue);
    	return resourceParameterValue;
    }
    
    // collaborators

    private FilterDao<ResourceGroup> resourceGroupDao;
    private FilterDao<ResourceAssociation> resourceAssociationDao;
    private FilterDao<ResourceParameter> resourceParameterDao;
    private FilterDao<ResourceParameterValue> resourceParameterValueDao;
    private SequenceMgr sequenceMgr;
    
    @javax.annotation.Resource(name="resourceGroupDao")
    public void setResourceGroupDao(FilterDao<ResourceGroup> resourceGroupDao) {
        this.resourceGroupDao = resourceGroupDao;
    }

    @javax.annotation.Resource(name="resourceAssociationDao")
    public void setResourceAssociationDao(FilterDao<ResourceAssociation> resourceAssociationDao) {
        this.resourceAssociationDao = resourceAssociationDao;
    }

    @javax.annotation.Resource(name="resourceParameterDao")
    public void setResourceParameterDao(FilterDao<ResourceParameter> resourceParameterDao) {
        this.resourceParameterDao = resourceParameterDao;
    }

    @javax.annotation.Resource(name="resourceParameterValueDao")
    public void setResourceParameterValueDao(FilterDao<ResourceParameterValue> resourceParameterValueDao) {
        this.resourceParameterValueDao = resourceParameterValueDao;
    }

    @javax.annotation.Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
        this.sequenceMgr = sequenceMgr;
    }

	static final Logger logger = LoggerFactory.getLogger(ResourceMgrImpl.class);

}
