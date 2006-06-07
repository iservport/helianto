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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.dao.ResourceDao;

public class ResourceDaoImpl extends GenericDaoImpl implements ResourceDao {

    public void persistResource(Resource resource) {
        merge(resource);
    }

    public void persistResourceGroup(ResourceGroup resourceGroup) {
        merge(resourceGroup);
    }
    
    public List<ResourceGroup> findResourceByEntity(Entity entity) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) find(RESOURCEGROUP_QRY, entity);
        return resourceList;
    }
    
	public List<ResourceGroup> findRootResourceByEntity(Entity entity) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) find(RESOURCEROOT_QRY, entity);
        return resourceList;
	}

    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) find(RESOURCEGROUP_QRY_BYPARENT, resourceGroup);
        return resourceList;
    }
    
    static String RESOURCEGROUP_QRY = "from ResourceGroup resourceGroup " +
    "where resourceGroup.entity = ?";

    static String RESOURCEGROUP_QRY_BYPARENT = "from ResourceGroup resourceGroup " +
    "where resourceGroup.parent = ?";

    static String RESOURCEROOT_QRY = "from ResourceGroup resourceGroup " +
    "where resourceGroup.entity = ? and resourceGroup.parent = null";

}
