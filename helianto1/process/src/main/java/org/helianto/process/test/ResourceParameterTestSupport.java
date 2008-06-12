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
import org.helianto.process.ResourceParameter;

/**
 * Test suport methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterTestSupport {
	
	static int testKey = 1;

    public static ResourceParameter createResourceParameter(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String resourceParameterCode;
        try {
        	resourceParameterCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
        	resourceParameterCode = DomainTestSupport.getNonRepeatableStringValue(20, testKey++);
        }
        ResourceParameter resourceParameter = ResourceParameter.resourceParameterFactory(
                entity, resourceParameterCode);
        return resourceParameter;
    }

    public static List<ResourceParameter> createResourceParameterList(int size) {
        return createResourceParameterList(size, 1) ;
    }

    public static List<ResourceParameter> createResourceParameterList(int size, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        return createResourceParameterList(size, entityList) ;
    }

    public static List<ResourceParameter> createResourceParameterList(int size, List<Entity> entityList) {
        List<ResourceParameter> resourceParameterList = new ArrayList<ResourceParameter>();
        for (Entity entity: entityList) {
            for (int i=0;i<size;i++) {
            	resourceParameterList.add(createResourceParameter(entity));
            }
        }
        return resourceParameterList ;
    }

}
