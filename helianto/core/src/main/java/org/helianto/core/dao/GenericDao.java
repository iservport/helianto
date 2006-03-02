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

/**
 * Extends the <code>LightWeightDao</code> to provide 
 * additional hibernate-like access code.
 * 
 * @author Maurício Fernandes de Castro
 * @version $Id$
 */
public interface GenericDao extends LightweightDao {
    
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
