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

package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceCreatorImpl;
import org.helianto.process.dao.ResourceDao;
import org.helianto.process.type.ResourceType;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Test suport methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceTestSupport extends AbstractIntegrationTest {
	
	static int testKey = 1;

    public static ResourceGroup createResourceGroup(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        ResourceGroup resourceGroup = ResourceCreatorImpl.resourceGroupFactory(
                entity, generateKey(20, testKey++), ResourceType.EQUIPMENT);
        return resourceGroup;
    }

    public static ResourceGroup createAndPersistResourceGroup(ResourceDao resourceDao) {
        ResourceGroup resourceGroup = createResourceGroup();
        if (resourceDao!=null) {
            resourceDao.persistResourceGroup(resourceGroup);
        }
        return resourceGroup;
    }

    public static List<ResourceGroup> createAndPersistResourceGroupList(HibernateTemplate hibernateTemplate, int i, int e) {
        List<ResourceGroup> resourceGroupList = createResourceGroupList(i, e);
        for (ResourceGroup g: resourceGroupList) {
            hibernateTemplate.merge(g);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return resourceGroupList;
    }

    public static List<ResourceGroup> createResourceGroupList(int size, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<ResourceGroup> resourceGroupList = new ArrayList<ResourceGroup>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                resourceGroupList.add(ResourceCreatorImpl.resourceGroupFactory(e, generateKey(20, i), ResourceType.EQUIPMENT));
            }
        }
        return resourceGroupList ;
    }

    public static List<ResourceGroup> createAndPersistResourceGroupList(HibernateTemplate hibernateTemplate, int i, int e, int p) {
        List<ResourceGroup> resourceGroupList = createResourceGroupList(i, e, p);
        hibernateTemplate.saveOrUpdateAll(resourceGroupList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return resourceGroupList;
    }

    public static List<ResourceGroup> createResourceGroupList(int size, int entityListSize, int parentListSize) {
        List<ResourceGroup> parentList = createResourceGroupList(parentListSize, entityListSize);
        List<ResourceGroup> resourceGroupList = new ArrayList<ResourceGroup>();
        for (ResourceGroup p: parentList) {
            resourceGroupList.add(p);
            for (int i=0;i<size;i++) {
                resourceGroupList.add(ResourceCreatorImpl.resourceGroupFactory(p, generateKey(20, i)));
            }
        }
        return resourceGroupList ;
    }

    public static Resource createResource() {
        ResourceGroup resourceGroup = createResourceGroup();
        Resource resource = ResourceCreatorImpl.resourceFactory(resourceGroup, "R"+generateKey(19), null);
        return resource;
    }

    public static Resource createAndPersistResource(ResourceDao resourceDao) {
        Resource resource = createResource();
        if (resourceDao!=null) {
            resourceDao.persistResourceGroup(resource);
        }
        return resource;
    }

    public static List<ResourceGroup> createAndPersistResourceList(HibernateTemplate hibernateTemplate, int i, int e, int p) {
        List<ResourceGroup> resourceList = createResourceList(i, e, p);
        hibernateTemplate.saveOrUpdateAll(resourceList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return resourceList;
    }
    
    public static List<ResourceGroup> createResourceList(int size, int entityListSize, int parentListSize) {
        List<ResourceGroup> resourceGroupList =  createResourceGroupList(parentListSize, entityListSize);
        List<ResourceGroup> resourceList = new ArrayList<ResourceGroup>();
        for (ResourceGroup g: resourceGroupList) {
            resourceList.add(g);
            for (int i=0;i<size;i++) {
                resourceList.add(ResourceCreatorImpl.resourceFactory(g, generateKey(20, testKey++), null));
            }
        }
        return resourceList;
    }

    
    
}