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

package org.helianto.core.orm;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.dao.EntityDao;
import org.helianto.core.hibernate.GenericDaoImpl;



import org.helianto.core.Operator;
import org.springframework.stereotype.Repository;
/**
 * Default implementation of <code>Entity</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("entityDao")
public class EntityDaoImpl extends GenericDaoImpl implements EntityDao {
     
    public void persistEntity(Entity entity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+entity);
        }
        persist(entity);
    }
    
    public Entity mergeEntity(Entity entity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+entity);
        }
        return (Entity) merge(entity);
    }
    
    public void removeEntity(Entity entity) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+entity);
        }
        remove(entity);
    }
    
    public Entity findEntityByNaturalId(Operator operator, String alias) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique entity with operator='"+operator+"' and alias='"+alias+"' ");
        }
        return (Entity) findUnique(Entity.getEntityNaturalIdQueryString(), operator, alias);
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Entity> findEntities(String criteria) {
        if (criteria!=null && !criteria.equals("")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding entity list with criteria='"+criteria+"' ");
            }
            return (ArrayList<Entity>) find(Entity.getEntityQueryStringBuilder().append("where ").append(criteria));
        }
        else {
            if (logger.isDebugEnabled()) {
                logger.debug("Finding full entity list");
            }
            return (ArrayList<Entity>) find(Entity.getEntityQueryStringBuilder());
        }
    }
    
}
