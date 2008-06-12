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

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.ResourceParameter;

/**
 * <code>ResourceParameter</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ResourceParameterDao {
    
    /**
     * Persist a <code>ResourceParameter</code>.
     */
	public void persistResourceParameter(ResourceParameter resourceParameter);

    /**
     * Merge a <code>ResourceParameter</code>.
     */
	public ResourceParameter mergeResourceParameter(ResourceParameter resourceParameter);

    /**
     * Remove a <code>ResourceParameter</code>.
     */
    public void removeResourceParameter(ResourceParameter resourceParameter);

    /**
     * Find <code>ResourceParameter</code> by <code>Entity</code> and code.
     */
    public ResourceParameter findResourceParameterByNaturalId(Entity entity, String resourceParameterCode);

    /**
     * Find <code>ResourceParameter</code>s by  parent criteria.
     */
    public List<ResourceParameter> findResourceParameters(String criteria);
    
}
