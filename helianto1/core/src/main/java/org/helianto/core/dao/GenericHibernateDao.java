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
 * Extends the <code>LightWeightDao</code> to provide 
 * additional methods to access code.
 * 
 * @author Maurício Fernandes de Castro
 */
public interface GenericHibernateDao {
    
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
     * Copies object state to datastore and make its
     * instance persistent.
     * 
     * @param object
     * @throws DataAccessException
     */
    public void persist(Object object) throws DataAccessException ;
    
    /**
     * Copies object state to datastore and returns
     * a persistent instance. The supplied instance remains
     * detached.
     * 
     * @param object
     * @throws DataAccessException
     */
    public Object merge(Object object) throws DataAccessException ;

    /**
     * Removes instance from the session.
     * 
     * @param object
     * @throws DataAccessException
     */
    public void remove(Object object) throws DataAccessException ;
    
    /**
     * Re-loads an object from datastore.
     * 
     * @param object
     * @throws DataAccessException
     */
    public void refresh(Object object) throws DataAccessException ;
    
    /**
     * True if session contains the object.
     * 
     * @param object
     * @throws DataAccessException
     */
    public boolean contains(Object object) throws DataAccessException;
    
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
     * A method to return query results.
     * 
     * <p>
     * Uses JSE 5.0 variable argument.
     * </p>
     * 
     */
    public Collection find(StringBuilder query, Object... values) throws DataAccessException;
    
    /**
     * A method to return a single object from query results.
     * 
     * <p>
     * Uses JSE 5.0 variable argument.
     * </p>
     * 
     */
    public Object findUnique(String query, Object... values) throws DataAccessException ;

    /**
     * A method to save an instance to the 
     * database.
     */
    public void save(Object object);

    /**
     * A method to save or update an instance to the 
     * datastore. The decision is made upon the content
     * of the id attribute: if it is present (not null),
     * then update, else save.
     */
    public void saveOrUpdate(Object object);
    
    /**
     * A method to update an instance to the datastore.
     */
    public void update(Object object);

    /**
     * A method to disconnect a persistent instance from the 
     * current Session.
     */
    public void evict(Object object);
    
}
