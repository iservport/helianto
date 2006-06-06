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

import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.core.creation.EntityCreator;
import org.helianto.core.dao.PartnerDao;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceCreator;
import org.helianto.process.creation.ResourceType;
import org.helianto.process.dao.ResourceDao;

public class ResourceMgrImpl implements ResourceMgr {
    
	public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode) {
        return getResourceCreator().resourceGroupFactory(entity, rootEquipentCode, ResourceType.EQUIPMENT);
    }
    
//    public Resource createEquipment(ResourceGroup parentGroup, String equipentCode) {
//        return getResourceCreator().resourceGroupFactory(parentGroup, equipentCode);
//    }
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup, String resourceCode) {
        return getResourceCreator().resourceGroupFactory(parentGroup, resourceCode);
    }
    
    public void persistResourceGroup(ResourceGroup resourceGroup) {
        getResourceDao().persistResourceGroup(resourceGroup);
    }

	public Resource createResource(ResourceGroup parentGroup, String resourceCode) {
		return resourceCreator.resourceFactory(parentGroup, resourceCode, null);
	}

	public Resource createResource(ResourceGroup parentGroup, String resourceCode, Partner owner) {
		if (owner==null) {
			owner = findCurrentDivision(parentGroup.getEntity());
		}
		return resourceCreator.resourceFactory(parentGroup, resourceCode, owner);
	}
	
	// TODO move to partnerDao
	public Division findCurrentDivision(Entity entity) {
		List<Division> divisionList = getPartnerDao().findDivisionByEntity(entity);
		for (Division d: divisionList) {
			if (d.getRelated()!=null && d.getRelated().equals(entity)) {
				return d;
			}
		}
		return null;
	}

    public List<ResourceGroup> findResourceByEntity(Entity entity) {
        return getResourceDao().findResourceByEntity(entity);
    }

	public List<ResourceGroup> findRootResourceByEntity(Entity entity) {
        return getResourceDao().findRootResourceByEntity(entity);
	}

    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup) {
        return getResourceDao().findResourceByParent(resourceGroup);
    }
    
    // accesssors and mutators

    private EntityCreator entityCreator; 
    private ResourceCreator resourceCreator; 
    private PartnerDao partnerDao;
    private ResourceDao resourceDao;

    public EntityCreator getEntityCreator() {
        return entityCreator;
    }

    public ResourceCreator getResourceCreator() {
        return resourceCreator;
    }

	public PartnerDao getPartnerDao() {
		return partnerDao;
	}

    public ResourceDao getResourceDao() {
        return resourceDao;
    }

    public void setEntityCreator(EntityCreator entityCreator) {
        this.entityCreator = entityCreator;
    }

    public void setResourceCreator(ResourceCreator resourceCreator) {
        this.resourceCreator = resourceCreator;
    }

	public void setPartnerDao(PartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

}
