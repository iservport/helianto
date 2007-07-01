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

package org.helianto.core.orm;

import org.helianto.core.UserAssociation;
import org.helianto.core.UserGroup;
import org.helianto.core.dao.UserAssociationDao;
import org.helianto.core.hibernate.GenericDaoImpl;
/**
 * Default implementation of <code>UserAssociation</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserAssociationDaoImpl extends GenericDaoImpl implements UserAssociationDao {
     
    public void persistUserAssociation(UserAssociation userAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting user association parent and "+userAssociation);
        }
        persist(userAssociation.getParent());
    }
    
    public UserAssociation mergeUserAssociation(UserAssociation userAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging  user association parent and "+userAssociation);
        }
        UserGroup mergedParent = (UserGroup) merge(userAssociation.getParent());
        return mergedParent.getChildAssociations().iterator().next();
    }
    
    public void removeUserAssociation(UserAssociation userAssociation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+userAssociation);
        }
        remove(userAssociation);
    }
    
    public UserAssociation findUserAssociationByNaturalId(UserGroup parent, UserGroup child) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique userAssociation with parent='"+parent+"' and child='"+child+"' ");
        }
        return (UserAssociation) findUnique(UserAssociation.getUserAssociationNaturalIdQueryString(), parent, child);
    }
    
}
