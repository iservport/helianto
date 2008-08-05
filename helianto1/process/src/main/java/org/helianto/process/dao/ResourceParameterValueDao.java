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

package org.helianto.process.dao;

import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;

/**
 * <code>ResourceParameterValue</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ResourceParameterValueDao {
    
    /**
     * Persist a <code>ResourceParameterValue</code>.
     */
	public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue);
	
    /**
     * Merge a <code>ResourceParameterValue</code>.
     */
	public ResourceParameterValue mergeResourceParameterValue(ResourceParameterValue resourceParameterValue);

    /**
     * Remove a <code>ResourceParameterValue</code>.
     */
    public void removeResourceParameterValue(ResourceParameterValue resourceParameterValue);

    /**
     * Find <code>ResourceParameterValue</code> by <code>ResourceGroup</code> and <code>ResourceParameter</code>.
     */
    public ResourceParameterValue findResourceParameterValueByNaturalId(ResourceGroup resourceGroup, ResourceParameter resourceParameter);

}