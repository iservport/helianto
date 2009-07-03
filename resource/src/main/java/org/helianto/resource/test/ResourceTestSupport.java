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

package org.helianto.resource.test;

import org.helianto.core.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.resource.Resource;

/**
 * Test suport methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceTestSupport {
	
	static int testKey = 1;

    public static Resource createResource() {
        return ResourceTestSupport.createResource(EntityTestSupport.createEntity());
    }

    public static Resource createResource(Entity entity) {
        return ResourceTestSupport.createResource(entity, DomainTestSupport.getNonRepeatableStringValue(20, testKey++));
    }

    public static Resource createResource(Entity entity, String resourceCode) {
        Resource resourceGroup = Resource.resourceFactory(entity, resourceCode);
        return resourceGroup;
    }

}
