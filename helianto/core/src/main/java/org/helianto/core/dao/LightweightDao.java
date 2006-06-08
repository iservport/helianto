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
import java.util.Collection;
import org.springframework.dao.DataAccessException;

/**
 * A lightweight dao interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public interface LightweightDao {

    /**
     * Retrieve an object from the datastore by id.
     * @param clazz the object class.
     * @param key the key to search for
     * @return the object if found, or null.
     * @throws org.springframework.dao.DataRetrievalFailureException if can't 
     * connect to the datastore.
     */
    public Object load(Class clazz, Serializable key)  throws DataAccessException ;
    
    /**
     * Save an object to the datastore,
     * either inserting or updating it.
     */
    public void merge(Object object) throws DataAccessException ;

    /**
     * A method to remove current instance in the 
     * correspondig table in datastore.
     */
    public void remove(Object object) throws DataAccessException ;
    
    /**
     * Re-loads an object and all its collections.
     */
    public void refresh(Object object) throws DataAccessException ;
    
    public void persist(Object object) throws DataAccessException ;
    
    /**
     * A method to return query results.
     * 
     * <p>
     * Implementation should inspect the <code>values</code>
     * parameter to distinguish if a single object is beeing 
     * passed or if it is an array of objects before taking 
     * the appropriate action.
     * </p>
     * 
     * @deprecated Use find(String query, Object... values).
     * 
     */
    public Collection find(String query, Object values) throws DataAccessException ;

    /**
     * A method to return query results.
     * 
     * <p>
     * Uses JSE 5.0 variable argument.
     * </p>
     * 
     */
    public Collection find(String query, Object... values) throws DataAccessException ;

    /**
     * A method to return a single object from query results.
     * 
     * <p>
     * Implementation should inspect the <code>values</code>
     * parameter to distinguish if a single object is beeing 
     * passed or if it is an array of objects before taking 
     * the appropriate action.
     * </p>
     * @deprecated Use findUnique(String query, Object... values).
     * 
     */
    public Object findUnique(String query, Object values) throws DataAccessException ;

    /**
     * A method to return a single object from query results.
     * 
     * <p>
     * Uses JSE 5.0 variable argument.
     * </p>
     * 
     */
    public Object findUnique(String query, Object... values) throws DataAccessException ;

}
