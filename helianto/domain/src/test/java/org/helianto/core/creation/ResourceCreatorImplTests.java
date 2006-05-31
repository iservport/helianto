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

package org.helianto.core.creation;

import org.helianto.core.Entity;
import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceCreator;
import org.helianto.process.creation.ResourceCreatorImpl;
import org.helianto.process.creation.ResourceType;

import junit.framework.TestCase;

public class ResourceCreatorImplTests extends TestCase {
    
    private ResourceCreator resourceCreator;
    
    @Override
    public void setUp() {
        resourceCreator = new ResourceCreatorImpl();
    }
    
    public void testResourceCreation() {
        Entity entity = new Entity();
        ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        assertSame(entity, resourceGroup.getEntity());
        assertEquals("123", resourceGroup.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resourceGroup.getResourceType());
    }
    
    public void testResourceParentCreation() {
        Entity entity = new Entity();
        ResourceGroup parent = resourceCreator.resourceGroupFactory(entity, "123", ResourceType.EQUIPMENT);
        ResourceGroup resourceGroup = resourceCreator.resourceGroupFactory(parent, "456");
        assertSame(entity, resourceGroup.getEntity());
        assertEquals("456", resourceGroup.getResourceCode());
        assertEquals(ResourceType.EQUIPMENT.getValue(), resourceGroup.getResourceType());
        assertSame(parent, resourceGroup.getParent());
    }
}
