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
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.Unit;

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
    public ResourceGroup loadResourceGroup(Serializable key);
    
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
    public void persistResourceParameter(ResourceParameter resourceParameter);
    
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
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#loadResourceParameterValue(int)}.
     * </p>  
     */
    public ResourceParameterValue loadResourceParameterValue(Serializable key);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceParameterByEntity(Entity)}.
     * </p>  
     */
    public List<ResourceParameter> findResourceParameterByEntity(Entity entity);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceParameterByParent(ResourceParameter)}.
     * </p>  
     */
    public List<ResourceParameter> findResourceParameterByParent(ResourceParameter parent);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceParameterValueByResource(ResourceGroup)}.
     * </p>  
     */
    public List<ResourceParameterValue> findResourceParameterValueByResource(ResourceGroup resourceGroup);
    
    /**
     * <p>
     * Delegates to {@link ResourceDao#findResourceByEntityAndCode(Entity, String)}.
     * </p>  
     */
    public ResourceParameter findResourceParameterByEntityAndCode(Entity entity, String resourceParameterCode);
    
}
