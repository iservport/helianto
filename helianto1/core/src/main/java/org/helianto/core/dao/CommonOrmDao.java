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

package org.helianto.core.dao;

import java.io.Serializable;

/**
 * Common methods to ORM tools.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface CommonOrmDao {
    
    /**
     * Clear the persistence context or session, causing all managed
     * entities to become detached. Changes made to entities that
     * have not been flushed to the database will not be
     * persisted.
     */
    public void clear();

    /**
     * Synchronize the persistence or session context to the
     * underlying database.
     */
    public void flush();
    
    /**
     * Retrieve an object from the datastore by id.
     * @param clazz the object class.
     * @param key the key to search for
     */
    public <T> T find(Class<T> clazz, Serializable key);

}