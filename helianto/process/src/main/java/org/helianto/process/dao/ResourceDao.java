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

import java.io.Serializable;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceType;

public interface ResourceDao {
    
    /**
     * Persist a <code>ResourceGroup</code>.
     */
    public void persistResourceGroup(ResourceGroup resourceGroup);
    
    /**
     * Persist a <code>Resource</code>.
     */
    public void persistResource(Resource resource);
    
    /**
     * Load a <code>Resource</code>.
     */
    public ResourceGroup load(Serializable key);
    
    /**
     * Find <code>ResourceGroup</code>s and <code>Resources</code> by <code>Entity</code>.
     */
    public List<ResourceGroup> findResourceAndGroupByEntity(Entity entity);
    
    /**
     * Find <code>Resource</code>s by <code>Entity</code>.
     */
    public List<Resource> findResourceByEntity(Entity entity);
    
    /**
     * Find <code>ResourceGroup</code>s and <code>Resources</code> by <code>Entity</code> 
     * and <code>ResourceType</code>.
     */
    public List<ResourceGroup> findResourceAndGroupByEntityAndType(Entity entity, ResourceType resourceType);
    
    /**
     * Find <code>Resource</code>s by <code>Entity</code>
     * and <code>ResourceType</code>.
     */
    public List<Resource> findResourceByEntityAndType(Entity entity, ResourceType resourceType);
    
    /**
     * Find <code>ResourceGroup</code>s and <code>Resources</code> by parent.
     */
    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup);
    
    /**
     * Find <code>ResourceGroup</code>s by <code>Entity</code> where
     * parent is null.
     */
    public List<ResourceGroup> findRootResourceByEntity(Entity entity);
    
    /**
     * Find <code>ResourceGroup</code>s by <code>Entity</code> and <code>ResourceType</code> where
     * parent is null.
     */
    public List<ResourceGroup> findRootResourceByEntityAndType(Entity entity, ResourceType resourceType);

    /**
     * Find <code>ResourceGroup</code> or <code>Resource</code> by <code>Entity</code> and code.
     */
    public ResourceGroup findResourceByEntityAndCode(Entity entity, String resourceCode);

}
