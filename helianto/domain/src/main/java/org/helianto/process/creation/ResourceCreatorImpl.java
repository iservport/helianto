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
import org.helianto.process.ResourceGroup;

/**
 * Default implementation for the <code>ResourceGroupCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class ResourceCreatorImpl implements ResourceCreator {

    public ResourceGroup resourceGroupFactory(Entity entity, String resourceCode, ResourceType resourceType) {
        return resourceGroupFactory(entity, resourceCode, resourceType.getValue());
    }

    private ResourceGroup resourceGroupFactory(Entity entity, String resourceCode, char resourceTypeValue) {
        ResourceGroup resourceGroup = new ResourceGroup();
        resourceGroup.setEntity(entity);
        resourceGroup.setResourceCode(resourceCode);
        resourceGroup.setResourceType(resourceTypeValue);
        return resourceGroup;
    }

    public ResourceGroup resourceGroupFactory(ResourceGroup parent, String resourceCode) {
        ResourceGroup resourceGroup = resourceGroupFactory(parent.getEntity(), resourceCode, parent.getResourceType());
        resourceGroup.setParent(parent);
        return resourceGroup;
    }

}
