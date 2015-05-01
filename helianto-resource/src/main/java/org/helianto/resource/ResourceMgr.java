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

import org.helianto.core.domain.Entity;
import org.helianto.resource.domain.ResourceGroup;

/**
 * Resource service interface.
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
     * Store a resource group.  
     */
    public ResourceGroup storeResourceGroup(ResourceGroup resourceGroup);
    
}
