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
import org.helianto.core.Unit;
import org.helianto.partner.Partner;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.ResourceType;
import org.helianto.process.dao.ResourceDao;
import org.helianto.process.dao.ResourceGroupDao;
import org.helianto.process.dao.ResourceParameterDao;
import org.helianto.process.dao.ResourceParameterValueDao;

/**
 * Default implementation for <code>ResourceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceMgrImpl implements ResourceMgr {
	
    private ResourceGroupDao resourceGroupDao;
    private ResourceDao resourceDao;
    private ResourceParameterDao resourceParameterDao;
    private ResourceParameterValueDao resourceParameterValueDao;

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
    
    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup) {
    	return resourceGroupDao.mergeResourceGroup(resourceGroup);
    }
    
    //

    public Resource prepareResource(ResourceGroup parentGroup) {
        return prepareResource(parentGroup, "");
    }

    public Resource prepareResource(ResourceGroup parentGroup, String resourceCode) {
        return createResource(parentGroup, resourceCode, null);
    }

	public Resource createResource(ResourceGroup parentGroup, String resourceCode, Partner owner) {
		Resource resource = Resource.resourceFactory(parentGroup, resourceCode);
		resource.setOwner(owner);
		return resource;
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
    
    //

    public List<ResourceGroup> findResourceByEntity(Entity entity) {
        throw new RuntimeException("Not yet implemented");
    }

	public List<ResourceGroup> findRootResourceByEntity(Entity entity) {
        throw new RuntimeException("Not yet implemented");
	}

    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup) {
        throw new RuntimeException("Not yet implemented");
    }
    
    public ResourceGroup findResourceByEntityAndCode(Entity entity, String resourceCode) {
        throw new RuntimeException("Not yet implemented");
    }
    
    public List<ResourceParameter> findResourceParameterByEntity(Entity entity) {
        throw new RuntimeException("Not yet implemented");
    }
    
    public List<ResourceParameter> findResourceParameterByParent(ResourceParameter parent) {
        throw new RuntimeException("Not yet implemented");
    }
    
    public List<ResourceParameterValue> findResourceParameterValueByResource(ResourceGroup resourceGroup) {
        throw new RuntimeException("Not yet implemented");
    }
    
    public ResourceParameter findResourceParameterByEntityAndCode(Entity entity, String resourceParameterCode) {
        throw new RuntimeException("Not yet implemented");
    }
    
    // collaborators

    @javax.annotation.Resource
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
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

	static final Log logger = LogFactory.getLog(ResourceMgrImpl.class);
    
}
