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

import org.helianto.core.Entity;

public class EntityDaoImpl extends GenericDaoImpl implements EntityDao {

    public void persistEntity(Entity entity) {
        merge(entity);
    }

    public void removeEntity(Entity entity) {
        remove(entity);
    }

    public Entity findEntityByHomeAndAlias(String homeName, String alias) {
        return (Entity) findUnique(ENTITY_QRY, new Object[] {homeName, alias});
    }

    static final String ENTITY_QRY = 
        "from Entity entity " +
        "where entity.home.homeName = ? " +
        "and entity.alias = ?";

}
