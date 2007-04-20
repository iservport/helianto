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

/**
 * Hibernate implementation for <code>EntityDao</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated in favour of new partner components
 */
public class DefaultEntityDaoImpl extends GenericDaoImpl  {

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
    
}
