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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.core.Self;
import org.helianto.core.dao.PartnerDao;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.Unit;
import org.helianto.process.creation.ResourceCreator;
import org.helianto.process.dao.ResourceDao;
import org.helianto.process.type.ResourceType;
import org.springframework.beans.factory.annotation.Required;

public class ResourceMgrImpl implements ResourceMgr {
	
	private final Log logger = LogFactory.getLog(getClass());
    
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
        return getResourceCreator().resourceGroupFactory(entity, rootEquipentCode, ResourceType.EQUIPMENT);
    }
    
//    public Resource createEquipment(ResourceGroup parentGroup, String equipentCode) {
//        return getResourceCreator().resourceGroupFactory(parentGroup, equipentCode);
//    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup) {
        return getResourceCreator().resourceGroupFactory(parentGroup, "");
    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup, String resourceCode) {
        return getResourceCreator().resourceGroupFactory(parentGroup, resourceCode);
    }
    
    public void persistResourceGroup(ResourceGroup resourceGroup) {
        getResourceDao().persistResourceGroup(resourceGroup);
    }
    
    public ResourceGroup loadResourceGroup(Serializable key) {
        if (key instanceof String) {
            return getResourceDao().load(Integer.parseInt((String) key)); 
        }
        return getResourceDao().load(key);
    }

    //

    public Resource prepareResource(ResourceGroup parentGroup) {
        return prepareResource(parentGroup, "");
    }

    public Resource prepareResource(ResourceGroup parentGroup, String resourceCode) {
        Self self = partnerDao.whoAmI(parentGroup.getEntity());
        return createResource(parentGroup, resourceCode, self);
    }

	public Resource createResource(ResourceGroup parentGroup, String resourceCode, Self self) {
		if (self==null) {
			throw new NullPointerException("Resource owner must be a non-null Partner (or Self)");
		}
		return resourceCreator.resourceFactory(parentGroup, resourceCode, self);
	}
	
    public void persistResource(Resource resource) {
        resourceDao.persistResource(resource);
    }
    
    //
    
    public ResourceParameter createResourceParameter(Entity entity) {
        return resourceCreator.resourceParameterFactory(entity, "");
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode, Unit unit) {
        return resourceCreator.resourceParameterFactory(entity, parameterCode, unit);
    }

    public ResourceParameter createResourceParameter(Entity entity, String parameterCode) {
        return resourceCreator.resourceParameterFactory(entity, parameterCode);
    }

    public ResourceParameter createResourceParameter(ResourceParameter parent, String parameterCode) {
        return resourceCreator.resourceParameterFactory(parent, parameterCode);
    }
    
    public void persistResourceParameter(ResourceParameter resourceParameter) {
        resourceDao.persistResourceParameter(resourceParameter);
    }
    
    public ResourceParameter loadResourceParameter(Serializable key) {
        if (key instanceof String) {
            return resourceDao.loadResourceParameter(Integer.parseInt((String) key)); 
        }
        return resourceDao.loadResourceParameter((Integer) key);
    }
    
    //
    
    public ResourceParameterValue createParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter);
    }

    public ResourceParameterValue createSuppressedParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        return resourceCreator.resourceParameterValueFactory(resourceGroup, resourceParameter, true);
    }

    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue) {
        resourceDao.persistResourceParameterValue(resourceParameterValue);
    }
    
    public ResourceParameterValue loadResourceParameterValue(Serializable key) {
        if (key instanceof String) {
            return resourceDao.loadResourceParameterValue(Integer.parseInt((String) key)); 
        }
        return resourceDao.loadResourceParameterValue((Integer) key);
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

    private ResourceCreator resourceCreator; 
    private PartnerDao partnerDao;
    private ResourceDao resourceDao;

    public ResourceCreator getResourceCreator() {
        return resourceCreator;
    }

	public PartnerDao getPartnerDao() {
		return partnerDao;
	}

    public ResourceDao getResourceDao() {
        return resourceDao;
    }

    @Required
    public void setResourceCreator(ResourceCreator resourceCreator) {
        this.resourceCreator = resourceCreator;
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
