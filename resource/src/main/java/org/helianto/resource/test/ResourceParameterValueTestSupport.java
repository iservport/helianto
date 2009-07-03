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

import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceParameter;
import org.helianto.resource.ResourceParameterValue;

/**
 * Test suport methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceParameterValueTestSupport {
	
    public static ResourceParameterValue createResourceParameterValue() {
        return ResourceParameterValueTestSupport.createResourceParameterValue(ResourceGroupTestSupport.createResourceGroup());
    }

    public static ResourceParameterValue createResourceParameterValue(ResourceGroup resource) {
        return ResourceParameterValueTestSupport.createResourceParameterValue(resource, ResourceParameterTestSupport.createResourceParameter());
    }

    public static ResourceParameterValue createResourceParameterValue(ResourceGroup resource, ResourceParameter parameter) {
        ResourceParameterValue resourceParameterValue = ResourceParameterValue.resourceParameterValueFactory(
        		resource, parameter);
        return resourceParameterValue;
    }

}
