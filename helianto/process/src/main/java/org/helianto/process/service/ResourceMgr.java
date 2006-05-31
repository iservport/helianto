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

import org.helianto.core.Entity;
import org.helianto.process.ResourceGroup;
import org.helianto.process.dao.ResourceDao;

/**
 * Base interface to deal with <code>Resource</code>s.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ResourceMgr extends ResourceDao {
    
    /**
     * Method required to create the equipment tree.
     * 
     *  This method should be invoked at least once during installation.
     */
    public ResourceGroup installEquipmentTree(Entity entity, String rootEquipentCode);
    
    public ResourceGroup createSubGroup(ResourceGroup parentGroup, String resourceCode);
    
}
