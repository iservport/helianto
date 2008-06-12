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
import org.helianto.process.Resource;
import org.helianto.process.dao.ResourceDao;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Default implementation for <code>ResourceDao</code> interface.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends GenericDaoImpl implements ResourceDao {

    public Resource findResourceByNaturalId(Entity entity, String resourceCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique resource with entity='"+entity+"' and docCode='"+resourceCode+"' ");
        }
        return (Resource) findUnique(Resource.getResourceNaturalIdQueryString(), entity, resourceCode);
    }

    @SuppressWarnings("unchecked")
	public List<Resource> findResources(String criteria) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding resource list with criteria ='"+criteria+"'");
        }
        if (criteria.equals("")) {
            return (ArrayList<Resource>) find(Resource.getResourceQueryStringBuilder());
        }
        return (ArrayList<Resource>) find(new StringBuilder(Resource.getResourceQueryStringBuilder()).append("where ").append(criteria));
    }
}
