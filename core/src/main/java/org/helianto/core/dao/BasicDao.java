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

import java.util.Collection;

/**
 * A convenient sub-set of persistence operations.
 * 
 * @author Mauricio Fernandes de Castro.
 */
public interface BasicDao<T> {
	
	/**
	 * The persistent class.
	 */
	public Class<? extends T> getClazz();

    /**
     * Persist a managed object.
     */
    public void persist(T managedObject);
    
    /**
     * Merge an object into a persisted managed object.
     */
    public T merge(T object);
    
    /**
     * Remove an object.
     */
    public void remove(T object);
    
    /**
     * Evict an object.
     */
    public void evict(T object);
    
    /**
     * Find unique.
     */
    public T findUnique(Object... args);
    
    /**
     * Find by criteria.
     */
    public Collection<T> find(String whereClause);
    
    /**
     * Find by criteria.
     */
    public Collection<T> find(StringBuilder selectClause, String whereClause);
    
    /**
     * Flush the session.
     */
	public void flush();
	
    /**
     * Clear the session.
     */
	public void clear();
}
