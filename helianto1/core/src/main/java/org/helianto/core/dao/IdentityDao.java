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

import java.util.List;

import org.helianto.core.Identity;
import org.helianto.core.dao.CommonOrmDao;
import org.helianto.core.filter.IdentityFilter;



/**
 * <code>Identity</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface IdentityDao extends CommonOrmDao {
     
    /**
     * Persist <code>Identity</code>.
     */
    public void persistIdentity(Identity identity);
    
    /**
     * Merge <code>Identity</code>.
     */
    public Identity mergeIdentity(Identity identity);
    
    /**
     * Remove <code>Identity</code>.
     */
    public void removeIdentity(Identity identity);
    
    /**
     * Find <code>Identity</code> by principal.
     */
    public Identity findIdentityByNaturalId(String principal);
    
    /**
     * Find <code>Identity</code> by principal.
     */
    public List<Identity> findIdentityByCriteria(IdentityFilter identityFilter);   
    
}
