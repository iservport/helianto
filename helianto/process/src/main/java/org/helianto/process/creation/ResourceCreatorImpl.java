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

package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.core.Partner;
import org.helianto.core.Self;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.Unit;
import org.helianto.process.type.ResourceType;
import org.springframework.util.Assert;

/**
 * Default implementation for the <code>ResourceCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public class ResourceCreatorImpl implements ResourceCreator {

    public ResourceGroup resourceGroupFactory(Entity entity, String resourceCode, ResourceType resourceType) {
        Assert.notNull(entity);
        return resourceGroupFactory(ResourceGroup.class, entity, resourceCode, resourceType.getValue());
    }

    private ResourceGroup resourceGroupFactory(Class clazz, Entity entity, String resourceCode, char resourceTypeValue) {
        Assert.notNull(entity);
        ResourceGroup resourceGroup;
        try {
            resourceGroup = (ResourceGroup) clazz.newInstance();
            resourceGroup.setEntity(entity);
            resourceGroup.setResourceCode(resourceCode);
            resourceGroup.setResourceType(resourceTypeValue);
            return resourceGroup;
        } catch (Exception e) {
            throw new RuntimeException("Can't create class "+clazz);
        }
    }

    public ResourceGroup resourceGroupFactory(ResourceGroup parent, String resourceCode) {
        ResourceGroup resourceGroup = resourceGroupFactory(ResourceGroup.class, parent.getEntity(), resourceCode, parent.getResourceType());
        resourceGroup.setParent(parent);
        return resourceGroup;
    }

	public Resource resourceFactory(Partner owner, String resourceCode, ResourceType resourceType) {
        Resource resource = (Resource) resourceGroupFactory(Resource.class, owner.getEntity(), resourceCode, resourceType.getValue());
        resource.setOwner(owner);
        return resource;
	}

    public Resource resourceFactory(ResourceGroup parent, String resourceCode, Self self) {
        Resource resource = (Resource) resourceGroupFactory(Resource.class, parent.getEntity(), resourceCode, parent.getResourceType());
        resource.setParent(parent);
        resource.setOwner(self);
        return resource;
    }
    
    public ResourceParameter resourceParameterFactory(Entity entity, String parameterCode, Unit unit) {
        Assert.notNull(entity);
    	ResourceParameter resourceParameter;
    	try {
        	resourceParameter = new ResourceParameter();
    		resourceParameter.setEntity(entity);
    		resourceParameter.setParameterCode(parameterCode);
    		resourceParameter.setUnit(unit);
        	return resourceParameter;
    	} catch (Exception e){
    		throw new RuntimeException("Can't instantiate "+ResourceParameter.class, e);
    	}
    }

    public ResourceParameter resourceParameterFactory(Entity entity, String parameterCode) {
	    return resourceParameterFactory(entity, parameterCode, null);
    }

    public ResourceParameter resourceParameterFactory(ResourceParameter parent, String parameterCode) {
        Assert.notNull(parent);
    	ResourceParameter resourceParameter = resourceParameterFactory(parent.getEntity(), parameterCode, parent.getUnit());
    	resourceParameter.setParent(parent);
    	return resourceParameter;
    }

    public ResourceParameterValue resourceParameterValueFactory(ResourceGroup resourceGroup, ResourceParameter resourceParameter, boolean suppressed) {
        Assert.notNull(resourceGroup);
        Assert.notNull(resourceParameter);
        ResourceParameterValue resourceParameterValue;
    	try {
    		resourceParameterValue = new ResourceParameterValue();
    		resourceParameterValue.setResource(resourceGroup);
    		resourceParameterValue.setParameter(resourceParameter);
    		resourceParameterValue.setSuppressed(suppressed);
        	return resourceParameterValue;
    	} catch (Exception e){
    		throw new RuntimeException("Can't instantiate "+ResourceParameter.class, e);
    	}
    }

    public ResourceParameterValue resourceParameterValueFactory(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        Assert.notNull(resourceGroup);
        Assert.notNull(resourceParameter);
    	return resourceParameterValueFactory(resourceGroup, resourceParameter, false);
    }

}