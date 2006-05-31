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

package org.helianto.core;

import org.helianto.process.ResourceGroup;
import org.helianto.process.creation.ResourceType;

import junit.framework.TestCase;

public class ResourceUseCaseDomainTests extends TestCase {
    
    private ResourceGroup resourceGroup;
    private Entity entity;
    
    public void test() {
        // ResourceGroup
        resourceGroup = new ResourceGroup();
        entity = new Entity();
        resourceGroup.setEntity(entity);
        resourceGroup.setResourceCode("");
        resourceGroup.setParent(null);
        resourceGroup.setParent(new ResourceGroup());
        resourceGroup.setResourceName("");
        resourceGroup.setResourceType(ResourceType.EQUIPMENT.getValue());
        resourceGroup.setResourceType(ResourceType.FIXTURE.getValue());
        resourceGroup.setResourceType(ResourceType.INSTRUMENT.getValue());
        resourceGroup.setResourceType(ResourceType.TOOL.getValue());
        
        assertTrue(resourceGroup.equals(resourceGroup));
        assertFalse(resourceGroup.equals(null));
        assertFalse(resourceGroup.equals(new Object()));
        ResourceGroup rg = new ResourceGroup();
        assertFalse(resourceGroup.equals(rg));
        resourceGroup.setResourceCode("123");
        rg.setEntity(null);
        rg.setResourceCode(null);
        assertFalse(resourceGroup.equals(rg));
        rg.setEntity(entity);
        assertFalse(resourceGroup.equals(rg));
        rg.setResourceCode("123");
        assertTrue(resourceGroup.equals(rg));
        

    }

}
