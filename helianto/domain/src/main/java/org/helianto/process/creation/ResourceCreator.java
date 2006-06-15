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
import org.helianto.core.Partner;
import org.helianto.core.creation.NullAssociationException;
import org.helianto.core.creation.NullEntityException;
import org.helianto.core.creation.NullParentException;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.Unit;

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
    public ResourceGroup resourceGroupFactory(Entity entity, String resourceCode, ResourceType resourceType) throws NullEntityException;

    /**
     * The <code>ResourceGroup</code> factory method.
     */
    public ResourceGroup resourceGroupFactory(ResourceGroup parent, String resourceCode) throws NullEntityException;

    /**
     * A <code>Resource</code> factory method to create resources with no groups. 
     * Requires a sub-class of <code>Partner</code> as owner.
     */
    public Resource resourceFactory(Partner owner, String resourceCode, ResourceType resourceType) throws NullEntityException;

    /**
     * The <code>Resource</code> factory method. Requires a sub-class of <code>Partner</code> as owner.
     */
    public Resource resourceFactory(ResourceGroup parent, String resourceCode, Partner owner) throws NullEntityException;

    /**
     * The <code>ResourceParameter</code> factory method.
     */
    public ResourceParameter resourceParameterFactory(Entity entity, String parameterCode, Unit unit) 
    	throws NullEntityException;

    /**
     * The <code>ResourceParameter</code> factory method. Requires a <code>Unit</code>.
     */
    public ResourceParameter resourceParameterFactory(Entity entity, String parameterCode) 
    	throws NullEntityException;

    /**
     * The <code>ResourceParameter</code> factory method. Requires a parent <code>ResourceParameter</code>.
     */
    public ResourceParameter resourceParameterFactory(ResourceParameter parent, String parameterCode) 
    	throws NullEntityException, NullParentException;

    /**
     * The <code>ResourceParameterValue</code> factory method.
     */
    public ResourceParameterValue resourceParameterValueFactory(ResourceGroup resourceGroup, ResourceParameter resourceParameter, boolean suppressed) 
		throws NullAssociationException;

    /**
     * The <code>ResourceParameterValue</code> factory method.
     */
    public ResourceParameterValue resourceParameterValueFactory(ResourceGroup resourceGroup, ResourceParameter resourceParameter) 
		throws NullAssociationException;

}