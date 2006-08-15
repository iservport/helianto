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

package org.helianto.core.hibernate;

import org.helianto.core.DefaultEntity;
import org.helianto.core.Entity;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.dao.EntityDao;
import org.springframework.util.Assert;

/**
 * Hibernate implementation for <code>EntityDao</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class EntityDaoImpl extends GenericDaoImpl implements EntityDao {

    public void persistEntity(Entity entity) {
        merge(entity);
    }

    public void removeEntity(Entity entity) {
        remove(entity);
    }

    public Entity findEntityByHomeAndAlias(String homeName, String alias) {
        return (Entity) findUnique(ENTITY_QRY, homeName, alias);
    }

    static final String ENTITY_QRY = 
        "from Entity entity " +
        "where entity.home.homeName = ? " +
        "and entity.alias = ?";
    
    public void persistDefaultEntity(DefaultEntity defaultEntity) {
        merge(defaultEntity);
    }

    public void removeDefaultEntity(DefaultEntity defaultEntity) {
        remove(defaultEntity);
    }

    public Entity findDefaultEntity() {
        return findDefaultEntity(0);
    }

    public Entity findDefaultEntity(int priority) {
        DefaultEntity defaultEntity = (DefaultEntity) findUnique(DEFAULT_ENTITY_QRY_BY_PRIOR, 0);
        if (defaultEntity!=null) {
            return defaultEntity.getEntity(); 
        }
        return null;
    }

    static final String DEFAULT_ENTITY_QRY_BY_PRIOR = 
        "from DefaultEntity defaultEntity " +
        "where defaultEntity.priority = ?";
    
    public InternalEnumerator findInternalEnumerator(Entity entity, String typeName)  {
        Assert.notNull(entity);
    	return (InternalEnumerator) findUnique(ENUM_QRY, entity, typeName);
    }
    
    static final String ENUM_QRY = 
        "from InternalEnumerator enumerator " +
        "where enumerator.entity = ? "+
        "and enumerator.typeName = ? ";

	public void persistInternalEnumerator(InternalEnumerator internalEnumerator) {
		merge(internalEnumerator);
	}
    

    
}
