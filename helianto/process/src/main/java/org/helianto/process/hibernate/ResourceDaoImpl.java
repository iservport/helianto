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

package org.helianto.process.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceType;
import org.helianto.process.dao.ResourceDao;

/**
 * <p>
 * Hibernate implementation for <code>ResourceDao</code> interface.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceDaoImpl extends GenericDaoImpl implements ResourceDao {

    public void persistResource(Resource resource) {
        merge(resource);
    }

    public void persistResourceGroup(ResourceGroup resourceGroup) {
        merge(resourceGroup);
    }
    
    public ResourceGroup load(Serializable key) {
        return (ResourceGroup) load(ResourceGroup.class, key);
    }
    
    public List<ResourceGroup> findResourceAndGroupByEntity(Entity entity) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY, entity);
        return resourceList;
    }
    
    public List<Resource> findResourceByEntity(Entity entity) {
        List<Resource> resourceList = (ArrayList<Resource>) 
            find(RESOURCE_QRY, entity);
        return resourceList;
    }
    
    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY_BYPARENT, resourceGroup);
        return resourceList;
    }
    
    public List<ResourceGroup> findRootResourceByEntity(Entity entity) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY+RESOURCEROOT_FILTER, entity);
        return resourceList;
    }

    public List<ResourceGroup> findResourceAndGroupByEntityAndType(Entity entity, ResourceType resourceType) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY+RESOURCETYPE_FILTER, entity, resourceType.getValue());
        return resourceList;
    }

    public List<Resource> findResourceByEntityAndType(Entity entity, ResourceType resourceType) {
        List<Resource> resourceList = (ArrayList<Resource>) 
            find(RESOURCE_QRY+RESOURCETYPE_FILTER, entity, resourceType.getValue());
        return resourceList;
    }

    public List<ResourceGroup> findRootResourceByEntityAndType(Entity entity, ResourceType resourceType) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY+RESOURCEROOT_FILTER+RESOURCETYPE_FILTER, entity, resourceType.getValue());
        return resourceList;
    }

    public ResourceGroup findResourceByEntityAndCode(Entity entity, String resourceCode) {
        ResourceGroup resourceGroup = (ResourceGroup)
            findUnique(RESOURCEGROUP_QRY+RESOURCECODE_FILTER, entity, resourceCode);
        return resourceGroup;
    }

    static String RESOURCEGROUP_QRY = "from ResourceGroup resource " +
        "where resource.entity = ?";

    static String RESOURCE_QRY = "from Resource resource " +
        "where resource.entity = ?";

    static String RESOURCEGROUP_QRY_BYPARENT = "from ResourceGroup resource " +
        "where resource.parent = ?";

    static String RESOURCEROOT_FILTER = " and resource.parent = null";
    
    static String RESOURCETYPE_FILTER = " and resource.resourceType = ?";

    static String RESOURCECODE_FILTER = " and resource.resourceCode = ?";

}
