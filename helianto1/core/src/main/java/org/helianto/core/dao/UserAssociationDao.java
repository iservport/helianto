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

import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;

/**
 * <code>UserAssociation</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserAssociationDao extends CommonOrmDao {
     
    /**
     * Persist <code>UserAssociation</code>.
     */
    public void persistUserAssociation(UserAssociation userAssociation);
    
    /**
     * Merge <code>UserAssociation</code>.
     */
    public UserAssociation mergeUserAssociation(UserAssociation userAssociation);
    
    /**
     * Remove <code>UserAssociation</code>.
     */
    public void removeUserAssociation(UserAssociation userAssociation);
    
    /**
     * Find <code>UserAssociation</code> by <code>UserGroup</code> and <code>UserGroup</code>.
     */
    public UserAssociation findUserAssociationByNaturalId(UserGroup parent, UserGroup child);
    
    
    
}
