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

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.ResourceParameter;
import org.helianto.process.dao.ResourceParameterDao;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Default implementation for <code>ResourceParameterDao</code> interface.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceParameterDao")
public class ResourceParameterDaoImpl extends GenericDaoImpl implements ResourceParameterDao {

    public void persistResourceParameter(ResourceParameter resourceParameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+resourceParameter);
        }
        persist(resourceParameter);
    }
    
    public ResourceParameter mergeResourceParameter(ResourceParameter resourceParameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+resourceParameter);
        }
        return (ResourceParameter) merge(resourceParameter);
    }
    
    public void removeResourceParameter(ResourceParameter resourceParameter) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+resourceParameter);
        }
        remove(resourceParameter);
    }

    public ResourceParameter findResourceParameterByNaturalId(Entity entity, String resourceCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique document with entity='"+entity+"' and docCode='"+resourceCode+"' ");
        }
        return (ResourceParameter) findUnique(ResourceParameter.getResourceParameterNaturalIdQueryString(), entity, resourceCode);
    }

    @SuppressWarnings("unchecked")
	public List<ResourceParameter> findResourceParameters(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding document list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<ResourceParameter>) find(ResourceParameter.getResourceParameterQueryStringBuilder());
        }
        return (ArrayList<ResourceParameter>) find(new StringBuilder(ResourceParameter.getResourceParameterQueryStringBuilder()).append("where ").append(criteria));
    }
}
