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

package org.helianto.process.dao;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.ResourceAssociation;
import org.helianto.process.ResourceGroup;

/**
 * <code>ResourceGroup</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ResourceGroupDao {
    
    /**
     * Persist a <code>ResourceGroup</code> or <code>Resource</code>.
     */
    public void persistResourceGroup(ResourceGroup resourceGroup);
    
    /**
     * Merge a <code>ResourceGroup</code> or <code>Resource</code>.
     */
    public ResourceGroup mergeResourceGroup(ResourceGroup resourceGroup);
    
    /**
     * Remove a <code>ResourceGroup</code> or <code>Resource</code> from the session.
     */
    public void evictResourceGroup(ResourceGroup resourceGroup);

    /**
     * Remove a <code>ResourceGroup</code> or <code>Resource</code> from the data store.
     */
    public void removeResourceGroup(ResourceGroup resourceGroup);

    /**
     * Find <code>ResourceGroup</code>s by naturall id.
     */
    public ResourceGroup findResourceGroupByNaturalId(Entity entity, String resourceCode);

    /**
     * Find <code>ResourceGroup</code>s by string criteria.
     */
    public List<ResourceGroup> findResourceGroups(String criteria);

    /**
     * Merge a <code>ResourceAssociation</code>.
     */
	public ResourceAssociation mergeResourceAssociation(ResourceAssociation resourceAssociation);

}
