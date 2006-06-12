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

import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;

/**
 * Base interface to deal with <code>Resource</code>s.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ResourceMgr {
    
    /**
     * <p>
     * Method required to create the equipment tree.
     * </p>
     * <p>
     * This method should be invoked at least once during installation.
     * </p>
     */
    public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode);
    
    /**
     * <p>
     * Delegates to {@link ResourceCreator#resourceGroupFactory(ResourceGroup, String)} with
     * an empty resource code (to be filled at the presentation layer).
     * </p>  
     */
    public ResourceGroup createSubGroup(ResourceGroup parentGroup);
    
    /**
     * <p>
     * Delegates to {@link ResourceCreator#resourceGroupFactory(ResourceGroup, String)}.
     * </p>  
     */
    public ResourceGroup createSubGroup(ResourceGroup parentGroup, String resourceCode);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#persistResourceGroup(ResourceGroup)}.
     * </p>  
     */
    public void persistResourceGroup(ResourceGroup resourceGroup);
    
    /**
     * <p>
     * Create a <code>Resource</code> for the parent <code>ResourceGroup</code> with
     * an empty resource code (to be filled at the presentation layer) and 
     * prepare the current <code>Division</code> to be the default owner.
     * </p> 
     */
    public Resource prepareResource(ResourceGroup parentGroup);
    
    /**
     * <p>
     * Create a <code>Resource</code> for the parent <code>ResourceGroup</code> and
     * prepare the current <code>Division</code> to be the default owner.
     * </p> 
     */
    public Resource prepareResource(ResourceGroup parentGroup, String resourceCode);
    
    /**
     * <p>
     * Create a <code>Resource</code> for the parent <code>ResourceGroup</code> with
     * a given owner.
     * </p> 
     */
    public Resource createResource(ResourceGroup parentGroup, String resourceCode, Partner owner);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#persistResource(Resource)}.
     * </p>  
     */
    public void persistResource(Resource resource);
    
    /**
     * Load a <code>Resource</code>.
     */
    public ResourceGroup load(Serializable key);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findRootResourceByEntity(Entity)}.
     * </p>  
     */
    public List<ResourceGroup> findRootResourceByEntity(Entity entity);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceByEntity(Entity)}.
     * </p>  
     */
    public List<ResourceGroup> findResourceByEntity(Entity entity);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceByParent(ResourceGroup)}.
     * </p>  
     */
    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceByEntityAndCode(Entity, String)}.
     * </p>  
     */
    public ResourceGroup findResourceByEntityAndCode(Entity entity, String resourceCode);
    
}
