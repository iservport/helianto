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
import org.helianto.partner.dao.PartnerDao;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.ResourceType;
import org.helianto.process.creation.ResourceCreatorImpl;
import org.helianto.process.dao.ResourceDao;
import org.springframework.beans.factory.annotation.Required;

public class ResourceMgrImpl implements ResourceMgr {
	
	static final Log logger = LogFactory.getLog(ResourceMgrImpl.class);
    
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
        return ResourceCreatorImpl.resourceGroupFactory(entity, rootEquipentCode, ResourceType.EQUIPMENT);
    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup) {
        return ResourceCreatorImpl.resourceGroupFactory(parentGroup, "");
    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup, String resourceCode) {
        return ResourceCreatorImpl.resourceGroupFactory(parentGroup, resourceCode);
    }
    
    public void persistResourceGroup(ResourceGroup resourceGroup) {
        getResourceDao().persistResourceGroup(resourceGroup);
    }
    
    //

    public Resource prepareResource(ResourceGroup parentGroup) {
        return prepareResource(parentGroup, "");
    }

    public Resource prepareResource(ResourceGroup parentGroup, String resourceCode) {
        return createResource(parentGroup, resourceCode, null);
    }

	public Resource createResource(ResourceGroup parentGroup, String resourceCode, Partner partner) {
		return ResourceCreatorImpl.resourceFactory(parentGroup, resourceCode, partner);
	}
	
    public void persistResource(Resource resource) {
        resourceDao.persistResourceGroup(resource);
    }
    
    //
    
    public ResourceParameter createResourceParameter(Entity entity) {
        return ResourceCreatorImpl.resourceParameterFactory(entity, "", null);
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode, Unit unit) {
        return ResourceCreatorImpl.resourceParameterFactory(entity, parameterCode, unit);
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode) {
        return ResourceCreatorImpl.resourceParameterFactory(entity, parameterCode, null);
    }

    public ResourceParameter createResourceParameter(ResourceParameter parent, String parameterCode) {
        return ResourceCreatorImpl.resourceParameterFactory(parent, parameterCode);
    }
    
    public void persistResourceParameter(ResourceParameter resourceParameter) {
        resourceDao.persistResourceParameter(resourceParameter);
    }
    
    //
    
    public ResourceParameterValue createParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceCreatorImpl.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue createSuppressedParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return ResourceCreatorImpl.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue) {
        resourceDao.persistResourceParameterValue(resourceParameterValue);
    }
    
    //

    public List<ResourceGroup> findResourceByEntity(Entity entity) {
        return resourceDao.findResourceAndGroupByEntity(entity);
    }

	public List<ResourceGroup> findRootResourceByEntity(Entity entity) {
        return resourceDao.findRootResourceByEntity(entity);
	}

    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup) {
        return resourceDao.findResourceByParent(resourceGroup);
    }
    
    public ResourceGroup findResourceByEntityAndCode(Entity entity, String resourceCode) {
        return resourceDao.findResourceByEntityAndCode(entity, resourceCode);
    }
    
    public List<ResourceParameter> findResourceParameterByEntity(Entity entity) {
        return resourceDao.findResourceParameterByEntity(entity);
    }
    
    public List<ResourceParameter> findResourceParameterByParent(ResourceParameter parent) {
        return resourceDao.findResourceParameterByParent(parent);
    }
    
    public List<ResourceParameterValue> findResourceParameterValueByResource(ResourceGroup resourceGroup) {
        return resourceDao.findResourceParameterValueByResource(resourceGroup);
    }
    
    public ResourceParameter findResourceParameterByEntityAndCode(Entity entity, String resourceParameterCode) {
        return resourceDao.findResourceParameterByEntityAndCode(entity, resourceParameterCode);
    }
    
    // accesssors and mutators

    private PartnerDao partnerDao;
    private ResourceDao resourceDao;

	public PartnerDao getPartnerDao() {
		return partnerDao;
	}

    public ResourceDao getResourceDao() {
        return resourceDao;
    }

	@Required
    public void setPartnerDao(PartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

    @Required
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

}
