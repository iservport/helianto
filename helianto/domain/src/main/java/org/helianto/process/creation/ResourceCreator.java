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
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;

/**
 * A factory method pattern interface to <code>ResourceGroup</code>
 * related domain objects.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public interface ResourceCreator {
    
    /**
     * The <code>ResourceGroup</code> factory method.
     */
    public ResourceGroup resourceGroupFactory(Entity entity, String resourceCode, ResourceType resourceType);

    /**
     * The <code>ResourceGroup</code> factory method.
     */
    public ResourceGroup resourceGroupFactory(ResourceGroup parent, String resourceCode);

    /**
     * The <code>Resource</code> factory method.
     */
    public Resource resourceFactory(Entity entity, String resourceCode, ResourceType resourceType);

    /**
     * The <code>Resource</code> factory method.
     */
    public Resource resourceFactory(ResourceGroup parent, String resourceCode);

}
