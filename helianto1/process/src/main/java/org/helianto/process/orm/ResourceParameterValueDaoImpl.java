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

package org.helianto.process.orm;

import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.dao.ResourceParameterValueDao;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Default implementation for <code>ResourceParameterDao</code> interface.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceParameterValueDao")
public class ResourceParameterValueDaoImpl extends GenericDaoImpl implements ResourceParameterValueDao {

    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+resourceParameterValue);
        }
        persist(resourceParameterValue);
    }
    
    public ResourceParameterValue mergeResourceParameterValue(ResourceParameterValue resourceParameterValue) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+resourceParameterValue);
        }
        return (ResourceParameterValue) merge(resourceParameterValue);
    }
    
    public void removeResourceParameterValue(ResourceParameterValue resourceParameterValue) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+resourceParameterValue);
        }
        remove(resourceParameterValue);
    }

    public ResourceParameterValue findResourceParameterValueByNaturalId(ResourceGroup resourceGroup, ResourceParameter resourceParameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique document with resourceGroup='"+resourceGroup+"' and resourceParameter='"+resourceParameter+"' ");
        }
        return (ResourceParameterValue) findUnique(ResourceParameterValue.getResourceParameterValueNaturalIdQueryString(), resourceGroup, resourceParameter);
    }

}
