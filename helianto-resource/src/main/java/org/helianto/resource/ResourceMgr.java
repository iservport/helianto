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

package org.helianto.resource;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Node;
import org.helianto.core.filter.Filter;
import org.helianto.resource.domain.Resource;
import org.helianto.resource.domain.ResourceAssociation;
import org.helianto.resource.domain.ResourceGroup;
import org.helianto.resource.domain.ResourceParameter;
import org.helianto.resource.domain.ResourceParameterValue;

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
	public List<Node> prepareTree(Filter resourceGroupFilter);
	
    /**
     * Find <tt>ResourceGroup</tt>s using filter.
     * 
     * @param resourceGroupFilter
     */
    public List<ResourceGroup> findResourceGroups(Filter resourceGroupFilter);
    
    /**
     * Find <tt>ResourceAssociation</tt>s using filter.
     * 
     * @param resourceAssociationFilter
     */
    public List<ResourceAssociation> findResourceAssociations(Filter resourceAssociationFilter);
    
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
     * Store a parameter value. 
     */
    public ResourceParameterValue storeResourceParameterValue(ResourceParameterValue resourceParameterValue);
    
}