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

package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.ResourceGroup;

/**
 * Test suport methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceGroupTestSupport {
	
	static int testKey = 1;

    public static ResourceGroup createResourceGroup(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String resourceCode;
        try {
        	resourceCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
        	resourceCode = DomainTestSupport.getNonRepeatableStringValue(20, testKey++);
        }
        ResourceGroup resourceGroup = ResourceGroup.resourceGroupFactory(entity, resourceCode);
        return resourceGroup;
    }

    public static List<ResourceGroup> createResourceGroupList(int size) {
    	return createResourceGroupList(size, 1) ;
    }
    
    public static List<ResourceGroup> createResourceGroupList(int size, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        return createResourceGroupList(size, entityList) ;
    }
    
    public static List<ResourceGroup> createResourceGroupList(int size, List<Entity> entityList) {
        List<ResourceGroup> resourceGroupList = new ArrayList<ResourceGroup>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                resourceGroupList.add(createResourceGroup(e));
            }
        }
        return resourceGroupList ;
    }
    
}
