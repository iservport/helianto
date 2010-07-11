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

package org.helianto.core.repository;

import java.util.Collection;

/**
 * Simple persistence strategy.
 * 
 * <p>
 * Implementing classes may either delegate to Hibernate or to
 * Jpa.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PersistenceStrategy<T> {
	
    /**
     * Find by criteria.
     * 
     * @param query
     * @param values
     */
	public Collection<T> find(String query, Object... values);

    /**
     * Persist a managed object.
     * 
     * @param managedObject
     */
    public void persist(T managedObject);
    
    /**
     * Hiberante operation to save or update a managed object.
     * 
     * @param managedObject
     */
    public void saveOrUpdate(T managedObject);
    
    /**
     * Merge an object and return a persisted managed object.
     * 
     * @param object
     */
    public T merge(T object);
    
    /**
     * Remove an object from the datastore.
     * 
     * @param object
     */
    public void remove(T object);
    
    /**
     * Remove an object from the cache (session or context).
     * 
     * @param object
     */
    public void evict(T object);
    
    /**
     * Flush the session.
     */
	public void flush();
	
    /**
     * Clear the session.
     */
	public void clear();
}
