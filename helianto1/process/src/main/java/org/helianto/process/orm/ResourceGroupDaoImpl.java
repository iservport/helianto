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

package org.helianto.process.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.ResourceGroup;
import org.helianto.process.dao.ResourceGroupDao;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Default implementation for <code>ResourceGroupDao</code> interface.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceGroupDao")
public class ResourceGroupDaoImpl extends GenericDaoImpl implements ResourceGroupDao {

    public void persistResourceGroup(ResourceGroup resourceGroup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+resourceGroup);
        }
        persist(resourceGroup);
    }
    
    public ResourceGroup mergeResourceGroup(ResourceGroup resourceGroup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+resourceGroup);
        }
        return (ResourceGroup) merge(resourceGroup);
    }
    
    public void removeResourceGroup(ResourceGroup resourceGroup) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+resourceGroup);
        }
        remove(resourceGroup);
    }

    public ResourceGroup findResourceGroupByNaturalId(Entity entity, String resourceCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique document with entity='"+entity+"' and docCode='"+resourceCode+"' ");
        }
        return (ResourceGroup) findUnique(ResourceGroup.getResourceGroupNaturalIdQueryString(), entity, resourceCode);
    }

    @SuppressWarnings("unchecked")
	public List<ResourceGroup> findResourceGroups(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding document list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<ResourceGroup>) find(ResourceGroup.getResourceGroupQueryStringBuilder());
        }
        return (ArrayList<ResourceGroup>) find(new StringBuilder(ResourceGroup.getResourceGroupQueryStringBuilder()).append("where ").append(criteria));
    }
}
