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

import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.Unit;
import org.helianto.process.Resource;
import org.helianto.process.ResourceAssociation;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceGroupFilter;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.dao.ResourceDao;

/**
 * <code>ResourceMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ResourceMgr {
    
	/**
	 * Create a managed resource tree.
	 * 
	 * @param resourceGroupFilter
	 */
	public List<Node> prepareTree(ResourceGroupFilter resourceGroupFilter);
	
    /**
     * Find <tt>ResourceGroup</tt>s using filter.
     * 
     * @param resourceGroupFilter
     */
    public List<ResourceGroup> findResourceGroups(ResourceGroupFilter resourceGroupFilter);
    
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
    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup);
    
    /**
     * Store <tt>ResourceAssociation</tt>.  
     */
    public ResourceAssociation storeResourceAssociation(ResourceAssociation resourceAssociation);
    
    /**
     * Load lazy collections, if any. 
     */
    public ResourceGroup prepareResourceGroup(ResourceGroup resourceGroup);
    
    /**
     * Remove a <tt>ResourceAssociation</tt> from its <tt>ResourceGroup</tt>.
     * 
     * @param resourceAssociation
     * @param removeOrphan
     */
	public void removeResourceAssociation(ResourceAssociation resourceAssociation, boolean removeOrphan);

    /**
     * Remove a <tt>Resource</tt>.
     * 
     * @param resource
     */
    public void removeResource(Resource resource);
    
    /**
     * <p>
     * Create a <code>ResourceParameter</code> with a given <code>Unit</code>.
     * </p>  
     */
    public ResourceParameter createResourceParameter(Entity entity, String parameterCode, Unit unit);

    /**
     * <p>
     * Create a <code>ResourceParameter</code>.
     * </p>  
     */
    public ResourceParameter createResourceParameter(Entity entity);

    /**
     * <p>
     * Create a <code>ResourceParameter</code>.
     * </p>  
     */
    public ResourceParameter createResourceParameter(Entity entity, String parameterCode);

    /**
     * <p>
     * Create a <code>ResourceParameter</code> with a parent <code>ResourceParameter</code>.
     * </p>  
     */
    public ResourceParameter createResourceParameter(ResourceParameter parent, String parameterCode);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#persistResourceParameter(ResourceParameter)}.
     * </p>  
     */
    public ResourceParameter storeResourceParameter(ResourceParameter resourceParameter);
    
    /**
     * <p>
     * Create a <code>ParameterValue</code>.
     * </p>  
     */
    public ResourceParameterValue createParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter);

    /**
     * <p>
     * Create a suppressed <code>ParameterValue</code> to hide the occurrence of the 
     * same parameter in any parent <code>ResourceGroup</code>.
     * </p>  
     */
    public ResourceParameterValue createSuppressedParameterValue(ResourceGroup resourceGroup, ResourceParameter resourceParameter);

    /**
     * <p>
     * Delegates to {@link ResourceDao#persistResourceParameterValue(ResourceParameterValue)}.
     * </p>  
     */
    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue);
    
}
